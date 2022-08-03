package com.ravisingh.retrofitdemo_in_kotlin.jsonObject

data class JSONAPIResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)