package com.example.module.product.infrastructure.controllers

import com.example.module.product.infrastructure.Product
import io.ktor.http.*
import kotlinx.coroutines.delay

/**
 * TODO: no implemented
 */
class ProductDeleteController {

    suspend fun delete(id: String): Pair<HttpStatusCode, Product?> {
        delay(1_000)
        return HttpStatusCode.BadRequest to null
    }
}
