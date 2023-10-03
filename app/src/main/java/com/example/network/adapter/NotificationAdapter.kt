package com.example.network.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.network.R
import com.example.network.database.model.HeadingImageNewsModel

class NotificationAdapter(
    private val onNotificationClicked: (Int) -> Unit,
) : RecyclerView.Adapter<NotificationAdapter.VH>() {
    private val notifications = mutableListOf<HeadingImageNewsModel>()
    
    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val news: TextView = view.findViewById(R.id.desc)
        val image: ImageView = view.findViewById(R.id.image)
        
        init {
            view.setOnClickListener {
                onNotificationClicked(adapterPosition)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapter.VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_news, parent, false)
        return VH(view)
    }
    
    override fun getItemCount() = notifications.size
    
    override fun onBindViewHolder(holder: NotificationAdapter.VH, position: Int) {
        holder.apply {
            news.text = notifications[position].news
            Glide.with(holder.itemView.context).load(notifications[position].imageUrl).into(image)
        }
    }
    
    fun setNotificationList(notification: List<HeadingImageNewsModel>) {
        notifications.clear()
        notifications.addAll(notification)
        notifyDataSetChanged()
    }
}

