package com.example.network.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.network.NewsFeedType
import com.example.network.R

private const val TAG = "DiscoverFirstAdapter_CommTag"

class DiscoverFirstAdapter(
    private val onItemClicked: (Int) -> Unit,
) : RecyclerView.Adapter<DiscoverFirstAdapter.VH>() {
    private val iconList = mutableListOf<Int>()
    private val titleList = mutableListOf<String>()
    
    init {
        with(iconList) {
            add(R.drawable.app_logo)
            add(R.drawable.app_logo)
            add(R.drawable.app_logo)
            add(R.drawable.app_logo)
            add(R.drawable.app_logo)
            add(R.drawable.app_logo)
        }
        
        with(titleList) {
            add(NewsFeedType.MY_FEED.type)
            add(NewsFeedType.ALL_NEWS.type)
            add(NewsFeedType.TOP_STORIES.type)
            add(NewsFeedType.TRENDING.type)
            add(NewsFeedType.BOOKMARKS.type)
            add(NewsFeedType.UNREAD.type)
        }
        
    }
    
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
    
    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.icon)
        val title: TextView = view.findViewById(R.id.title)
        
        init {
            view.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_discover_first_rv, parent, false)
        return VH(view)
    }
    
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.apply {
            icon.setImageDrawable(AppCompatResources.getDrawable(this.itemView.context, iconList[position]))
            title.text = titleList[position]
        }
    }
    
    override fun getItemCount(): Int {
        return titleList.size
    }
    
    /*fun setNewLists(drawables: MutableList<Int>, titles: MutableList<String>) {
        if (drawables.size != titles.size) {
            Log.d(TAG, "Cannot set list because drawable list size is not same as title list size.")
            return
        }
        
        iconList.clear()
        iconList.addAll(drawables)
        
        titleList.clear()
        titleList.addAll(titles)
        
        notifyDataSetChanged()
    }*/
    
    /* val type = when (adapterPosition) {
         0 -> NewsFeedType.MY_FEED
         1 -> NewsFeedType.ALL_NEWS
         2 -> NewsFeedType.TOP_STORIES
         3 -> NewsFeedType.TRENDING
         4 -> NewsFeedType.BOOKMARKS
         5 -> NewsFeedType.UNREAD
         else -> throw Exception("Clicked on an unknown news type enum")
     }*/
}