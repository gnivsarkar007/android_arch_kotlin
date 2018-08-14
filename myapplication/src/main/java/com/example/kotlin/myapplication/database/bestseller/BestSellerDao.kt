package com.example.kotlin.myapplication.database.bestseller

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.kotlin.myapplication.repository.bestseller.BestSellerEntity

@Dao
interface BestSellerDao {
    @Query("SELECT * from bestsellerentity")
    fun getAll(): List<BestSellerEntity>

    @Insert(onConflict = REPLACE)
    fun insert(bestseller: BestSellerEntity)
}

