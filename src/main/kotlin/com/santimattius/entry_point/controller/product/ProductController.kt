package com.santimattius.entry_point.controller.product

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.productV1() {
    postProduct()
}

fun Route.postProduct() {

    post("/v1/notification") {
        call.respond(mapOf("hello" to "world"))
    }
}

fun Route.getProduct() {

    get("/v1/product") {
        call.respond(mapOf("hello" to "world"))
    }
}

fun Route.getAllProduct() {

    get("/v1/product/all") {
        call.respond(mapOf("hello" to "world"))
    }
}
