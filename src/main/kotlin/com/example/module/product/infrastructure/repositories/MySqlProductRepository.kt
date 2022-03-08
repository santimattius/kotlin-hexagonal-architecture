package com.example.module.product.infrastructure.repositories

import com.example.module.product.domain.Product
import com.example.module.product.domain.ProductId
import com.example.module.product.domain.ProductNotExists
import com.example.module.product.domain.ProductRepository
import com.example.module.product.infrastructure.asDomainProduct
import com.example.module.product.infrastructure.asEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.sql.SQLException

/**
 * Adapter
 */
class MySqlProductRepository : ProductRepository {

    // Mutex to make writes to cached values thread-safe.
    private val latestProductMutex = Mutex()

    override suspend fun all(): List<Product> {
        return latestProductMutex.withLock {
            timeSimulate()
            DatabaseEntityManager.products().asDomainProduct()
        }
    }

    override suspend fun find(productId: ProductId): Result<Product> {
        return latestProductMutex.withLock {
            try {
                timeSimulate()
                val product = DatabaseEntityManager.find(id = productId())
                Result.success(product.asDomainProduct())
            } catch (ex: SQLException) {
                Result.failure(ProductNotExists(id = productId()))
            }
        }
    }

    override suspend fun save(product: Product): Result<Product> {
        return latestProductMutex.withLock {
            timeSimulate()
            val productSaved = DatabaseEntityManager.save(product.asEntity())
            Result.success(productSaved.asDomainProduct())
        }
    }

    override suspend fun update(product: Product): Result<Product> {
        return latestProductMutex.withLock {
            try {
                timeSimulate()
                val productUpdated = DatabaseEntityManager.update(product.asEntity())
                Result.success(productUpdated.asDomainProduct())
            } catch (ex: SQLException) {
                Result.failure(ProductNotExists(id = product.id()))
            }
        }
    }

    private suspend fun timeSimulate() = delay(2_000)
}


