package com.mehdik.mvvmdi.repositories

import com.mehdik.mvvmdi.mappers.RestaurantMapper
import com.mehdik.mvvmdi.models.Restaurant
import com.mehdik.mvvmdi.utils.ApiService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    private var mRestaurant: Restaurant? = null //restaurant received from the API

    fun getRestaurant(id: Int): Observable<Restaurant> {

        mRestaurant?.let {
            return Observable.just(it)
        } ?: run {
            //if it's the first search, we call the API to get result
            return apiService.getRestaurant(id)
                .map {
                    mRestaurant = RestaurantMapper.mapFromEntity(it) //save search results in a var
                    mRestaurant!!
                }
        }
    }
}