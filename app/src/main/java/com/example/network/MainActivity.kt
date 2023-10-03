package com.example.network

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.network.database.AppDatabase
import com.example.network.database.entity.News
import com.example.network.viewModel.MainVM
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "MainActivity_CommTag"

class MainActivity : AppCompatActivity() {
    val sliderItems: MutableList<SliderItems> = ArrayList()
    private val news = mutableListOf<News>()
    private lateinit var mRef: DatabaseReference
    var verticalViewPager: VerticalViewPager? = null
    private lateinit var vm: MainVM
    private lateinit var db: AppDatabase
    
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getDatabase(this)
        
        vm = ViewModelProvider(this)[MainVM::class.java]
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        verticalViewPager = findViewById(R.id.verticalViewPager)
        mRef = FirebaseDatabase.getInstance().getReference("news")
        getNewsFromRealtimeDB()
    }
    
    private fun getNewsFromRealtimeDB() {
        mRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    //add data to array list
                    news.add(
                        News(
                            ds.child("title").getValue(String::class.java)!!,
                            ds.child("desc").getValue(String::class.java)!!,
                            ds.child("imagelink").getValue(String::class.java)!!,
                            ds.child("newslink").getValue(String::class.java)!!,
                            ds.child("head").getValue(String::class.java)!!,
                            ds.child("time").getValue(Long::class.java)!!
                        )
                    )
                }
                // inserting news in room db.
                vm.deleteAndInsertNews(news)
                
                Log.d(TAG, news.toString())
                for (i in news.indices) {
                    //here we add slider items with the images that are store in images array list....
                    sliderItems.add(SliderItems(news[i].image))
                    //we change int to string because now we retrieve image link and save to array list...istead of drwable image
                }
                verticalViewPager!!.adapter = ViewPagerAdapter(this@MainActivity, sliderItems, news, verticalViewPager!!)
                //now add all array list in adapter
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "onCancelled: error getting data from firebase")
            }
        })
    }
}