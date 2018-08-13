package com.example.kotlin.myapplication.domain

class ViewModelResponse<T>(internal var data: T?, internal var error: Throwable?, internal var state: Int) {
}