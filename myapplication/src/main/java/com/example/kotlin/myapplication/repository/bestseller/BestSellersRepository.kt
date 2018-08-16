package com.example.kotlin.myapplication.repository.bestseller

import com.example.kotlin.myapplication.repository.base.ILocalStorage
import com.example.kotlin.myapplication.repository.base.IRemoteStorage
import com.example.kotlin.myapplication.repository.base.IRepository
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import io.reactivex.Observable

class BestSellersRepository(
    private val localStorage: ILocalStorage<BestSellerEntity, Observable<List<BestSellerViewModel>>>?,
    private val remoteStorage: IRemoteStorage<List<BestSellerEntity>, Observable<List<BestSellerViewModel>>>?
) : IRepository<BestSellerEntity, Observable<List<BestSellerViewModel>>> {
    override fun set(vararg data: BestSellerEntity) {
        // possibly write to local storage?
        localStorage?.set(*data)
    }

    override fun get(): Observable<List<BestSellerViewModel>> {
        return localStorage!!.get()
//        remoteStorage!!.get()
//            .doOnNext { it.map { BestSellerEntity.entityFromViewModel(it) }.forEach { set(it) } }
//            .doOnNext { set(it) }
//            .switchMap { localStorage?.get() }

    }

}