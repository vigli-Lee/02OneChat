package com.mobile.vigli.onechat.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mobile.vigli.onechat.BuildConfig
import com.mobile.vigli.onechat.MainActivity
import com.mobile.vigli.onechat.R
import com.mobile.vigli.onechat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.tvAppVersion.text = "(v${BuildConfig.VERSION_NAME})"

        binding.btnLogin.setOnClickListener {
            //로그인 엑티비티 -> 메인 엑티비티를 실행한다.
            var startMainActivityIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(startMainActivityIntent)
            //로그인 엑티비티를 종료한다.
            finish()
        }


        binding.btnGuest.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                //로그인 엑티비티 -> 메인 엑티비티를 실행한다.
                var startMainActivityIntent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(startMainActivityIntent)
                //로그인 엑티비티를 종료한다.
                finish()
            }
        })
    }
}
