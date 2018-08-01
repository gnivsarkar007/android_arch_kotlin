package com.example.kotlin.myapplication.repository

interface ILocalStorage<T> {
    fun get(): List<T>
    fun set(data: List<T>)
}