package com.mehdik.mvvmdi.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehdik.mvvmdi.R
import com.mehdik.mvvmdi.databinding.MainFragmentBinding
import com.mehdik.mvvmdi.models.Restaurant
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val DEFAULT_RESTAURANT_ID = 1884
    }

    private val mainViewModel: MainViewModel by viewModels()
    private var disposable: Disposable? = null
    private var mainBinding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        mainBinding = binding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //carousel of product images
        mainBinding?.imagesProductCarousel?.registerLifecycle(lifecycle)
        mainBinding?.imagesProductCarousel?.setData(mainViewModel.getImagesList())

        //configures recyclerView
        mainBinding?.dishRecyclerView?.layoutManager = LinearLayoutManager(context)

        //filling recyclerView with the dishes
        mainBinding?.dishRecyclerView?.adapter =
            MainAdapter(
                mainViewModel.getDishes(),
                object : MainAdapter.OnItemClickListener {
                    override fun openDish() {
                        Toast.makeText(
                            context,
                            getString(R.string.dish_on_click),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

        //getting restaurant infos & dishes
        getRestaurant()
    }

    override fun onStop() {
        disposeSubscribers()
        super.onStop()
    }

    override fun onDestroyView() {
        mainBinding = null
        super.onDestroyView()
    }

    private fun getRestaurant(id: Int = DEFAULT_RESTAURANT_ID) {

        //showing progressBar during the API call
        mainBinding?.progressView?.visibility = View.VISIBLE

        //calling the API
        disposable = mainViewModel.getRestaurant(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Restaurant>() {
                override fun onNext(restaurant: Restaurant) {
                    mainBinding?.progressView?.visibility = View.GONE
                    updateRestaurant(restaurant)
                }

                override fun onError(e: Throwable) {
                    mainBinding?.progressView?.visibility = View.GONE
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    Log.e("KERMAD", e.message.toString())
                }

                override fun onComplete() {
                }
            })
    }

    @SuppressLint("SetTextI18n")
    private fun updateRestaurant(restaurant: Restaurant) {

        mainBinding?.imagesProductCarousel?.setData(mainViewModel.getImagesList())

        //filling the UI with received data
        mainBinding?.rateRestaurantText?.text = restaurant.avg_rate.toString()
        mainBinding?.specialitiesText?.text = restaurant.speciality
        mainBinding?.averagePriceText?.text =
            getString(R.string.average_price, restaurant.min_price)
        mainBinding?.reviewsPriceText?.text = getString(R.string.reviews, restaurant.rate_count)

        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        mainBinding?.lastUpdatePriceText?.text = "${getString(R.string.updated_on)} $date"
        mainBinding?.infoChiefCardText?.text =
            "${getString(R.string.chief_card_preview)} ${restaurant.chef_name}"

        //refreshing recyclerView with received dishes
        mainBinding?.dishRecyclerView?.adapter?.notifyItemRangeChanged(
            0,
            restaurant.dishList.size - 1
        )
    }

    private fun disposeSubscribers() {
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }
}