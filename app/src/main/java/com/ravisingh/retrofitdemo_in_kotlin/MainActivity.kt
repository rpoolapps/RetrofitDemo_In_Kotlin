package com.ravisingh.retrofitdemo_in_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ravisingh.retrofitdemo_in_kotlin.jsonObject.JSONAPIResponse
import com.ravisingh.retrofitdemo_in_kotlin.jsonObject.commentsAPIResponse
import com.ravisingh.retrofitdemo_in_kotlin.recycler.CommentsRecyclerAdapter
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
    //val data = MutableLiveData<List<commentsAPIResponse>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonApi = retrofit.create(JsonAPI::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        /* val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
         linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
         recyclerView.layoutManager = linearLayoutManager*/


        //getData(jsonApi)  // For Get Data Request

        postData(jsonApi) // For Post Data Request

        data.observe(this) {
            val adapter = RecyclerAdapter(it)
            //val adapter = CommentsRecyclerAdapter(it)
            recyclerView.adapter = adapter
        }


    }

    /*fun getData(jsonApi: JsonAPI) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.getPostsIDComments(4)
            //val response = jsonApi.getCommentsPostID(4)
            if (response.isSuccessful) {
                data.postValue(response.body())
            }
        }
    }*/

    fun postData(jsonApi: JsonAPI) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.postDataToServer(JSONAPIResponse("This is Json Body",110,"This is Json Title",4))
            if (response.isSuccessful) {
                response.body()?.let {
                    data.postValue(listOf(it))
                }
            }
        }
    }
}