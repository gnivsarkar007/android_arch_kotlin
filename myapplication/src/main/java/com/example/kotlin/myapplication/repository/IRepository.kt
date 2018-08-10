package com.example.kotlin.myapplication.repository

import android.arch.lifecycle.LiveData

interface IRepository<T> {
    fun get(): LiveData<T>
    fun set(data: List<T>)
}