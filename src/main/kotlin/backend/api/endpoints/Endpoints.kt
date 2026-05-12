package org.example.backend.api.endpoints

import org.example.backend.api.RetrofitClient

open class Endpoints {
    protected val authEndpoints: AuthEndpoints by lazy { RetrofitClient.createService(AuthEndpoints::class.java) }
    protected val usersEndpoints: UsersEndpoints by lazy { RetrofitClient.createService(UsersEndpoints::class.java) }
    protected val ordersEndpoints: OrderEndpoints by lazy { RetrofitClient.createService(OrderEndpoints::class.java) }
    protected val productsEndpoints: ProductsEndpoints by lazy { RetrofitClient.createService(ProductsEndpoints::class.java) }
}