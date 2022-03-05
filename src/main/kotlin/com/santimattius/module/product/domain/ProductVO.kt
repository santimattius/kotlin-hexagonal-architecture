package com.santimattius.module.product.domain

import com.santimattius.module.shared.isNotEmptyOrNotBlack

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
            "price is required!!"
        }
    }

    operator fun invoke() = this.value
}