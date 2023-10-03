package com.example.network

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class NewsDetailActivity : AppCompatActivity() {
    private var newslink: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        
        //get data from intent
        val i = intent
        newslink = i.getStringExtra("url")
        val view = findViewById<View>(R.id.webview) as WebView
        view.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }
        view.settings.javaScriptEnabled = true
        view.loadUrl(newslink!!)
    }
}
