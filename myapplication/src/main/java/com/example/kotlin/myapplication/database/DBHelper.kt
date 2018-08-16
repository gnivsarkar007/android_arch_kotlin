package com.example.kotlin.myapplication.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.kotlin.myapplication.database.bestseller.BestSellerDao
import com.example.kotlin.myapplication.repository.bestseller.BestSellerEntity

@Database(
        entities = arrayOf(BestSellerEntity::class),
        version = 5
)
abstract class DBHelper : RoomDatabase() {
    abstract fun bestSellerDao(): BestSellerDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, DBHelper::class.java, "newsdb")
                .fallbackToDestructiveMigration()
                .build()
    }
}