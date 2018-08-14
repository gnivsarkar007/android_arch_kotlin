package com.example.kotlin.myapplication.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.kotlin.myapplication.database.bestseller.BestSellerDao
import com.example.kotlin.myapplication.repository.bestseller.BestSellerEntity

@Database(
    entities = arrayOf(BestSellerEntity::class),
    version = 1
)
abstract class DBHelper : RoomDatabase() {
    abstract fun bestSellerDao(): BestSellerDao
}