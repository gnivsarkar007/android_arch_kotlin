package com.example.kotlin.myapplication.utils

open class SingletonHolder<in I, out O>(creator: (I) -> O) {
    private var creator: ((I) -> O)? = creator

    @Volatile
    private var instance: O? = null

    fun getInstance(arg: I): O {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}