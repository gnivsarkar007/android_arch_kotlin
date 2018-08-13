package com.example.kotlin.myapplication.repository

import io.reactivex.Observable

interface IRepository<T> {
    fun get(): Observable<T>
    fun set(data: List<T>)
}