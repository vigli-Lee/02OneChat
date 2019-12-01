package com.mobile.vigli.onechat.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mobile.vigli.onechat.BuildConfig
import com.mobile.vigli.onechat.main.MainActivity
import com.mobile.vigli.onechat.R
import com.mobile.vigli.onechat.databinding.ActivityLoginBinding
import com.mobile.vigli.onechat.util.SharedPreferenceUtil

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.tvAppVersion.text = "(v${BuildConfig.VERSION_NAME})"

        if (SharedPreferenceUtil.getUserLogin(this)) {
            startMainActivityAndFinish(true)
        } else {
            //로그인 버튼 클릭
            binding.btnLogin.setOnClickListener {
                setResult(Activity.RESULT_OK)
                startMainActivityAndFinish(true)
            }

            //게스트 로그인 버튼 클릭
            binding.btnGuest.setOnClickListener {
                startMainActivityAndFinish(false)
            }
        }
    }

    private fun startMainActivityAndFinish(isLogin: Boolean) {
        SharedPreferenceUtil.putIsLogin(this, isLogin)
        //로그인 엑티비티 -> 메인 엑티비티를 실행한다.
        var startMainActivityIntent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(startMainActivityIntent)
        //로그인 엑티비티를 종료한다.
        finish()
    }
}
