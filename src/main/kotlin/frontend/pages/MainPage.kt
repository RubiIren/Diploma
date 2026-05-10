package org.example.frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step
import org.example.frontend.components.HeaderComponent
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

class MainPage {

    private val txtBannerTitle get() = element(byDataTestId("main-image-text"))

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
}