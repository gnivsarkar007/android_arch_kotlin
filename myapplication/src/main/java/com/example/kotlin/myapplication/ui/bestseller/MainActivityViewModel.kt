package com.example.kotlin.myapplication.ui.bestseller

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.kotlin.myapplication.domain.ViewState
import com.example.kotlin.myapplication.repository.base.IRepository
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import com.example.kotlin.myapplication.utils.Constants

class MainActivityViewModel(
    application: Application, private val repository: IRepository<ViewState<List<BestSellerViewModel>>>,
    private val schedulerProvider: RxSchedulerProvider
) : AndroidViewModel(application) {


    internal var resultsListLiveData = MutableLiveData<ViewState<List<BestSellerViewModel>>>()
    private var response = ViewState<List<BestSellerViewModel>>(null, null, Constants.STATE_LOADING)
    fun doStuff() {
        repository
                .get()
                .subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe { resultsListLiveData.value = response; Log.d("TAG", "Subscribed") }
                .doOnComplete { Log.d("TAG", "Completed") }
                .subscribe({ setSuccessObject(it) }, { setErrorObject(it) })
    }

    private fun setSuccessObject(response: ViewState<List<BestSellerViewModel>>) {
        this.response.data = response.data
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