package com.example.kotlin.myapplication.manager

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.kotlin.myapplication.utils.SingletonHolder

class SharedPrefManager constructor(context: Context) {
    private var prefsManager: SharedPreferences = context.getSharedPreferences("app_data", MODE_PRIVATE);

    fun getEditor() : SharedPreferences.Editor {
        return prefsManager.edit()
    }

    companion object : SingletonHolder<Context, SharedPrefManager>(::SharedPrefManager)
}