package com.santimattius.entry_point.plugins

import com.santimattius.entry_point.routes.notification.notificationV1
import com.santimattius.entry_point.routes.product.productV1
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        notificationV1()
        productV1()
    }
}
