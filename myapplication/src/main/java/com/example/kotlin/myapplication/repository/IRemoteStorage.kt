package com.example.kotlin.myapplication.repository

import io.reactivex.Observable
import io.reactivex.Single

interface IRemoteStorage<T> {
    fun get(): Observable<List<T>>
    fun getSingle(): Single<T>
    fun set(data: List<T>)
}