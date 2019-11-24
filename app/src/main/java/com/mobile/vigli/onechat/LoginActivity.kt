package com.mobile.vigli.onechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mobile.vigli.onechat.databinding.ActivityLoginConstraintBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginConstraintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_constraint)

        binding.tvAppVersion.text = "(${BuildConfig.VERSION_NAME})"

        binding.btnLogin.setOnClickListener {
            var mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }
    }
}
