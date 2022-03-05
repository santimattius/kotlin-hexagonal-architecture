package com.example.module.product.infrastructure.repositories

import com.example.module.product.infrastructure.Product
import java.util.*
import kotlin.random.Random

object DatabaseEntityManager {

    private val products: List<Product> by lazy {
        (1..10).map {
            Product(
                id = UUID.randomUUID().toString(),
                name = "Product $it",
                price = Random.nextDouble()
            )
        }
    }

    fun products() = products
}