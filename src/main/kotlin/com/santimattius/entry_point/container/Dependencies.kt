package com.santimattius.entry_point.container

import com.santimattius.module.product.application.ProductCatalog
import com.santimattius.module.product.application.ProductCreator
import com.santimattius.module.product.application.ProductSearcher
import com.santimattius.module.product.application.ProductUpdater
import com.santimattius.module.product.domain.ProductRepository
import com.santimattius.module.product.infrastructure.repositories.InMemoryProductRepository
import com.santimattius.module.product.infrastructure.controllers.ProductGetController
import com.santimattius.module.product.infrastructure.controllers.ProductPostController
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