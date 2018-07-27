package com.example.kotlin.myapplication.prefs

import com.example.kotlin.myapplication.manager.SharedPrefManager

class IntPreference(private var data: Int, private var key: String,
                    private var sharedPrefManager: SharedPrefManager) : IPreference<Int> {

    override fun set(data: Int) {
        this.data = data
        sharedPrefManager.getEditor().putInt(this.key, data)
    }

    override fun get(): Int {
        return data
    }
}