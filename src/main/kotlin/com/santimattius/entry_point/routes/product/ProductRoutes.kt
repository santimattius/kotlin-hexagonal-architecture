package com.santimattius.entry_point.routes.product

import com.santimattius.entry_point.plugins.inject
import com.santimattius.module.product.infrastructure.Product
import com.santimattius.module.product.infrastructure.controllers.ProductGetController
import com.santimattius.module.product.infrastructure.controllers.ProductPostController
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
            val result = controller.get(id = productId)
            call.respond(result.first, result.second ?: "{}")
        }
    }

    get("/v1/product/all") {
        val result = controller.get()
        call.respond(result.first, result.second)
    }
}

fun Route.postProduct() {

    val controller: ProductPostController by inject()

    post("/v1/product") {
        val product = call.receive<Product>()
        val result = controller.post(id = product.id, name = product.name, price = product.price)
        call.respond(result.first, result.second ?: "{}")
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
