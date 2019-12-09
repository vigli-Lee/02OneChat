package com.mobile.vigli.onechat.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.mobile.vigli.onechat.ChatApplication
import com.mobile.vigli.onechat.ChatDatabaseHelper
import com.mobile.vigli.onechat.R
import com.mobile.vigli.onechat.databinding.ActivityMainBinding
import com.mobile.vigli.onechat.login.LoginActivity
import com.mobile.vigli.onechat.util.SharedPreferenceUtil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainChatAdapter

    private lateinit var email: String
    private var isLogin = false

    private lateinit var myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLogin = SharedPreferenceUtil.getUserLogin(this)
        if (isLogin) email = (application as ChatApplication).user!!.email!!

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        //init db
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("one_chat")

        var chatItems = getChatItemsInDatabase()

        //init and setting recycler view
        var linearLayoutManager = LinearLayoutManager(this)
        adapter = MainChatAdapter(chatItems)
        binding.rvChat.layoutManager = linearLayoutManager
        binding.rvChat.adapter = adapter

        //보내기 버튼
        binding.btnSend.setOnClickListener {
            if (isLogin) {
                var message = binding.etInput.text.toString()
                //초기화
                binding.etInput.text.clear()

                var chatItem = ChatItem(message, email)
                addItem(chatItem)
            }
            else Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show()
        }

        //입력값에 따라 보내기 버튼 상태 변경
        binding.etInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnSend.isEnabled = !p0.isNullOrEmpty()
            }
        })
    }

    /**
     * 메시지를 추가한다.
     *
     * @param chatItem
     */
    private fun addItem(chatItem: ChatItem) {
        //insert db
        insertChatItemInDatabase(chatItem)

        //add RecyclerView
        adapter.addItem(chatItem)
        binding.rvChat.smoothScrollToPosition(adapter.itemCount - 1)
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

                    //파이어베이스 로그아웃
                    (application as ChatApplication).signOut()
                    Toast.makeText(this, "로그아웃 됐습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    //로그인 선택
                    var startLoginActivity = Intent(this, LoginActivity::class.java)
                    startActivityForResult(startLoginActivity, LoginActivity.CODE_REQUEST)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LoginActivity.CODE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "로그인 했습니다.", Toast.LENGTH_SHORT).show()
                    email = (application as ChatApplication).user!!.email!!
                }
            }
        }
    }

    private fun getChatItemsInDatabase(): ArrayList<ChatItem> {
        var chatItems = ArrayList<ChatItem>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(ChatItem::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })

        return chatItems
    }

    private fun insertChatItemInDatabase(chatItem: ChatItem) {
        myRef.setValue(chatItem)
    }
}
