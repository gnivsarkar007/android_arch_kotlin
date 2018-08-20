package com.example.kotlin.myapplication.ui.bestseller

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.kotlin.myapplication.domain.ViewState
import com.example.kotlin.myapplication.repository.base.IRepository
import com.example.kotlin.myapplication.repository.bestseller.BestSellerEntity
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import com.example.kotlin.myapplication.utils.Constants
import io.reactivex.Observable

class MainActivityViewModel(
    application: Application,
    private val repository: IRepository<BestSellerEntity, Observable<List<BestSellerViewModel>>>,
    private val schedulerProvider: RxSchedulerProvider
) : AndroidViewModel(application) {

    internal var resultsListLiveData = MutableLiveData<ViewState<List<BestSellerViewModel>>>()
    internal var currentState: Int = Constants.STATE_LOADING
    fun doStuff() {
        repository
                .get()
                .subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe {
                    resultsListLiveData.value = ViewState(null,
                                                          null,
                                                          Constants.STATE_LOADING)
                    Log.d("TAG", "Subscribed")
                }
                .doOnComplete { Log.d("TAG", "Completed") }
                .subscribe({ setSuccessObject(it) }, { setErrorObject(it) })
    }

    private fun setSuccessObject(responseList: List<BestSellerViewModel>?) {
        currentState = Constants.STATE_SUCCESS
        this.resultsListLiveData.value = ViewState(responseList, null, currentState)
    }

    private fun setErrorObject(error: Throwable) {
        currentState = Constants.STATE_FAILURE
        this.resultsListLiveData.value = ViewState(null, error, currentState)
    }

}