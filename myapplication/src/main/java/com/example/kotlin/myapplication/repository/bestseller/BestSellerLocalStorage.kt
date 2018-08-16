package com.example.kotlin.myapplication.repository.bestseller

import android.util.Log
import com.example.kotlin.myapplication.database.bestseller.BestSellerDao
import com.example.kotlin.myapplication.repository.base.ILocalStorage
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

class BestSellerLocalStorage(private val bestSellerDao: BestSellerDao,
                             private val scheduler: RxSchedulerProvider) :
    ILocalStorage<BestSellerEntity, Observable<List<BestSellerViewModel>>> {

    override fun get(): Observable<List<BestSellerViewModel>> {
        return bestSellerDao.getAll()
            .flatMapIterable { it }
            .map { BestSellerViewModel.viewModelFromEntity(it) }
            .toList().toObservable()
    }

    override fun set(vararg data: BestSellerEntity) {
        data.toObservable()
            .doOnNext { bestSellerDao.insert(it) }
            .doOnNext { Log.d("TAG", "INSERTED " + it.id) }
            .subscribe()
    }
}