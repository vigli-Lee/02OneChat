/**
 * Created by Vigli on 01,December,2019
 * Kmong.com
 */

package com.mobile.vigli.onechat

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChatDatabaseHelper(context: Context): SQLiteOpenHelper(context, FILE_DB, null, 1)  {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE CHAT_TABLE (" +
                "id INTEGER PRIMERY KEY auto_increment," +
                "message TEXT NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //DB migration
    }

    companion object {
        private const val FILE_DB = "chat_db"
    }
}