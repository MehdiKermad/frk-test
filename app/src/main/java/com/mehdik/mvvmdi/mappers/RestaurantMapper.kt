package com.mehdik.mvvmdi.mappers

import com.mehdik.mvvmdi.entities.RestaurantEntity
import com.mehdik.mvvmdi.models.Dish
import com.mehdik.mvvmdi.models.Restaurant

//transforms object received from API in a model for our purpose
object RestaurantMapper : EntityMapper<RestaurantEntity, Restaurant> {

    //we keep only the largest img to display
    override fun mapFromEntity(entity: RestaurantEntity): Restaurant {
        return Restaurant(
            avg_rate = entity.data.avg_rate,
            dishList = hardcoded2DishList(
                Pair(entity.data.card_main_1, entity.data.price_card_main_1),
                Pair(entity.data.card_main_2, entity.data.price_card_main_2),
                Pair(entity.data.card_main_3, entity.data.price_card_main_3),
                Pair(entity.data.card_dessert_1, entity.data.price_card_dessert_1),
                Pair(entity.data.card_dessert_2, entity.data.price_card_dessert_2),
                Pair(entity.data.card_dessert_3, entity.data.price_card_dessert_3)
            ),
            chef_name = entity.data.chef_name ?: String(),
            min_price = entity.data.min_price,
            pics_diaporama = PicsDiaporamaMapper.fromEntityList(entity.data.pics_diaporama),
            rate_count = entity.data.rate_count,
            speciality = entity.data.speciality
        )
    }

    fun fromEntityList(initial: List<RestaurantEntity>): List<Restaurant> {
        return initial.map { mapFromEntity(it) }
    }

    //transforms hardcoded dishes/prices to a list
    private fun hardcoded2DishList(vararg dishes: Pair<String, Int>): List<Dish> {

        val dishList = mutableListOf<Dish>()

        for (dish in dishes) {
            dishList.add(Dish(name = dish.first, price = dish.second))
        }

        return dishList
    }
}