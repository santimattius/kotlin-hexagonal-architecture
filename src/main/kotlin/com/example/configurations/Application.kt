package com.example.configurations

import com.example.configurations.plugins.configureKoin
import com.example.configurations.plugins.configureRouting
import com.example.configurations.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain


//fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
//        module()
//    }.start(wait = true)
//}

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureKoin()
    configureRouting()
    configureSerialization()
}
