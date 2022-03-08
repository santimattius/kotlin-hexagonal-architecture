package com.example.module.product.domain

import com.example.module.shared.isNotEmptyOrNotBlack

data class ProductId(private val value: String) {
    init {
        check(value.isNotEmptyOrNotBlack()) {
            "ProductId is required!!"
        }
    }

    operator fun invoke() = this.value
}

data class ProductName(private val value: String) {
    init {
        check(value.isNotEmptyOrNotBlack()) {
            "ProductName is required!!"
        }
    }

    operator fun invoke() = this.value
}

data class ProductPrice(private val value: Double) {
    init {
        check(value > 0) {
            "Price invalid!"
        }
    }

    operator fun invoke() = this.value
}