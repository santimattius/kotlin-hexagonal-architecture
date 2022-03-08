package com.example

import com.example.entry_point.configure
import com.example.entry_point.plugins.configureKoin
import com.example.entry_point.plugins.configureRouting
import com.example.entry_point.plugins.configureSerialization
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.koin.dsl.module
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureKoin(listOf(module {
                single {

                }
            }))
            configureRouting()
            configureSerialization()
        }

        val response = client.get("/v1/product/all")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}