package com.example.kotlin.myapplication.database

import android.arch.persistence.room.Room
import android.content.Context

class DaoProvider(context: Context) {
    var database: DBHelper

    init {
        database = Room.databaseBuilder(context.applicationContext, DBHelper::class.java, "news.db").build()
    }
}