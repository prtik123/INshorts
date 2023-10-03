package com.example.network.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    var title: String,
    var desc: String,
    var image: String,
    var newslink: String,
    var heads: String,
    var time: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var rowId: Long = 0
}