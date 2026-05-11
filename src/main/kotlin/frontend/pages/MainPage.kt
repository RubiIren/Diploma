package org.example.frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import io.qameta.allure.Step
import org.example.frontend.components.HeaderComponent
import org.example.frontend.components.list.ProductItem
import org.example.frontend.components.list.ProductsItems
import org.example.frontend.helpers.Wrappers.Companion.byTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byTestId

class MainPage {

    private val txtBannerTitle get() = element(byTestId("main-image-text"))
    private val listPopularProducts get() = elements(byTestGroup("product-card"))

    @Step("Открыть главную страницу")
    fun open() {
        Selenide.open("/")
    }

    @Step("Получить текст на баннере")
    fun getBannerTitle(): String {
        return txtBannerTitle.text
    }

    @Step("Перейти к хедеру")
    fun navigateHeader(): HeaderComponent {
        return HeaderComponent()
    }

    @Step("Получить список популярных товаров")
    fun getPopularProducts(): List<ProductItem> {
        return ProductsItems(listPopularProducts).getItems()
    }
}