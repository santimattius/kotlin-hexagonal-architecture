package com.example.module.product.domain

class ProductNotExists(id: String) : Throwable("Product $id no found")

