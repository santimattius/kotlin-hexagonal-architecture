package com.santimattius.entry_point.controller.notification

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.notificationV1() {
    postNotification()
}

fun Route.postNotification() {

    post("/v1/notification") {
        call.respond(mapOf("hello" to "world"))
    }
}

