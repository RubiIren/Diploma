package org.example.frontend.components.popup

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

class AuthPopup {
    private val inputEmail get() = element(byDataTestId("login-email")).find(shadowCss("input"))
    private val inputPassword get() = element(byDataTestId("login-password")).find(shadowCss("input"))
    private val txtError get() = element(byDataTestId("login-error"))
    private val btnLogin get() = element(byDataTestId("login-submit"))

    @Step("Заполнить поле email")
    fun inputEmail(email: String): AuthPopup {
        inputEmail.value = email
        return this
    }

    @Step("Заполнить поле пароль")
    fun inputPassword(password: String): AuthPopup {
        inputPassword.value = password
        return this
    }

    @Step("Получить текст ошибки")
    fun getErrorText(): String {
        txtError.shouldBe(visible)
        return txtError.text
    }

    @Step("Нажать на кнопку Login")
    fun clickLoginBtn(): AuthPopup {
        btnLogin.click()
        return this
    }
}