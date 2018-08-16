package com.example.kotlin.myapplication.repository.bestseller

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel

@Entity(tableName = "bestseller")
data class BestSellerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "publisher")
    val publisher: String?
) {
    companion object {
        fun entityFromViewModel(viewModel: BestSellerViewModel): BestSellerEntity {
            return BestSellerEntity(null, viewModel.title, viewModel.author, viewModel.publisher)
        }
    }
}