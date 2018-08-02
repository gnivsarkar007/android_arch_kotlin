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
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BestSellerVH {
        return BestSellerVH(LayoutInflater.from(context)
                .inflate(R.layout.best_seller_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: BestSellerVH?, position: Int) {
        holder?.title?.text = dataList[position].title
    }

    fun refreshData(newData: List<ResultsItem>) {
        dataList = newData
        notifyDataSetChanged()
    }

    data class BestSellerVH(private val view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
    }
}