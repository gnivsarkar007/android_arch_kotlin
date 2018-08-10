package com.example.kotlin.myapplication.repository

import android.arch.lifecycle.LiveData
import io.reactivex.Single

interface IRemoteStorage<T> {
    fun get(): LiveData<T>
    fun getSingle(): Single<T>
    fun set(data: List<T>)
}