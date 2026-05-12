package org.example.backend.controllers

import io.qameta.allure.Step
import org.example.backend.api.endpoints.Endpoints
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.models.orders.CreateOrderRequest
import org.example.backend.api.models.orders.CreateOrdersResponse
import org.example.backend.helpers.AuthorizationHelper
import org.example.backend.helpers.GarbageCollector

class OrdersController : Endpoints() {

    private val authHelper = AuthorizationHelper()

    @Step("Создание заказ")
    fun createOrder(token: String = authHelper.getAdminToken(), body: CreateOrderRequest): CreateOrdersResponse {
        return ordersEndpoints.postCreateOrder(token, body).execute().getAsObject().also {
            GarbageCollector.orders.add(it.id)
        }
    }
}
