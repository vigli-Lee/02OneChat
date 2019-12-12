package com.m.vigli.onechat.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.m.vigli.onechat.R
import com.m.vigli.onechat.databinding.ItemChattingBinding

class MainChatAdapter(var chatItems: ArrayList<ChatItem>): RecyclerView.Adapter<MainChatAdapter.ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_chatting, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatItems.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.binding?.tvMessage?.text = chatItems[position].message
        holder.binding?.tvEmail?.text = chatItems[position].email
    }

    fun addItem(chatItem: ChatItem) {
        chatItems.add(chatItem)
        notifyItemInserted(chatItems.size - 1)
    }

    inner class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var binding = DataBindingUtil.bind<ItemChattingBinding>(view)
    }
}