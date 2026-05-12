package org.example.backend.api.endpoints

import org.example.backend.api.endpoints.headers.Headers
import org.example.backend.api.models.orders.CreateOrderRequest
import org.example.backend.api.models.orders.CreateOrdersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderEndpoints {

    @POST("orders/create")
    fun postCreateOrder(
        @Header(Headers.AUTHORIZATION) token: String,
        @Body body: CreateOrderRequest
    ): Call<CreateOrdersResponse>
}