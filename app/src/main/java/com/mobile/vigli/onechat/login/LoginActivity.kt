package com.mobile.vigli.onechat.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.mobile.vigli.onechat.BuildConfig
import com.mobile.vigli.onechat.ChatApplication
import com.mobile.vigli.onechat.R
import com.mobile.vigli.onechat.databinding.ActivityLoginBinding
import com.mobile.vigli.onechat.util.SharedPreferenceUtil

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //파이어베이스 인증 초기화
        auth = (application as ChatApplication).auth

        //구글 로그인 초기화
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.tvAppVersion.text = "(v${BuildConfig.VERSION_NAME})"

        //로그인 버튼 클릭
        binding.btnLogin.setOnClickListener {
            googleSignIn()
        }

        //게스트 로그인 버튼 클릭
        binding.btnGuest.setOnClickListener {
            Toast.makeText(this, "게스트로 로그인 했습니다.", Toast.LENGTH_SHORT).show()
            SharedPreferenceUtil.putIsLogin(this, false)
            finish()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //구글 로그인 성공, 파이어베이스 인증 등록
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "구글 계정으로 로그인 했습니다.", Toast.LENGTH_SHORT).show()
                    (application as ChatApplication).user = auth.currentUser
                    SharedPreferenceUtil.putIsLogin(this, true)
                    finish()
                } else {

                }
            }
    }

    /**
     * 구글 로그인을 한다.
     *
     */
    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    companion object {
        const val CODE_REQUEST = 1
        const val RC_SIGN_IN = 100
    }
}
