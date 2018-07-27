package com.example.kotlin.myapplication.prefs

import com.example.kotlin.myapplication.App
import com.example.kotlin.myapplication.manager.SharedPrefManager

class LongPreference(private var data: Long?, private var key: String) : IPreference<Long> {
    private var sharedPrefManager: SharedPrefManager = SharedPrefManager.getInstance(App.context)

    init {
        set(this.data)
    }

    override fun set(data: Long?) {
        this.data = data
        sharedPrefManager.getEditor().putLong(this.key, data!!)
    }

    override fun get(): Long {
        return sharedPrefManager.prefsManager.getLong(this.key, -1L)
    }
}