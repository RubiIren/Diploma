package org.example.backend.controllers

import io.qameta.allure.Step
import org.example.backend.api.endpoints.Endpoints
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.models.orders.CreateOrderRequest
import org.example.backend.api.models.orders.CreateOrdersResponse
import org.example.backend.api.models.orders.UpdateOrderRequest
import org.example.backend.helpers.AuthorizationHelper
import org.example.backend.helpers.GarbageCollector

class OrdersController : Endpoints() {

    private val authHelper = AuthorizationHelper()

    @Step("Создание заказ")
    fun createOrder(token: String = authHelper.getAdminToken(), body: CreateOrderRequest): CreateOrdersResponse {
        return orders.postCreateOrder(token, body).execute().getAsObject().also {
            GarbageCollector.orders.add(it.id)
        }
    }

    @Step("Получить все заказы")
    fun getOrders(
        token: String = authHelper.getAdminToken(),
        offset: Int = 0,
        limit: Int = 100
    ): List<CreateOrdersResponse> {
        return orders.getOrders(token, offset, limit).execute().getAsObject()
    }

    @Step("Получить заказ по Id заказа")
    fun getOrderById(
        token: String = authHelper.getAdminToken(),
        id: Int
    ): CreateOrdersResponse {
        return orders.getOrder(token, id).execute().getAsObject()
    }

    @Step("Получить все заказы пользователя по Id пользователя")
    fun getOrderByUserId(
        token: String = authHelper.getAdminToken(),
        id: Int
    ): List<CreateOrdersResponse> {
        return orders.getOrderByUserId(token, id).execute().getAsObject()
    }

    @Step("Изменить статус заказа")
    fun updateOrderById(
        token: String = authHelper.getAdminToken(),
        id: Int,
        body: UpdateOrderRequest
    ): CreateOrdersResponse {
        return orders.updateOrder(token, id, body).execute().getAsObject()
    }
}