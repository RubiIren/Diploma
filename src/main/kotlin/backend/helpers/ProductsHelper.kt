package org.example.backend.helpers

import io.qameta.allure.Step
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.models.products.CreateProductRequest
import org.example.backend.api.models.products.CreateProductResponse
import org.example.backend.controllers.Controllers

class ProductsHelper : Controllers() {

    @Step("Создание заданного количества продуктов: {count}")
    fun createProducts(count: Int): List<CreateProductResponse> {
        // Создаём изменяемый список для хранения ответов о созданных продуктах
        val listOfProducts = mutableListOf<CreateProductResponse>()

        repeat(count) { index ->
            listOfProducts.add(
                products.createProduct(
                    product = CreateProductRequest(
                        "Product #$index",
                        description = "Description for product #$index",
                        price = index + 1.toDouble()
                    )
                ).getAsObject()
            )
        }

        return listOfProducts.toList()
    }
}