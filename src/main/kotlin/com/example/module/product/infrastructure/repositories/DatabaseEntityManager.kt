package com.example.module.product.infrastructure.repositories

import com.example.module.product.infrastructure.Product
import java.sql.SQLException
import java.util.*
import kotlin.random.Random

object DatabaseEntityManager {

    private val products: MutableList<Product> by lazy {
        (1..10).map {
            Product(
                id = UUID.randomUUID().toString(),
                name = "Product $it",
                price = Random.nextDouble(from = 100.0, until = 1000.0)
            )
        }.toMutableList()
    }

    fun products() = products


    fun find(id: String): Product {
        return products.firstOrNull { it.id == id } ?: throw SQLException()
    }

    fun update(product: Product): Product {
        val index = this.products.indexOfFirst { it.id == product.id }
        return if (index == -1) {
            throw SQLException()
        } else {
            this.products.removeAt(index)
            this.products.add(product)
            product
        }
    }

    fun save(product: Product): Product {
        this.products.add(product)
        return product
    }
}