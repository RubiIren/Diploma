package org.example.frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step
import org.example.frontend.components.HeaderComponent
import org.example.frontend.components.list.ProductItem
import org.example.frontend.components.list.ProductItems
import org.example.frontend.helpers.Wrappers.Companion.byTestId


class MainPage {

    private val txtBannerTitle get() = element(byTestId("main-image-text"))
    private val listPopularProducts get() = ProductItems().getItems()


    @Step("Открыть главную страницу")
    fun open(): MainPage {
        Selenide.open("/")
        return this
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
        return listPopularProducts
    }

}