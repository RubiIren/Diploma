package backend.auth

import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.extension.Extensions.Companion.getErrorAsObject
import org.example.backend.api.models.ErrorResponse
import org.example.backend.controllers.Controllers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Authentication")
@Story("Authentication back tests")
@Tags(Tag("regress"), Tag("auth"), Tag("api"))
class LoginTest : Controllers() {

    @Test
    @DisplayName("Проверка работы авторизации")
    @Tag("crit")
    fun testLoginWithValidCredentials() {
        val response = auth.login("fdgfd@mail.re", "12345").getAsObject()

        response.accessToken.length shouldBeGreaterThan 10
        response.refreshToken.length shouldBeGreaterThan 10
    }

    @Test
    @DisplayName("Ошибка авторизации: Invalid email or password")
    fun testLoginInvalidCredentialsError() {
        val response = auth.login("wrong@test.com", "12345").getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "Invalid email or password"
    }

    @Test
    @DisplayName("Ошибка авторизации: Wrong password")
    fun testLoginInvalidPassError() {
        val response = auth.login("test@mail.com", "12345").getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "Wrong password: 123 | 12345"
    }
}