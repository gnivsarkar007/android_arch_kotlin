package com.example.kotlin.myapplication.prefs

import com.example.kotlin.myapplication.App
import com.example.kotlin.myapplication.manager.SharedPrefManager

class StringPreference(private var data: String?, private var key: String) : IPreference<String> {
    private var sharedPrefManager: SharedPrefManager = SharedPrefManager.getInstance(App.context)

    init {
        set(this.data)
    }

    override fun set(data: String?) {
        this.data = data
        sharedPrefManager.getEditor().putString(this.key, data!!)
    }

    override fun get(): String {
        return sharedPrefManager.prefsManager.getString(this.key, "")
    }
}