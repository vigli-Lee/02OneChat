package com.m.vigli.onechat.util

import android.content.Context

object SharedPreferenceUtil {
    /**
     * 로그인 여부를 저장한다.
     *
     * @param context
     * @param isLogin
     */
    fun putIsLogin(context: Context, isLogin: Boolean) {
        var sp = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        var edit = sp.edit()
        edit.putBoolean(KEY_PREF_IS_LOGIN, isLogin)
        edit.commit()
    }

    /**
     * 로그인 여부를 가져온다.
     *
     * @param context
     * @return
     */
    fun getUserLogin(context: Context): Boolean {
        var sp = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return sp.getBoolean(KEY_PREF_IS_LOGIN, false)
    }

    private const val NAME_PREF = "pref"
    private const val KEY_PREF_IS_LOGIN = "key_is_login"
}