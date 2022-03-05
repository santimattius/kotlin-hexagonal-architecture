package com.santimattius.module.product.infrastructure.controllers

import com.santimattius.module.product.application.ProductCatalog
import com.santimattius.module.product.application.ProductSearcher
import com.santimattius.module.product.domain.ProductId
import com.santimattius.module.product.infrastructure.Product
import com.santimattius.module.product.infrastructure.asDTO
import com.santimattius.module.product.infrastructure.asDTOs
import io.ktor.http.*

class ProductGetController(
    private val productSearcher: ProductSearcher,
    private val productCatalog: ProductCatalog
) {

    suspend fun get(id: String): Pair<HttpStatusCode, Product?> {
        return try {
            val result = productSearcher.search(
                id = ProductId(id)
            )
            result.fold(
                onSuccess = { HttpStatusCode.Created to it.asDTO() },
                onFailure = { HttpStatusCode.BadRequest to null }
            )
        } catch (ex: IllegalArgumentException) {
            HttpStatusCode.BadRequest to null
        }
    }


    suspend fun get(): Pair<HttpStatusCode, List<Product>> {
        return try {
            val result = productCatalog.list()
            result.fold(
                onSuccess = { HttpStatusCode.Created to it.asDTOs() },
                onFailure = { HttpStatusCode.BadRequest to emptyList() }
            )
        } catch (ex: IllegalArgumentException) {
            HttpStatusCode.BadRequest to emptyList()
        }
    }

}


