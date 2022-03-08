package com.example.entry_point.routes

import com.example.entry_point.plugins.inject
import com.example.module.notification.infrastructure.Notification
import com.example.module.notification.infrastructure.NotificationPostController
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.notificationV1() {
    postNotification()
}

fun Route.postNotification() {

    val controller: NotificationPostController by inject()

    post("/v1/notification") {
        val notification = call.receive<Notification>()
        val result = controller.post(notification)
        call.respondText(result.getOrNull() ?: "Notification not found")
    }
}

