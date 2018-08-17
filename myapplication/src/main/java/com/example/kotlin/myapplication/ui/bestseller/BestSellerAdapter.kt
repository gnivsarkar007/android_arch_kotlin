package com.example.kotlin.myapplication.ui.bestseller

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel

class BestSellerAdapter(private val context: Context, private var dataList: MutableList<BestSellerViewModel>) :
        RecyclerView.Adapter<BestSellerAdapter.BestSellerVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BestSellerVH {
        return BestSellerVH(
            LayoutInflater.from(context)
                .inflate(R.layout.best_seller_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BestSellerVH, position: Int) {
        val result = dataList[position]
        holder.bindData(result)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun refreshData(newData: List<BestSellerViewModel>?) {
        newData?.let { dataList.addAll(newData) } ?: dataList.addAll(mutableListOf())
//        dataList.addAll(newData ?: mutableListOf())
        notifyDataSetChanged()
    }

    class BestSellerVH(view: View) : RecyclerView.ViewHolder(view) {
      private val title: TextView = view.findViewById(R.id.title)
      private val author: TextView = view.findViewById(R.id.author)
      private val publisher: TextView = view.findViewById(R.id.publisher)
      private val rankStatus: ImageView = view.findViewById(R.id.rank)

        fun bindData(result: BestSellerViewModel) {
            title.text = result.title
            author.text = result.author
            publisher.text = result.publisher
            rankStatus.visibility = View.INVISIBLE
      }
    }
}