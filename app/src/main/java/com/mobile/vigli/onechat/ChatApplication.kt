/**
 * Created by Vigli on 09,December,2019
 * Kmong.com
 */

package com.mobile.vigli.onechat

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChatApplication: Application() {
    var user: FirebaseUser? = null
    lateinit var auth: FirebaseAuth

    override fun onCreate() {
        super.onCreate()

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
    }

    /**
     * 로그아웃 한다.
     *
     */
    fun signOut() {
        auth.signOut()
    }


}