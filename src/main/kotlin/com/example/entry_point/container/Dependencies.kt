package com.example.entry_point.container

import com.example.module.product.application.ProductCatalog
import com.example.module.product.application.ProductCreator
import com.example.module.product.application.ProductSearcher
import com.example.module.product.application.ProductUpdater
import com.example.module.product.domain.ProductRepository
import com.example.module.product.infrastructure.repositories.InMemoryProductRepository
import com.example.module.product.infrastructure.controllers.ProductGetController
import com.example.module.product.infrastructure.controllers.ProductPostController
import org.koin.dsl.module

private val application = module {
    factory { ProductCreator(repository = get<ProductRepository>()) }
    factory { ProductSearcher(repository = get<ProductRepository>()) }
    factory { ProductCatalog(repository = get<ProductRepository>()) }
    factory { ProductUpdater(repository = get<ProductRepository>()) }
}

private val domain = module {

}

private val infrastructure = module {
    single<ProductRepository> { InMemoryProductRepository() }
    factory {
        ProductGetController(
            productSearcher = get<ProductSearcher>(),
            productCatalog = get<ProductCatalog>()
        )
    }
    factory { ProductPostController(productCreator = get<ProductCreator>()) }
}

val dependencies = listOf(application, domain, infrastructure)