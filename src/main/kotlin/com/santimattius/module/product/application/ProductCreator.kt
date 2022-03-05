package com.santimattius.module.product.application

import com.santimattius.module.product.domain.*

class ProductCreator(
    private val repository: ProductRepository
) {

    suspend fun create(id: ProductId, name: ProductName, price: ProductPrice): Result<Product> {
        val product = Product.create(id, name, price)
        return repository.save(product)
    }
}