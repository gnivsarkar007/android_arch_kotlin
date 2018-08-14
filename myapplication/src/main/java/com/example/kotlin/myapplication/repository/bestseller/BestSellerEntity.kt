package com.example.kotlin.myapplication.repository.bestseller

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel

@Entity
data class BestSellerEntity(
    @PrimaryKey(autoGenerate = true)
    private val _id: Int,
    @ColumnInfo(name = "title")
    private val _title: String?,
    @ColumnInfo(name = "author")
    private val _author: String?,
    @ColumnInfo(name = "publisher")
    private val _publisher: String?
) {
    companion object {
        fun entityFromViewModel(viewModel: BestSellerViewModel): BestSellerEntity {
            return BestSellerEntity(-1, viewModel.title, viewModel.author, viewModel.publisher)
        }
    }
}