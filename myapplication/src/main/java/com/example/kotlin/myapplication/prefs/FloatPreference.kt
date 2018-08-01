package com.example.kotlin.myapplication.prefs

import com.example.kotlin.myapplication.manager.SharedPrefManager

class FloatPreference(private var data: Float, private var key: String,
                      private var sharedPrefManager: SharedPrefManager) : IPreference<Float> {
    init {
        set(data)
    }
    override fun set(data: Float) {
        this.data = data
        sharedPrefManager.getEditor().putFloat(this.key, data)
    }

    override fun get(): Float {
        return data
    }
}