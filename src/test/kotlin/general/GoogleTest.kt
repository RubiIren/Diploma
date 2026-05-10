package general

import com.codeborne.selenide.Selenide
import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.example.frontend.helpers.BaseUiTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("DriverProvider")
@Story("Addition tests")
@Tags(Tag("google"), Tag("driverProvider"))
class GoogleTest : BaseUiTest() {
    @Test
    @DisplayName("Проверка открытия Google")
    @Disabled("Необходимо в BaseUiTest раскомментировать настройки для WebDriver")
    fun testOpenGoogle() {
        openBrowser()
        val title = Selenide.title()
        title shouldBe "Google"
    }
}