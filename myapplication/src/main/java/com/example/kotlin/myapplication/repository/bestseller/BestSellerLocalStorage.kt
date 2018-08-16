package com.example.kotlin.myapplication.repository.bestseller

import android.util.Log
import com.example.kotlin.myapplication.database.bestseller.BestSellerDao
import com.example.kotlin.myapplication.repository.base.ILocalStorage
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import io.reactivex.Flowable
import io.reactivex.Observable

class BestSellerLocalStorage(private val bestSellerDao: BestSellerDao,
                             private val scheduler: RxSchedulerProvider) :
    ILocalStorage<BestSellerEntity, Observable<List<BestSellerViewModel>>> {

    override fun get(): Observable<List<BestSellerViewModel>> {
        return bestSellerDao.getAll()
                .flatMapSingle {
                    Flowable.fromIterable(it).map {
                        BestSellerViewModel.viewModelFromEntity(it)
                    }.toList()
                }
                .toObservable()
    }

    override fun set(vararg data: BestSellerEntity) {
        data.forEach { val id = bestSellerDao.insert(it); Log.d("TAG", "INSERTED ID $id") }
    }
}