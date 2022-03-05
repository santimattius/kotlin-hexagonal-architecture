package com.example.module.product.infrastructure.repositories

import com.example.module.product.domain.*
import com.example.module.product.infrastructure.asDomainProducts
import com.example.module.product.infrastructure.asEntity
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import com.example.module.product.infrastructure.Product as ProductEntity

/**
 * Adapter
 */
class InMemoryProductRepository : ProductRepository {

    // Mutex to make writes to cached values thread-safe.
    private val latestProductMutex = Mutex()

    // Cache of the latest products
    private var productCached: MutableList<ProductEntity> = mutableListOf()

    override suspend fun all(): List<Product> {
        return latestProductMutex.withLock {
            this.productCached.asDomainProducts()
        }
    }

    override suspend fun find(productId: ProductId): Product? {
        return latestProductMutex.withLock {
            this.productCached
                .firstOrNull { it.id == productId() }
                ?.asDomainProducts()
        }
    }

    override suspend fun save(product: Product): Result<Product> {
        return latestProductMutex.withLock {
            this.productCached.add(product.asEntity())
            Result.success(product)
        }
    }

    override suspend fun update(product: Product): Result<Product> {
        return latestProductMutex.withLock {
            val index = this.productCached.indexOfFirst { it.id == product.id() }
            if (index == -1) {
                Result.failure(IllegalArgumentException())
            } else {
                this.productCached.removeAt(index)
                this.productCached.add(product.asEntity())
                Result.success(product)
            }
        }
    }
}


