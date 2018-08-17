package com.example.kotlin.myapplication.domain

// represents the state of the view, if data is available, was there an error, and a state that the view must show
// depending in this state object. Data class because one object of this class must represent one state only.
data class ViewState<T>(internal var data: T?,
                        internal var error: Throwable?,
                        internal var state: Int)