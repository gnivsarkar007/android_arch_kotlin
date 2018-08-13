package com.example.kotlin.myapplication.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.api.model.ResultsItem

class BestSellerAdapter(private val context: Context, private var dataList: List<ResultsItem>) :
        RecyclerView.Adapter<BestSellerAdapter.BestSellerVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BestSellerVH {
        return BestSellerVH(LayoutInflater.from(context)
                .inflate(R.layout.best_seller_layout, parent, false))
    }

    override fun onBindViewHolder(holder: BestSellerVH, position: Int) {
        val result = dataList[position]
        holder.title.text = result.title
        holder.author.text = result.author
        holder.publisher.text = result.publisher
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun refreshData(newData: List<ResultsItem>?) {
        dataList = newData ?: listOf()
        notifyDataSetChanged()
    }

    class BestSellerVH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val author: TextView = view.findViewById(R.id.author)
        val publisher: TextView = view.findViewById(R.id.publisher)
    }
}