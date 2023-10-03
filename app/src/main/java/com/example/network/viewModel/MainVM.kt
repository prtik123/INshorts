package com.example.network.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.database.AppDatabase
import com.example.network.database.entity.News
import kotlinx.coroutines.launch

private const val TAG = "MainVM_CommTag"

class MainVM(app: Application) : AndroidViewModel(app) {
    private val db = AppDatabase.getDatabase(app)
    
    fun insertNewsInRoom(news: List<News>) {
        viewModelScope.launch {
            val rows = db.newsDao().insertNews(news)
            Log.d(TAG, "rows inserted (primary keys) = $rows")
        }
    }
    
    fun deleteAllNews() {
        viewModelScope.launch {
            val rowsDeleted = db.newsDao().deleteAllNews()
            Log.d(TAG, "rows deleted from table  = $rowsDeleted")
        }
    }
    
    fun deleteAndInsertNews(news: List<News>) {
        viewModelScope.launch {
            deleteAllNews()
            insertNewsInRoom(news)
            Log.d(TAG, "deleteAndInsertNews: finished.")
        }
    }
}