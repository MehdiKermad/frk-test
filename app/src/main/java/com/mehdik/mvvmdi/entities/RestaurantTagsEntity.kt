package com.mehdik.mvvmdi.entities

data class RestaurantTagsEntity(
    val category_name: String,
    val category_pic: String,
    val id_restaurant_tag_category: Int,
    val tag_list: Map<String, TagListEntity>
)