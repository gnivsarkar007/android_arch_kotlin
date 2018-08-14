package com.example.kotlin.myapplication.repository.base

import io.reactivex.Observable

interface ILocalStorage<T> {
    fun get(): Observable<T>
    fun set(data: List<T>)
}