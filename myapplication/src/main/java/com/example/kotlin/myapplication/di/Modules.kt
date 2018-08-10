package com.example.kotlin.myapplication.di

import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.api.model.Response
import com.example.kotlin.myapplication.manager.SharedPrefManager
import com.example.kotlin.myapplication.prefs.IntPreference
import com.example.kotlin.myapplication.repository.BestSellerRemoteStorage
import com.example.kotlin.myapplication.repository.BestSellersRepository
import com.example.kotlin.myapplication.repository.IRemoteStorage
import com.example.kotlin.myapplication.repository.IRepository
import com.example.kotlin.myapplication.scheduler.AppSchedulerProvider
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import com.example.kotlin.myapplication.ui.BestSellerAdapter
import com.example.kotlin.myapplication.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val AppModule = applicationContext {
    bean { SharedPrefManager(androidApplication().applicationContext) }
    bean("key_int_pref") { IntPreference(1, "key", sharedPrefManager = get()) }
    bean { ApiInterface.Factory.create() }
    bean("apiKey") { androidApplication().applicationContext.resources.getString(R.string.api_key) }
    factory { AppSchedulerProvider() as RxSchedulerProvider }
    factory { MainActivityViewModel(androidApplication(), repository = get(), schedulerProvider = get()) }
    factory {
        BestSellerRemoteStorage(apiInterface = get(), rxScheduler = get(), apiKey = get("apiKey"))
                as IRemoteStorage<Response>
    }
    factory { BestSellersRepository(null, get()) as IRepository<Response> }
    factory { parameterProvider -> BestSellerAdapter(parameterProvider["activity"], listOf()) }
}