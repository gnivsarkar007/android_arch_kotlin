package com.example.kotlin.myapplication.repository.bestseller

import com.example.kotlin.myapplication.domain.ViewState
import com.example.kotlin.myapplication.repository.base.ILocalStorage
import com.example.kotlin.myapplication.repository.base.IRemoteStorage
import com.example.kotlin.myapplication.repository.base.IRepository
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import io.reactivex.Observable

class BestSellersRepository(
    private val localStorage: ILocalStorage<ViewState<List<BestSellerViewModel>>>?,
    private val remoteStorage: IRemoteStorage<ViewState<List<BestSellerViewModel>>>?
) : IRepository<ViewState<List<BestSellerViewModel>>> {


    override fun get(): Observable<ViewState<List<BestSellerViewModel>>> {
        return remoteStorage!!.get()
            .doOnNext({ data: ViewState<List<BestSellerViewModel>>? -> localStorage?.set(listOf(data).requireNoNulls()) })
            .mergeWith(localStorage?.get())

    }

    override fun set(data: List<ViewState<List<BestSellerViewModel>>>) {
    }

}