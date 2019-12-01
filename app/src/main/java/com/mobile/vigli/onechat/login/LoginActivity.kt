package com.mobile.vigli.onechat.login

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mobile.vigli.onechat.BuildConfig
import com.mobile.vigli.onechat.R
import com.mobile.vigli.onechat.databinding.ActivityLoginBinding
import com.mobile.vigli.onechat.util.SharedPreferenceUtil

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.tvAppVersion.text = "(v${BuildConfig.VERSION_NAME})"

        //로그인 버튼 클릭
        binding.btnLogin.setOnClickListener {
            SharedPreferenceUtil.putIsLogin(this, true)
            setResult(Activity.RESULT_OK)
            finish()
        }

        //게스트 로그인 버튼 클릭
        binding.btnGuest.setOnClickListener {
            SharedPreferenceUtil.putIsLogin(this, false)
            finish()
        }
    }

    companion object {
        const val CODE_REQUEST = 1
    }
}
