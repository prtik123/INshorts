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

private const val TAG = "TopicAdapter_CommTag"

class TopicAdapter(
    private val onItemClicked: (Int) -> Unit,
) : RecyclerView.Adapter<TopicAdapter.VH>() {
    private var newsList = mutableListOf<HeadingImageNewsModel>()
    
    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val desc: TextView = view.findViewById(R.id.desc)
        val image: ImageView = view.findViewById(R.id.image)
        
        init {
            view.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_news, parent, false)
        return VH(view)
    }
    
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.apply {
            desc.text = newsList[position].news
            Glide.with(this.itemView.context).load(newsList[position].imageUrl).into(this.image)
        }
    }
    
    override fun getItemCount(): Int {
        return newsList.size
    }
    
    fun setNewImage(newNews: MutableList<HeadingImageNewsModel>) {
        newsList = newNews
        notifyDataSetChanged()
    }
}