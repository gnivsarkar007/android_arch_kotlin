package com.example.kotlin.myapplication.scheduler

import io.reactivex.Scheduler

interface RxSchedulerProvider {
    fun mainThread(): Scheduler
    fun newThread(): Scheduler
    fun trampolineThread(): Scheduler
    fun ioThread(): Scheduler
}