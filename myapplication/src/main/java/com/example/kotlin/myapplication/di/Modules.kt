package com.example.kotlin.myapplication.di

import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.database.DaoProvider
import com.example.kotlin.myapplication.domain.ViewState
import com.example.kotlin.myapplication.manager.SharedPrefManager
import com.example.kotlin.myapplication.prefs.IntPreference
import com.example.kotlin.myapplication.repository.base.ILocalStorage
import com.example.kotlin.myapplication.repository.base.IRemoteStorage
import com.example.kotlin.myapplication.repository.base.IRepository
import com.example.kotlin.myapplication.repository.bestseller.BestSellerLocalStorage
import com.example.kotlin.myapplication.repository.bestseller.BestSellerRemoteStorage
import com.example.kotlin.myapplication.repository.bestseller.BestSellersRepository
import com.example.kotlin.myapplication.scheduler.AppSchedulerProvider
import com.example.kotlin.myapplication.scheduler.RxSchedulerProvider
import com.example.kotlin.myapplication.ui.bestseller.BestSellerAdapter
import com.example.kotlin.myapplication.ui.bestseller.MainActivityViewModel
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val AppModule = applicationContext {
    bean { SharedPrefManager(androidApplication().applicationContext) }
    bean("key_int_pref") { IntPreference(1, "key", sharedPrefManager = get()) }
    bean { ApiInterface.Factory.create() }
    bean("apiKey") { androidApplication().applicationContext.resources.getString(R.string.api_key) }
    factory { AppSchedulerProvider() as RxSchedulerProvider }
    factory {
        MainActivityViewModel(
            androidApplication(),
            repository = get(),
            schedulerProvider = get()
        )
    }
    factory {
        BestSellerRemoteStorage(
            apiInterface = get(),
            apiKey = get("apiKey")
        )
                as IRemoteStorage<ViewState<List<BestSellerViewModel>>>
    }

    bean { DaoProvider(androidApplication().applicationContext) }
    factory { BestSellerLocalStorage(get()) as ILocalStorage<ViewState<List<BestSellerViewModel>>> }
    factory { BestSellersRepository(get(), get()) as IRepository<ViewState<List<BestSellerViewModel>>> }
    factory { parameterProvider ->
        BestSellerAdapter(
            parameterProvider["activity"],
            listOf()
        )
    }
}