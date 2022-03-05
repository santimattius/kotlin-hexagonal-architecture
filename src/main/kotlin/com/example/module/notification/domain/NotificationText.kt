package com.example.module.notification.domain

data class NotificationText(private val value: String) {

    operator fun invoke() = value
}
