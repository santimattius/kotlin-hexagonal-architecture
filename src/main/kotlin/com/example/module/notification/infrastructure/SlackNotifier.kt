package com.example.module.notification.infrastructure

import com.example.module.notification.domain.NotificationText
import com.example.module.notification.domain.NotificationType
import com.example.module.notification.domain.Notifier
import com.external.slack.Message
import com.external.slack.SlackClient

class SlackNotifier(
    hookUrl: String,
    setting: Map<String, String>
) : Notifier {

    private val client = SlackClient(hookUrl, setting)

    override suspend fun notify(text: NotificationText, type: NotificationType): Result<String> {
        val message = Message(
            channel = "hardcoded-channel",
            content = text()
        )
        client.send(message)
        return Result.success("Slack")
    }
}