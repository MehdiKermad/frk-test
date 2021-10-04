package com.mehdik.mvvmdi.models

class Restaurant(
    val avg_rate: Double,
    val dishList: List<Dish>,
    val chef_name: String,
    val min_price: Int,
    val pics_diaporama: List<PicsDiaporama>,
    val rate_count: Int,
    val speciality: String
)