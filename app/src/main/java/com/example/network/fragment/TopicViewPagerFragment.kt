package com.example.network.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.network.R
import com.example.network.adapter.TopicAdapter
import com.example.network.database.model.HeadingImageNewsModel

const val POSITION = "position"

private const val TAG = "TopicViewPagerFrag_CommTag"

class TopicViewPagerFragment : Fragment() {
    private var pos: Int? = null
    private lateinit var topicRV: RecyclerView
    private lateinit var topicAdapter: TopicAdapter
    private lateinit var divider: DividerItemDecoration
    
    companion object {
        @JvmStatic
        fun newInstance(pos: Int) =
            TopicViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, pos)
                }
            }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pos = it.getInt(POSITION)
        }
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_topic_view_pager, container, false)
    }
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        topicRV = view.findViewById(R.id.topicRV)
        topicRV.addItemDecoration(divider)
        topicAdapter = TopicAdapter(::onItemClicked)
        topicRV.adapter = topicAdapter
        topicRV.layoutManager = LinearLayoutManager(requireContext())
        val list = mutableListOf<HeadingImageNewsModel>()
        list.add(HeadingImageNewsModel("Karnataka Bandh LIVE Updates: TN to appeal SC for 5,000 cusecs of Cauvery water", "https://www.hindustantimes.com/ht-img/img/2023/09/29/550x309/PTI09-29-2023-000061B-0_1695964895465_1695964923224.jpg"))
        list.add(HeadingImageNewsModel("Karnataka Bandh LIVE Updates: TN to appeal SC for 5,000 cusecs of Cauvery water", "https://www.hindustantimes.com/ht-img/img/2023/09/29/550x309/PTI09-29-2023-000061B-0_1695964895465_1695964923224.jpg"))
        list.add(HeadingImageNewsModel("Karnataka Bandh LIVE Updates: TN to appeal SC for 5,000 cusecs of Cauvery water", "https://www.hindustantimes.com/ht-img/img/2023/09/29/550x309/PTI09-29-2023-000061B-0_1695964895465_1695964923224.jpg"))
        list.add(HeadingImageNewsModel("Karnataka Bandh LIVE Updates: TN to appeal SC for 5,000 cusecs of Cauvery water", "https://www.hindustantimes.com/ht-img/img/2023/09/29/550x309/PTI09-29-2023-000061B-0_1695964895465_1695964923224.jpg"))
        
        topicAdapter.setNewImage(list)
        
    }
    
    private fun onItemClicked(pos: Int) {
        val message = "tab pos = ${this.pos}, adapter pos = $pos"
        Log.d(TAG, message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        
    }
    
}