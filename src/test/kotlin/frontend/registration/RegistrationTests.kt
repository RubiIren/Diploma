package frontend.registration

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.example.frontend.components.HeaderComponent
import org.example.frontend.components.popup.RegistrationPopup
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@Feature("Registration")
@Story("Registration tests")
@Tags(Tag("registration"), Tag("regress"), Tag("auth"))
class RegistrationTests : BaseUiTest() {

    @ParameterizedTest
    @Tag("error")
    @CsvSource(
        // имя, email, пароль, ожидаемая ошибка
        "'',user@ast.com,12345,'Please enter username, email and password'",  // пустое имя
        "Random,'',12345,'Please enter username, email and password'",  // пустой email
        "'',user@ast.com,'','Please enter username, email and password'", // пустой пароль
        "'','', '', 'Please enter username, email and password'", // пустая форма
        "' ',user@ast.com, 12345, 'User details cannot be null or blank'",  // пробел в форме имени
        "Random,fdgfd@mail.re, 12345, 'Something went wrong. Please verify request.'",  // пользователь уже имеется в системе
    )
    @DisplayName("Проверка ошибок в форме регистрации")
    fun testAuthErrors(name: String, email: String, password: String, expectedError: String) {
        MainPage()
            .navigateHeader()
            .clickLink("Join")

        val error = RegistrationPopup()
            .inputUsername(name)
            .inputEmail(email)
            .inputPassword(password)
            .clickCreateUser()
            .getErrorText()

        expectedError shouldBe error
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    @Tag("crit")
    fun testAuthUser() {
        val randomEmail = "user${System.currentTimeMillis()}@test.com"

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        RegistrationPopup()
            .inputUsername("User")
            .inputEmail(randomEmail)
            .inputPassword("12345")
            .clickCreateUser()

        val avatar = HeaderComponent()
            .checkAvatarUser()

        avatar shouldBe true
    }
}