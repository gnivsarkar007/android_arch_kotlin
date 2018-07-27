package com.example.kotlin.myapplication.prefs

import com.example.kotlin.myapplication.App
import com.example.kotlin.myapplication.manager.SharedPrefManager

class FloatPreference(private var data: Float?, private var key: String) : IPreference<Float> {
    private var sharedPrefManager: SharedPrefManager = SharedPrefManager.getInstance(App.context)

    init {
        set(this.data)
    }

    override fun set(data: Float?) {
        this.data = data
        sharedPrefManager.getEditor().putFloat(this.key, data!!)
    }

    override fun get(): Float {
        return sharedPrefManager.prefsManager.getFloat(this.key, -1.0f)
    }
}