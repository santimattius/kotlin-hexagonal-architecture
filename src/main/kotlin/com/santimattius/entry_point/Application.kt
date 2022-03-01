package com.santimattius.entry_point

import com.santimattius.entry_point.plugins.configureKoin
import com.santimattius.entry_point.plugins.configureRouting
import com.santimattius.entry_point.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configure()
    }.start(wait = true)
}

private fun Application.configure() {
    configureKoin()
    configureRouting()
    configureSerialization()
}
