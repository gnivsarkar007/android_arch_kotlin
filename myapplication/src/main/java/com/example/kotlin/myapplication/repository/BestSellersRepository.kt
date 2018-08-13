package com.example.kotlin.myapplication.repository

import com.example.kotlin.myapplication.api.model.Response
import io.reactivex.Observable

class BestSellersRepository(private val localStorage: ILocalStorage<Response>?,
                            private val remoteStorage: IRemoteStorage<Response>) : IRepository<Response> {


    override fun get(): Observable<Response> {
        return remoteStorage.get()
    }

    override fun set(data: List<Response>) {
    }

}