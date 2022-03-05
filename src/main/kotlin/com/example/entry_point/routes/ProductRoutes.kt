package com.example.entry_point.routes

import com.example.entry_point.plugins.inject
import com.example.module.product.infrastructure.Product
import com.example.module.product.infrastructure.controllers.ProductGetController
import com.example.module.product.infrastructure.controllers.ProductPostController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Routing.productV1() {
    getProduct()
    postProduct()
    putProduct()
    deleteProduct()
}

fun Route.getProduct() {
    val controller: ProductGetController by inject()

    get("/v1/product/{productId}") {

        val productId = call.parameters["productId"]
        if (productId == null) {
            call.respond(HttpStatusCode.BadRequest, "ProductId required")
        } else {
            val (code, response) = controller.get(id = productId)
            call.respond(code, response ?: "{}")
        }
    }

    get("/v1/product/all") {
        val (code, response) = controller.get()
        call.respond(code, response)
    }
}

fun Route.postProduct() {

    val controller: ProductPostController by inject()

    post("/v1/product") {
        val product = call.receive<Product>()
        val (code, response) = controller.post(product)
        call.respond(code, response ?: "{}")
    }
}

fun Route.putProduct() {
    put("/v1/product") {

    }
}

fun Route.deleteProduct() {
    delete("/v1/product") {

    }
}
