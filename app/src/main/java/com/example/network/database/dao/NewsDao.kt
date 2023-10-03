package com.example.network.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.network.database.entity.News

@Dao
interface NewsDao {
    @Insert
    suspend fun insertNews(news: List<News>): List<Long>
    
    @Query("Select * from news")
    suspend fun getAllNews(): List<News>
    
    @Query("delete from news")
    suspend fun deleteAllNews()
}