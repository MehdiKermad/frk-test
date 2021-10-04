package com.mehdik.mvvmdi.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehdik.mvvmdi.R
import com.mehdik.mvvmdi.databinding.ItemProductBinding
import com.mehdik.mvvmdi.models.Dish

class MainAdapter(
    private val dishList: List<Dish>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    interface OnItemClickListener {
        fun openDish()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainViewHolder {

        // Create a new view, which defines the UI of the list item
        return MainViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = dishList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val dish = dishList[position]
        holder.bind(dish, listener)
    }

    class MainViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var dish: Dish

        fun bind(dish: Dish, listener: OnItemClickListener) {

            this.dish = dish

            //displays product datas
            binding.nameDishText.text = dish.name
            binding.priceDishText.text = itemView.context.getString(R.string.price_euro, dish.price)

            //callback onClick to open the DetailProduct activity
            binding.root.setOnClickListener {
                listener.openDish()
            }
        }
    }
}