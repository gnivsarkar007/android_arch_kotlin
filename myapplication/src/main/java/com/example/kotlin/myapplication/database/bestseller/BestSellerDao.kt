package com.example.kotlin.myapplication.database.bestseller

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.kotlin.myapplication.repository.bestseller.BestSellerEntity
import io.reactivex.Flowable

@Dao
interface BestSellerDao {
    @Query("SELECT * from bestseller")
    fun getAll(): Flowable<List<BestSellerEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(bestseller: BestSellerEntity): Long
}

