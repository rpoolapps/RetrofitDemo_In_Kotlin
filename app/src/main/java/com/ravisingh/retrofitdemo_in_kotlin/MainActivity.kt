package com.ravisingh.retrofitdemo_in_kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
    val delete = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonApi = retrofit.create(JsonAPI::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        /* val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
         linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
         recyclerView.layoutManager = linearLayoutManager*/


        //getData(jsonApi)  // For Get Data Request

        //postData(jsonApi) // For Post Data Request

        // putPostRequest(jsonApi) // For Put Request

       // patchPostRequest(jsonApi) // For Patch request

        // deletePost(jsonApi,4) // For Delete request

        // putWithField(jsonApi)

        patchWithField(jsonApi)

        delete.observe(this){
            if (it != null){ // it will return success code as 200 then delete request is successfully done
                 Toast.makeText(this@MainActivity,it.toString(),Toast.LENGTH_SHORT).show()
                 Log.d("RAVI","onCreate: {$it}")
            }
        }

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

    fun putPostRequest(jsonApi: JsonAPI){
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.putPostRequest(4,JSONAPIResponse("This is Body",110,"This is Title",4))
            if (response.isSuccessful){
                response.body()?.let {
                    data.postValue(listOf(it))
                }
            }else{
                Toast.makeText(this@MainActivity,"Response is UnSuccessful",Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun patchPostRequest(jsonApi: JsonAPI){
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.patchPostRequest(4,JSONAPIResponse("This is Body",110," ",4))
            if (response.isSuccessful){
                response.body()?.let {
                    data.postValue(listOf(it))
                }
            }else{
                Toast.makeText(this@MainActivity,"Response is UnSuccessful",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deletePost(jsonApi: JsonAPI,id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.deletePost(id)
            if (response.isSuccessful){
                delete.postValue(response.code())
            }else{ }
        }
    }


    fun putWithField(jsonApi: JsonAPI){
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.putWithField("This is Body",4)
            if (response.isSuccessful){
                response.body()?.let {
                    data.postValue(listOf(it))
                }
            }else{
                Log.d("RAVI","PutPostRequest: ${response.message()}")
            }
        }
    }


    fun patchWithField(jsonApi: JsonAPI){
        CoroutineScope(Dispatchers.IO).launch {
            val response = jsonApi.patchWithField(4,"This is Body")
            if (response.isSuccessful){
                response.body()?.let {
                    data.postValue(listOf(it))
                }
            }else{
                Log.d("RAVI","patchPostRequest: ${response.message()}")
            }
        }
    }


}