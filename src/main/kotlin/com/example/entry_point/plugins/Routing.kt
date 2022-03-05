package com.example.entry_point.plugins

import com.example.entry_point.routes.notificationV1
import com.example.entry_point.routes.productV1
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        notificationV1()
        productV1()
    }
}
