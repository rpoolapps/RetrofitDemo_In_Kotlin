package com.ravisingh.retrofitdemo_in_kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.ravisingh.retrofitdemo_in_kotlin.jsonObject.JSONAPIResponse
import com.ravisingh.retrofitdemo_in_kotlin.recycler.RecyclerAdapter
import com.ravisingh.retrofitdemo_in_kotlin.retrofit.JsonAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val data = MutableLiveData<List<JSONAPIResponse>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonApi = retrofit.create(JsonAPI::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

       /* val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager*/


        getData(jsonApi)

        data.observe(this) {
            val adapter = RecyclerAdapter(it)
            recyclerView.adapter = adapter
        }


    }

    fun getData(jsonApi: JsonAPI) {
        CoroutineScope(Dispatchers.IO).launch {
            val responce = jsonApi.getPosts()
            if (responce.isSuccessful) {
                data.postValue(responce.body())
            }
        }
    }
}