package com.example.kotlin.myapplication.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : RxSchedulerProvider {
    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun newThread(): Scheduler {
        return Schedulers.newThread()
    }

    override fun trampolineThread(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ioThread(): Scheduler {
        return Schedulers.io()
    }
}