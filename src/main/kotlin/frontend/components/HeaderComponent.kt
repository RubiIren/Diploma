package org.example.frontend.components

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import io.qameta.allure.Step
import okhttp3.internal.wait
import org.example.frontend.helpers.Extensions.Companion.getFirstOrAsserted
import org.example.frontend.helpers.Wrappers.Companion.byDataTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId
import org.openqa.selenium.By

class HeaderComponent {
    private val clickHeaderLinks get() = elements(byDataTestGroup("nav-link"))
    private val txtHeaderTitle get() = element(byDataTestId("nav-link-home"))
    private val avatarUser get() = element(By.ByClassName("avatar"))


    @Step("Нажать на раздел {name} в хедере")
    fun clickLink(name: String): HeaderComponent {
        clickHeaderLinks.getFirstOrAsserted(name).click()
        return this
    }

    @Step("Получить текст на логотипе")
    fun getHeaderTitle(): String {
        return txtHeaderTitle.text
    }

    @Step("Получить список ссылок в хедере")
    fun getHeaderLinks(): List<String> {
        return clickHeaderLinks.map { it.text }
    }

    @Step("Проверить наличие аватара")
    fun checkAvatarUser(): Boolean {
        avatarUser.shouldBe(visible)
        return avatarUser.isDisplayed
    }
}