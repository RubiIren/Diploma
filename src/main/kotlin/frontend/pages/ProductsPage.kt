package org.example.frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.*
import io.qameta.allure.Step
import org.example.frontend.components.list.ProductItem
import org.example.frontend.components.list.ProductItems
import org.example.frontend.helpers.Wrappers.Companion.byTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byTestId

class ProductsPage {
    private val txtTitle get() = element(byTestId("products-title"))
    private val listItems get() = elements(byTestGroup("product-card"))
    private val listProducts get() = ProductItems().getItems()

    @Step("Открыть страницу продуктов")
    fun open(): ProductsPage {
        open("/products")
        return this
    }

    @Step("Получить название страницы")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Получить список продуктов на странице")
    fun getProducts(): ElementsCollection {
        return listItems
    }

    @Step("Получить список продуктов на странице в виде объектов")
    fun getProductsAsObjects(): List<ProductItem> {
        return listProducts
    }
}
