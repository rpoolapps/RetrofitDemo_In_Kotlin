package com.ravisingh.retrofitdemo_in_kotlin.retrofit

import com.ravisingh.retrofitdemo_in_kotlin.jsonObject.JSONAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonAPI {

    @GET("posts")
    suspend fun getPosts(): Response<List<JSONAPIResponse>>

    /*@GET("posts/{id}")
    suspend fun getPostParticularID(@Path("id") id: Int) : Response<JSONAPIResponse>*/


}