package com.mobile.vigli.onechat.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vigli.onechat.R
import com.mobile.vigli.onechat.databinding.ItemChattingBinding

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
    }

    fun addItem(chatItem: ChatItem) {
        chatItems.add(chatItem)
        notifyItemInserted(chatItems.size - 1)
    }

    class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var binding = DataBindingUtil.bind<ItemChattingBinding>(view)

        init {
            binding!!.root.setOnClickListener {
                Toast.makeText(view.context, "$adapterPosition", Toast.LENGTH_SHORT).show()
            }
        }
    }
}