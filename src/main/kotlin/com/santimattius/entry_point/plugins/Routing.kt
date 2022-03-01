package com.santimattius.entry_point.plugins

import com.santimattius.entry_point.controller.notification.notificationV1
import com.santimattius.entry_point.controller.product.productV1
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        notificationV1()
        productV1()
    }
}
