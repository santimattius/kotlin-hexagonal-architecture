package com.example.module.notification.domain


@JvmInline
value class NotificationText(private val value: String) {

    operator fun invoke() = value
}
