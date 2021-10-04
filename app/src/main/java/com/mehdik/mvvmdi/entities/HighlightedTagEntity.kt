package com.mehdik.mvvmdi.entities

data class HighlightedTagEntity(
    val id: Int,
    val indexable: Boolean,
    val slug: String,
    val text: String,
    val type: String
)