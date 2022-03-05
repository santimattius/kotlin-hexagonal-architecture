package com.santimattius.module.product.infrastructure

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double
)
