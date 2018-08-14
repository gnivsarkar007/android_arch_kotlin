package com.example.kotlin.myapplication.repository.base

import io.reactivex.Observable
import io.reactivex.Single

interface IRemoteStorage<T> {
    fun get(): Observable<T>
    fun getSingle(): Single<T>
    fun set(data: List<T>)
}