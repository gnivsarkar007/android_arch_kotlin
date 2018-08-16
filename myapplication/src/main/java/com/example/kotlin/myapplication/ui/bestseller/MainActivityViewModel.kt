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
    private var responseViewState = ViewState<List<BestSellerViewModel>>(null,
        null,
        Constants.STATE_LOADING)
    fun doStuff() {
        repository
                .get()
                .subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.mainThread())
            .doOnSubscribe {
                resultsListLiveData.value = responseViewState; Log.d("TAG", "Subscribed")
            }
                .doOnComplete { Log.d("TAG", "Completed") }
                .subscribe({ setSuccessObject(it) }, { setErrorObject(it) })
    }

    private fun setSuccessObject(responseList: List<BestSellerViewModel>) {
        this.responseViewState = ViewState(responseList, null, Constants.STATE_SUCCESS)
        this.resultsListLiveData.value = this.responseViewState
    }

    private fun setErrorObject(error: Throwable) {
        this.responseViewState = ViewState(null, error, Constants.STATE_FAILURE)
        this.resultsListLiveData.value = this.responseViewState
    }

}