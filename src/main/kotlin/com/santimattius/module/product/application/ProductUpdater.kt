package com.santimattius.module.product.application

import com.santimattius.module.product.domain.*

class ProductUpdater(
    private val repository: ProductRepository
) {

    // <editor-fold defaultstate="collapsed" desc="Ejemplo 1">
    private val productSearcher = ProductSearcher(repository)

    suspend fun update(id: ProductId, name: ProductName, price: ProductPrice): Result<Product> {
        val result = productSearcher.search(id)
        return result.fold(
            onSuccess = {
                val productUpdate = it.copy(name = name, price = price)
                repository.update(productUpdate)
                Result.success(productUpdate)
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ejemplo 2">
//    private val productSearcher = ProductSearchService(repository)
//
//    suspend fun update(id: ProductId, name: ProductName, price: ProductPrice): Result<Product> {
//        val result = productSearcher.search(id)
//        return result.fold(
//            onSuccess = {
//                val productUpdate = it.copy(name = name, price = price)
//                repository.update(productUpdate)
//                Result.success(productUpdate)
//            },
//            onFailure = {
//                Result.failure(it)
//            }
//        )
//    }
    // </editor-fold>

}
