package com.example.kotlin.myapplication

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        lateinit var context: Context
    }

    init {
        context = applicationContext
    }
}