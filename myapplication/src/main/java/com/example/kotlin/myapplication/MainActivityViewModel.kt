package com.example.kotlin.myapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.kotlin.myapplication.api.model.ResultsItem

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    protected lateinit var resultsListLiveData: LiveData<List<ResultsItem>>
}