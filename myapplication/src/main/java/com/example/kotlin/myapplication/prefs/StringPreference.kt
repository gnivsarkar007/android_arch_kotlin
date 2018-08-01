package com.example.kotlin.myapplication.prefs

import com.example.kotlin.myapplication.manager.SharedPrefManager

class StringPreference(private var data: String, private var key: String,
                       private var sharedPrefManager: SharedPrefManager) : IPreference<String> {
    init {
        set(data)
    }

    override fun set(data: String) {
        this.data = data
        sharedPrefManager.getEditor().putString(this.key, data)
    }

    override fun get(): String {
        return data
    }
}