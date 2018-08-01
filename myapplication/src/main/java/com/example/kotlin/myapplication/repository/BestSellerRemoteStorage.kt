package com.example.kotlin.myapplication.repository

import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.api.model.ResultsItem
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single

class BestSellerRemoteStorage(val apiInterface: ApiInterface, val rxScheduler: RxSchedulerProvider,
                              val apiKey: String)
    : IRemoteStorage<ResultsItem> {
    override fun get(): Observable<List<ResultsItem>> {
        return apiInterface.getBestSellerList(apiKey)
                .subscribeOn(rxScheduler.newThread())
                .flatMap { resp -> Observable.just(resp.results) }

    }


    override fun getSingle(): Single<ResultsItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(data: List<ResultsItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}