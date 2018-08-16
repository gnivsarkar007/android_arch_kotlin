package com.example.kotlin.myapplication.repository.bestseller


import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.api.model.ResultsItem
import com.example.kotlin.myapplication.repository.base.IRemoteStorage
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import io.reactivex.Observable

class BestSellerRemoteStorage(
    val apiInterface: ApiInterface,
    val apiKey: String
) : IRemoteStorage<BestSellerEntity, Observable<List<BestSellerViewModel>>> {
    override fun get(): Observable<List<BestSellerViewModel>> {
        return apiInterface.getBestSellerList(apiKey)
            .map { it.results }
            .flatMapIterable { t: List<ResultsItem> -> t }
            .map { BestSellerViewModel.viewModelFromResult(it) }
            .toList().toObservable()
    }

    override fun set(vararg data: BestSellerEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
