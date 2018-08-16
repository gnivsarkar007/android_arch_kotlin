package com.example.kotlin.myapplication.domain

data class ViewState<T>(internal var data: T?,
                        internal var error: Throwable?,
                        internal var state: Int)