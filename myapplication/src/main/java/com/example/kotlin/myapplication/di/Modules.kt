package com.example.kotlin.myapplication.di

import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.manager.SharedPrefManager
import com.example.kotlin.myapplication.prefs.IntPreference
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val AppModule = applicationContext {
    bean { SharedPrefManager.getInstance(androidApplication().applicationContext) }
    bean("key_int_pref") { IntPreference(1, "key", sharedPrefManager = get()) }
    bean { ApiInterface.Factory.create() }
    bean("apiKey") { androidApplication().applicationContext.resources.getString(R.string.api_key) }
}