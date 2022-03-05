package com.example.module.notification.infrastructure

import com.example.module.notification.domain.NotificationText
import com.example.module.notification.domain.NotificationType
import com.example.module.notification.domain.Notifier
import com.external.gmail.Email
import com.external.gmail.GmailClient

class EmailNotifier(
    private val user: String,
    private val password: String
) : Notifier {

    private val gmailClient = GmailClient(user, password)

    override suspend fun notify(text: NotificationText, type: NotificationType): Result<String> {
        val email = Email("hardcoded-subject", text())
        gmailClient.send(email)
        return Result.success("Email")
    }
}