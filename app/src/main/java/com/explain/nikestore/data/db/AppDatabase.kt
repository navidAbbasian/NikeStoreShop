package com.explain.nikestore.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.explain.nikestore.data.Product
import com.explain.nikestore.data.repo.source.ProductLocalDataSource

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductLocalDataSource
}