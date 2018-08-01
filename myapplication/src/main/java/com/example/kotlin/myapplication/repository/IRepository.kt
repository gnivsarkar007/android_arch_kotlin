package com.example.kotlin.myapplication.repository

import io.reactivex.Observable

interface IRepository<T> {
    fun get(): Observable<List<T>>
    fun set(data: List<T>)
}