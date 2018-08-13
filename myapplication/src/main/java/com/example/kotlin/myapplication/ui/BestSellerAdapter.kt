package com.example.kotlin.myapplication.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
      holder.bindData(result)

    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun refreshData(newData: List<ResultsItem>?) {
        dataList = newData ?: listOf()
        notifyDataSetChanged()
    }

    class BestSellerVH(view: View) : RecyclerView.ViewHolder(view) {
      private val title: TextView = view.findViewById(R.id.title)
      private val author: TextView = view.findViewById(R.id.author)
      private val publisher: TextView = view.findViewById(R.id.publisher)
      private val rankStatus: ImageView = view.findViewById(R.id.rank)

      fun bindData(result: ResultsItem) {
        title.text = result.title
        author.text = result.author
        publisher.text = result.publisher
        rankStatus.visibility = View.INVISIBLE
//            val currRank = result.ranksHistory!![0].rank
//            val lastRank = result.ranksHistory[1].rank
//
//            when {
//              lastRank == null || currRank == null -> rankStatus.visibility = View.INVISIBLE
//              currRank - lastRank < 0 -> rankStatus.setImageResource(R.drawable.ic_arrow_upward_black_24dp)
//              currRank - lastRank > 0 -> rankStatus.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
//              else ->
//            }
      }
    }
}