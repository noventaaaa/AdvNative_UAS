package com.example.hobbyapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news: News)

    @Query("SELECT * FROM news")
    fun selectAllNews(): List<News>

    @Delete
    fun deleteNews(news:News)
}