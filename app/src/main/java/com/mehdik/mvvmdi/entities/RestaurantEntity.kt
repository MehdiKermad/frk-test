package com.mehdik.mvvmdi.entities

data class RestaurantEntity(
    val `data`: DataEntity,
    val result: Int,
    //val result_cached: Any,
    //val result_code: Any,
    val result_detail: String?,
    val result_msg: String?,
    //val sync: List<Any>
)