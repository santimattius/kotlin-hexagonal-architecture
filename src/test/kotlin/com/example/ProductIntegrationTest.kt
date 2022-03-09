package com.example

import com.example.module.product.domain.ProductRepository
import com.example.module.product.infrastructure.Product
import com.example.module.product.infrastructure.repositories.InMemoryProductRepository
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProductIntegrationTest {

    @Test
    fun getProducts() = acceptanceTest {

        overrideDefinition {
            single<ProductRepository> { FakeProductRepository() }
        }

        execute { client ->
            val response = client.get("/v1/product/all")
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals(response.data<List<Product>>().isNotEmpty(), true)
        }
    }

    @Test
    fun postProduct() = legacyAcceptanceTest {
        overrideDefinition {
            single<ProductRepository> { InMemoryProductRepository() }
        }

        val response = post("/v1/product") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(parse(Product(name = "Test Product", price = 200.0)).toString())
        }

        assertEquals(HttpStatusCode.Created, response.status())
        assertEquals(200.0, response.data<Product>()?.price)
    }

    @Test
    fun putProduct() = legacyAcceptanceTest {

        overrideDefinition {
            single<ProductRepository> { InMemoryProductRepository() }
        }

        val responsePost = post("/v1/product") {
            header(ContentType.Application.Json)
            setBody(parse(Product(name = "Test Product", price = 200.0)).toString())
        }
        val productCreated = responsePost.data<Product>()
        assertTrue(productCreated != null)

        val productUpdated = productCreated.copy(price = 500.0)

        val responsePut = put("/v1/product") {
            header(ContentType.Application.Json)
            body(productUpdated)
        }

        val productUpdate = responsePut.data<Product>()
        assertTrue(productUpdate != null)
        assertEquals(500.0, productUpdate.price)
    }
}





