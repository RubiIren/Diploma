package org.example.backend.api.models.orders

data class CreateOrdersResponse(
    var id: Int,
    var orderStatus: String,
    var products: List<Product>,
    var totalAmount: Double,
    var createdAt: Long,
    var updatedAt: Long
)

data class Product(
    var id: Int,
    var name: String,
    var price: Double,
    var description: String
)