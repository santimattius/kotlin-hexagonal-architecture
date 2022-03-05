package com.santimattius.module.product.infrastructure.controllers

import com.santimattius.module.product.application.ProductCreator
import com.santimattius.module.product.domain.ProductId
import com.santimattius.module.product.domain.ProductName
import com.santimattius.module.product.domain.ProductPrice
import com.santimattius.module.product.infrastructure.Product
import com.santimattius.module.product.infrastructure.asDTO
import io.ktor.http.*

class ProductPostController(private val productCreator: ProductCreator) {

    suspend fun post(id: String, name: String, price: Double): Pair<HttpStatusCode, Product?> {
        return try {
            val result = productCreator.create(
                id = ProductId(id), name = ProductName(name), price = ProductPrice(price)
            )
            result.fold(
                onSuccess = { HttpStatusCode.Created to it.asDTO() },
                onFailure = { HttpStatusCode.BadRequest to null })
        } catch (ex: IllegalArgumentException) {
            HttpStatusCode.BadRequest to null
        }
    }
}


