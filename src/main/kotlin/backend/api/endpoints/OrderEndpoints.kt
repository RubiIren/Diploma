package org.example.backend.api.endpoints

import org.example.backend.api.endpoints.headers.Headers
import org.example.backend.api.models.orders.CreateOrderRequest
import org.example.backend.api.models.orders.CreateOrdersResponse
import org.example.backend.api.models.orders.UpdateOrderRequest
import retrofit2.Call
import retrofit2.http.*

interface OrderEndpoints {

    @POST("orders/create")
    fun postCreateOrder(
        @Header(Headers.AUTHORIZATION) token: String,
        @Body body: CreateOrderRequest
    ): Call<CreateOrdersResponse>

    @GET("orders/")
    fun getOrders(
        @Header(Headers.AUTHORIZATION) token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Call<List<CreateOrdersResponse>>

    @GET("orders/{id}")
    fun getOrder(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int
    ): Call<CreateOrdersResponse>

    @GET("orders/user/{id}")
    fun getOrderByUserId(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int
    ): Call<List<CreateOrdersResponse>>

    @PUT("orders/{id}/status")
    fun updateOrder(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int,
        @Body body: UpdateOrderRequest
    ): Call<CreateOrdersResponse>
}