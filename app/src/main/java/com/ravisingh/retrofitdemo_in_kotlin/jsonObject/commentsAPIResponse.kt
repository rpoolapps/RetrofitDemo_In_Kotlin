package com.ravisingh.retrofitdemo_in_kotlin.jsonObject

data class commentsAPIResponse(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)