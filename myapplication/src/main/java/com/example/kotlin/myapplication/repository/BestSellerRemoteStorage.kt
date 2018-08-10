package com.example.kotlin.myapplication.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.api.model.Response
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import io.reactivex.Single

class BestSellerRemoteStorage(val apiInterface: ApiInterface, val rxScheduler: RxSchedulerProvider,
                              val apiKey: String)
    : IRemoteStorage<Response> {

    private var liveData = MutableLiveData<Response>()
    override fun get(): LiveData<Response> {
        return LiveDataReactiveStreams.fromPublisher<Response> { subscriber ->
            apiInterface.getBestSellerList(apiKey)
                    .subscribeOn(rxScheduler.newThread())
                    .doOnComplete({ subscriber.onComplete() })
                    .subscribe({ t: Response? -> subscriber.onNext(t) }, { t -> subscriber.onError(t) })
        }
    }


    override fun getSingle(): Single<Response> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(data: List<Response>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}