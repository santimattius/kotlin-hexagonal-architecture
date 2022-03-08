package com.example.module.product.domain

class ProductSearchService(
    private val repository: ProductRepository
) {

    suspend fun search(id: ProductId): Result<Product> {
        val result = repository.find(id)
        return result.fold(
            onSuccess = { product -> Result.success(product) },
            onFailure = { Result.failure(ProductNotExists(id())) }
        )
    }
}