package com.ravisingh.retrofitdemo_in_kotlin.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ravisingh.retrofitdemo_in_kotlin.R
import com.ravisingh.retrofitdemo_in_kotlin.jsonObject.commentsAPIResponse

class CommentsRecyclerAdapter(val list: List<commentsAPIResponse>) : RecyclerView.Adapter<CommentsRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)
        val id = view.findViewById<TextView>(R.id.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsRecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsRecyclerAdapter.MyViewHolder, position: Int) {
        holder.title.text = list[position].name
        holder.description.text = list[position].body
        holder.id.text = list[position].id.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

