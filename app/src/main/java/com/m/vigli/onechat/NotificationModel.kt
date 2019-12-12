package com.m.vigli.onechat

data class NotificationModel(val to: String, val notification: Notification) {
    data class Notification(val title: String, val body: String)
}