package com.example.kotlin.myapplication.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.kotlin.myapplication.api.model.Response
import com.example.kotlin.myapplication.repository.IRepository

class MainActivityViewModel(application: Application,
                            private val repository: IRepository<Response>) : AndroidViewModel(
    application) {
    var resultsListLiveData: MutableLiveData<Response> = MutableLiveData()

    fun doStuff() {
        repository
            .get()
            .observeForever({ response: Response? -> resultsListLiveData.value = response })
    }
}