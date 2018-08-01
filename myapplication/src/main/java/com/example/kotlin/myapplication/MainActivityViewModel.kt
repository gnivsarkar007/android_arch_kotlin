package com.example.kotlin.myapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.kotlin.myapplication.api.model.ResultsItem
import com.example.kotlin.myapplication.repository.IRepository
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider

class MainActivityViewModel(application: Application, private val repository: IRepository<ResultsItem>,
                            private val schedulerProvider: RxSchedulerProvider) : AndroidViewModel(application) {
    var resultsListLiveData: MutableLiveData<List<ResultsItem>> = MutableLiveData()

    fun doStuff() {
        repository
                .get()
                .observeOn(schedulerProvider.mainThread())
                .doOnNext { t: List<ResultsItem>? -> Log.d("TAG", "data size " + t?.size) }
                .subscribe({ results: List<ResultsItem>? -> resultsListLiveData.value = results }, { err -> })
    }
}