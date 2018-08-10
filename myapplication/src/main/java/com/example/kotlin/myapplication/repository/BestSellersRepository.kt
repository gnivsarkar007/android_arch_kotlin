package com.example.kotlin.myapplication.repository

import android.arch.lifecycle.LiveData
import com.example.kotlin.myapplication.api.model.Response

class BestSellersRepository(private val localStorage: ILocalStorage<Response>?,
                            private val remoteStorage: IRemoteStorage<Response>) : IRepository<Response> {


    override fun get(): LiveData<Response> {
        return remoteStorage.get()
    }

    override fun set(data: List<Response>) {
    }

}