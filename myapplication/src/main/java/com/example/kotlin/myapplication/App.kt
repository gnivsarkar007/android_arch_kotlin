package com.example.kotlin.myapplication

import android.app.Application
import com.example.kotlin.myapplication.di.AppModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(AppModule))
    }
}