package com.example.network.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.network.database.dao.NewsDao
import com.example.network.database.entity.News

@Database(entities = [News::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "inshotDB").build()
                
                INSTANCE = instance
                return instance
            }
            
        }
        
    }
}