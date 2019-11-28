package com.mobile.vigli.onechat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatRecyclerViewAdapter(var chatItems: MutableList<ChatItem>): RecyclerView.Adapter<ChatRecyclerViewAdapter.ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatItems.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.messageTextView.text = chatItems[position].message
    }

    class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.tvMessage)
    }
}