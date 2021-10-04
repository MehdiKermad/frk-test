package com.mehdik.mvvmdi.ui.main

import androidx.lifecycle.ViewModel
import com.mehdik.mvvmdi.models.Dish
import com.mehdik.mvvmdi.models.PicsDiaporama
import com.mehdik.mvvmdi.models.Restaurant
import com.mehdik.mvvmdi.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    @Inject
    lateinit var productImagesList: List<CarouselItem>
    private var mRestaurant: Restaurant? = null //restaurant with details
    @Inject
    lateinit var dishList: MutableList<Dish>

    fun getRestaurant(id: Int): Observable<Restaurant> {

        mRestaurant?.let {

            //if we already have the detailProduct, we return it
            return Observable.just(it)
        } ?: run {

            //if we don't have the detailProduct, we call the API
            return mainRepository.getRestaurant(id)
                .map {
                    setImagesList(it.pics_diaporama)
                    dishList.clear()
                    dishList.addAll(it.dishList)
                    mRestaurant = it //save productDetail in a var
                    it
                }
        }
    }

    fun getImagesList(): List<CarouselItem> = productImagesList

    fun getDishes(): List<Dish> = dishList

    //create a list of images for carousel from URL
    private fun setImagesList(imagesList: List<PicsDiaporama>) {
        productImagesList = imagesList.map { img ->
            CarouselItem(img.url, img.label)
        }
    }
}