package org.example.frontend.pages

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import io.qameta.allure.Step
import org.example.frontend.components.list.ProductItem
import org.example.frontend.components.list.ProductsItems
import org.example.frontend.helpers.Wrappers.Companion.byTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byTestId

class ProductsPage {
    private val txtTitle get() = element(byTestId("products-title"))
    private val listItems get() = elements(byTestGroup("product-card"))


    @Step("Получить название страницы")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Получить список товаров")
    fun getProducts(): List<ProductItem> {
        return ProductsItems(listItems).getItems()
    }
}
