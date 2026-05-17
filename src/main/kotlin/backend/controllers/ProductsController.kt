package org.example.backend.controllers

import io.qameta.allure.Step
import okhttp3.ResponseBody
import org.example.backend.api.endpoints.Endpoints
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.models.products.CreateProductRequest
import org.example.backend.api.models.products.CreateProductResponse
import org.example.backend.helpers.AuthorizationHelper
import org.example.backend.helpers.GarbageCollector
import retrofit2.Response

class ProductsController: Endpoints() {
    val authHelper = AuthorizationHelper()

    @Step("Получение всех продуктов")
    fun getProducts(): Response<List<CreateProductResponse>> {
        return products.getProducts().execute()
    }

    @Step("Получение продуктов с id: {id}")
    fun getProductById(id: Any): Response<CreateProductResponse> {
        return products.getProductById(id).execute()
    }

    @Step("Создание нового продукта")
    fun createProduct(token: String = authHelper.getAdminToken(), product: CreateProductRequest): Response<CreateProductResponse> {
        return products.postCreateProduct(token, product).execute()
            .also { GarbageCollector.products.add(it.getAsObject().id) }
    }

    @Step("Удаление продукта с id: {id}")
    fun deleteProductById(token: String = authHelper.getAdminToken(), id: Any): Response<ResponseBody> {
        return products.deleteProductById(token, id).execute()
    }
}