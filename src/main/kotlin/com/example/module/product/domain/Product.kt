package com.example.module.product.domain

data class Product(
    val id: ProductId,
    val name: ProductName,
    val price: ProductPrice
) {
    companion object {
        /**
         * named constructor
         */
        fun create(id: ProductId, name: ProductName, price: ProductPrice): Product {
            return Product(id, name, price)
        }
    }
}



