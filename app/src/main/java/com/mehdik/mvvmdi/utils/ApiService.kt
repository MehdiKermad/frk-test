package com.mehdik.mvvmdi.utils

import com.mehdik.mvvmdi.entities.RestaurantEntity
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //provides a restaurant detail
    @GET("api?key=IPHONEPRODEDCRFV&method=restaurant_get_info")
    fun getRestaurant(@Query("id_restaurant") id: Int): Observable<RestaurantEntity>
}