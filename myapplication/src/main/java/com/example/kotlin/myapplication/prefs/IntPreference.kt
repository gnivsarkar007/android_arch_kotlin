package com.example.kotlin.myapplication.prefs

import com.example.kotlin.myapplication.App
import com.example.kotlin.myapplication.manager.SharedPrefManager

class IntPreference(private var data: Int?, private var key: String) : IPreference<Int> {

    private var sharedPrefManager: SharedPrefManager = SharedPrefManager.getInstance(App.context)

    init {
        set(this.data)
    }

    override fun set(data: Int?) {
        this.data = data
        sharedPrefManager.getEditor().putInt(this.key, data!!)
    }

    override fun get(): Int {
        return sharedPrefManager.prefsManager.getInt(this.key, -1)
    }
}