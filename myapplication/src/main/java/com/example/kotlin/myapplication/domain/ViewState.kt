package com.example.kotlin.myapplication.domain

class ViewState<T>(internal var data: T?, internal var error: Throwable?, internal var state: Int) {
}