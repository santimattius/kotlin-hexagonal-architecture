package com.example.module.product.domain

class ProductSearchService(
    private val repository: ProductRepository
) {

    suspend fun search(id: ProductId): Result<Product> {
        val product = repository.find(id)
        return if (product == null) {
            Result.failure(IllegalArgumentException())
        } else {
            Result.success(product)
        }
    }
}