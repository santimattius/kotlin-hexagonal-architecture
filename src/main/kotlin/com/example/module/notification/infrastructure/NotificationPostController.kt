package com.example.module.notification.infrastructure

import com.example.module.notification.application.NotificationSender
import com.example.module.notification.domain.NotificationText
import com.example.module.notification.domain.NotificationType

class NotificationPostController(
    private val notificationSender: NotificationSender
) {

    suspend fun post(text: String, type: String): Result<String> {
        return notificationSender(text = NotificationText(text), type = NotificationType.from(type))
    }
}