package com.example.kotlin.myapplication.prefs

interface IPreference<T> {
    fun get(): T
    fun set(data: T?)
}