package com.example.kotlin.myapplication.repository.bestseller

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel

@Entity(tableName = "bestseller", primaryKeys = ["title", "author"])
data class BestSellerEntity(
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "author")
        val author: String,
        @ColumnInfo(name = "publisher")
        val publisher: String?
) {
    companion object {
        fun entityFromViewModel(viewModel: BestSellerViewModel): BestSellerEntity {
            return BestSellerEntity(viewModel.title!!, viewModel.author!!, viewModel.publisher)
        }
    }
}