package com.example.kotlin.myapplication.repository.bestseller

import android.util.Log
import com.example.kotlin.myapplication.database.DaoProvider
import com.example.kotlin.myapplication.domain.ViewState
import com.example.kotlin.myapplication.repository.base.ILocalStorage
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import io.reactivex.Observable

class BestSellerLocalStorage(val bestSellerDaoProvider: DaoProvider) :
    ILocalStorage<ViewState<List<BestSellerViewModel>>> {
    override fun get(): Observable<ViewState<List<BestSellerViewModel>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(data: List<ViewState<List<BestSellerViewModel>>>) {
        Observable
            .fromIterable(data)
            .map { it -> it.data!! }
            .flatMapIterable { d -> d }
            .doOnNext { t: BestSellerViewModel ->
                bestSellerDaoProvider
                    .database
                    .bestSellerDao()
                    .insert(BestSellerEntity.entityFromViewModel(t))
            }
            .doOnComplete { Log.d("TAG", "DATA entered") }
            .doOnError({ Log.d("TAG", "DATA failed" + it.message) })

    }
}