package org.example.backend.api.models.products

data class CreateProductResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String
)

