package com.example.kotlin.myapplication.repository

import com.example.kotlin.myapplication.api.model.ResultsItem
import io.reactivex.Observable

class BestSellersRepository(private val localStorage: ILocalStorage<ResultsItem>?,
                            private val remoteStorage: IRemoteStorage<ResultsItem>) : IRepository<ResultsItem> {
    override fun get(): Observable<List<ResultsItem>> {
        return remoteStorage.get()
    }

    override fun set(data: List<ResultsItem>) {
    }

}