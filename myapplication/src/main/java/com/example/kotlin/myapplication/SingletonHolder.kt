package com.example.kotlin.myapplication

open class SingletonHolder<in C, out S>(creator: (C) -> S) {
    private var creator: ((C) -> S)? = creator

    @Volatile private var instance: S? = null

    fun getInstance(arg: C): S {
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