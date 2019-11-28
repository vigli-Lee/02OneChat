package com.mobile.vigli.onechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vigli.onechat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val chatItems = arrayListOf<ChatItem>()
        for (i in 1..100) {
            var chatItem = ChatItem("hello$i")
            chatItems.add(chatItem)
        }

        binding.rvChatting.layoutManager = LinearLayoutManager(this)
        binding.rvChatting.adapter = ChatRecyclerViewAdapter(chatItems)
    }
}
