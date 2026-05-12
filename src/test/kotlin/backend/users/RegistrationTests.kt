package backend.users

import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.extension.Extensions.Companion.getErrorAsObject
import org.example.backend.api.models.ErrorResponse
import org.example.backend.api.models.users.CreateUserRequest
import org.example.backend.api.models.users.defaultUser
import org.example.backend.controllers.Controllers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Registration")
@Story("Registration tests")
@Tags(Tag("registration"), Tag("regress"), Tag("api"))
class RegistrationTest : Controllers() {

    @Test
    @DisplayName("Проверка создания пользователя")
    @Tag("crit")
    fun testCreateUser() {
        val response = users.createUser(defaultUser).getAsObject()

        val login = auth.login(email = response.email, password = defaultUser.password).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
        response.username shouldBe "random"
    }

    @Test
    @DisplayName("Ошибка регистрации при пустом имени пользователя: User details cannot be null or blank")
    fun testCreateUserErrorNoUsername() {
        val response =
            users.createUser(CreateUserRequest("", "user@ast.com", "123456")).getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "User details cannot be null or blank"
    }

    @Test
    @DisplayName("Ошибка регистрации при пустом email: User details cannot be null or blank")
    fun testCreateUserErrorNoEmail() {
        val response = users.createUser(CreateUserRequest("User", "1234567", "")).getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "User details cannot be null or blank"
    }

    @Test
    @DisplayName("Ошибка регистрации при пустом пароле: User details cannot be null or blank")
    fun testCreateUserErrorNoPassword() {
        val email = users.createUser(defaultUser).getAsObject().email
        val response = users.createUser(CreateUserRequest(" ", "", email)).getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "User details cannot be null or blank"
    }

    @Test
    @DisplayName("Ошибка регистрации при ранее зарегистрированном email: Something went wrong. Please verify request.")
    fun testLoginWithNullCredentials() {
        val response =
            users.createUser(CreateUserRequest("Users", "123456", "fdgfd@mail.re")).getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "Something went wrong. Please verify request."
    }
}