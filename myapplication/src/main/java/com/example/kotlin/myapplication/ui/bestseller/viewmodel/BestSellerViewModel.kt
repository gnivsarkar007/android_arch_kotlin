package com.example.kotlin.myapplication.ui.bestseller.viewmodel

import com.example.kotlin.myapplication.api.model.ResultsItem
import com.example.kotlin.myapplication.repository.bestseller.BestSellerEntity

data class BestSellerViewModel(
    private val _title: String?,
    private val _author: String?,
    private val _publisher: String?
) {
    var title: String? = _title
    var author: String? = _author
    var publisher: String? = _publisher

    companion object {
        fun viewModelFromResult(result: ResultsItem): BestSellerViewModel {
            return BestSellerViewModel(result.title, result.author, result.publisher)
        }

      fun viewModelFromEntity(entity: BestSellerEntity): BestSellerViewModel {
        return BestSellerViewModel(entity.title, entity.author, entity.publisher)
      }
    }

}