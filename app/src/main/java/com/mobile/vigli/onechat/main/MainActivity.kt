package com.mobile.vigli.onechat.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.vigli.onechat.R
import com.mobile.vigli.onechat.databinding.ActivityMainBinding
import com.mobile.vigli.onechat.login.LoginActivity
import com.mobile.vigli.onechat.util.SharedPreferenceUtil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var isLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLogin = SharedPreferenceUtil.getUserLogin(this)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        var chatItems = ArrayList<ChatItem>()
        for (idx in 1..100) {
            var chatItem = ChatItem("메시지 입니다. $idx")
            chatItems.add(chatItem)
        }

        var linearLayoutManager = LinearLayoutManager(this)
        var adapter = MainChatAdapter(chatItems)
        binding.rvChat.layoutManager = linearLayoutManager
        binding.rvChat.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.menuLogin)?.title = if (isLogin) "로그아웃" else "로그인"
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuLogin -> {
                if (isLogin) {
                    //로그아웃 선택
                    item.title = "로그인"
                    SharedPreferenceUtil.putIsLogin(this, false)
                    isLogin = false
                    Toast.makeText(this, "로그아웃 됐습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
