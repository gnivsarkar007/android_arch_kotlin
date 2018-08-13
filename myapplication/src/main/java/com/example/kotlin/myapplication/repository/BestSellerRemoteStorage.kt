package com.example.kotlin.myapplication.repository


import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.api.model.Response
import io.reactivex.Observable
import io.reactivex.Single

class BestSellerRemoteStorage(val apiInterface: ApiInterface,
                              val apiKey: String)
    : IRemoteStorage<Response> {
    override fun getSingle(): Single<Response> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(data: List<Response>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(): Observable<Response> {
        return apiInterface.getBestSellerList(apiKey)
    }
}
