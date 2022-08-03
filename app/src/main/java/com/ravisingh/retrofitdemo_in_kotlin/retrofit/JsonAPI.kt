package com.ravisingh.retrofitdemo_in_kotlin.retrofit

import com.ravisingh.retrofitdemo_in_kotlin.jsonObject.JSONAPIResponse
import com.ravisingh.retrofitdemo_in_kotlin.jsonObject.commentsAPIResponse
import retrofit2.Response
import retrofit2.http.*

interface JsonAPI {

    //https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    suspend fun getPosts(): Response<List<JSONAPIResponse>>

    //https://jsonplaceholder.typicode.com/posts/1
    @GET("posts/{id}")
    suspend fun getPostParticularID(@Path("id") id: Int) : Response<JSONAPIResponse>

    //https://jsonplaceholder.typicode.com/posts/1/comments
    @GET("posts/{id}/comments")
    suspend fun getPostsIDComments(@Path("id") id: Int) : Response<List<JSONAPIResponse>>

    //https://jsonplaceholder.typicode.com/comments?postId=1
    @GET("comments")
    suspend fun getCommentsPostID(@Query("posts") postID: Int) : Response<List<commentsAPIResponse>>


    @POST("posts")
    suspend fun postDataToServer(@Body jsonapiResponse: JSONAPIResponse) : Response<JSONAPIResponse>

}