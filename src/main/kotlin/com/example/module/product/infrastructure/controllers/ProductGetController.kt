package com.example.module.product.infrastructure.controllers

import com.example.module.product.application.ProductCatalog
import com.example.module.product.application.ProductSearcher
import com.example.module.product.domain.ProductId
import com.example.module.product.infrastructure.Product
import com.example.module.product.infrastructure.asDTO
import com.example.module.product.infrastructure.asDTOs
import io.ktor.http.HttpStatusCode

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
                onSuccess = { HttpStatusCode.OK to it.asDTOs() },
                onFailure = { HttpStatusCode.BadRequest to emptyList() }
            )
        } catch (ex: IllegalArgumentException) {
            HttpStatusCode.BadRequest to emptyList()
        }
    }

}


