package com.example.kotlin.myapplication.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.kotlin.myapplication.api.model.Response
import com.example.kotlin.myapplication.domain.ViewModelResponse
import com.example.kotlin.myapplication.repository.IRepository
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import com.example.kotlin.myapplication.utils.Constants

class MainActivityViewModel(application: Application, private val repository: IRepository<Response>,
                            private val schedulerProvider: RxSchedulerProvider) : AndroidViewModel(application) {

    var resultsListLiveData = MutableLiveData<ViewModelResponse<Response>>()
    var response = ViewModelResponse<Response>(null, null, Constants.STATE_LOADING)
    fun doStuff() {
        repository
                .get()
                .subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe { resultsListLiveData.value = response; Log.d("TAG", "Subscribed") }
                .doOnComplete { Log.d("TAG", "Completed") }
                .subscribe({ setSuccessObject(it) }, { setErrorObject(it) })
    }

    private fun setSuccessObject(response: Response) {
        this.response.data = response
        this.response.error = null
        this.response.state = Constants.STATE_SUCCESS
        this.resultsListLiveData.value = this.response
    }

    private fun setErrorObject(error: Throwable) {
        this.response.data = null
        this.response.error = error
        this.response.state = Constants.STATE_FAILURE
        this.resultsListLiveData.value = this.response
    }

}