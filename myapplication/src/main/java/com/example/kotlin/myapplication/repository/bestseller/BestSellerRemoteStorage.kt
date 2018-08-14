package com.example.kotlin.myapplication.repository.bestseller


import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.domain.ViewState
import com.example.kotlin.myapplication.repository.base.IRemoteStorage
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import io.reactivex.Observable
import io.reactivex.Single

class BestSellerRemoteStorage(
    val apiInterface: ApiInterface,
    val apiKey: String
) : IRemoteStorage<ViewState<List<BestSellerViewModel>>> {
    override fun getSingle(): Single<ViewState<List<BestSellerViewModel>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(data: List<ViewState<List<BestSellerViewModel>>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(): Observable<ViewState<List<BestSellerViewModel>>> {
        return apiInterface.getBestSellerList(apiKey)
            .map { it -> it.results }
            .flatMapIterable { it -> it }
            .map { item -> BestSellerViewModel.viewModelFromResult(item) }
            .toList()
            .map { t: MutableList<BestSellerViewModel> -> ViewState<List<BestSellerViewModel>>(t, null, 0) }
            .toObservable()
    }
}
