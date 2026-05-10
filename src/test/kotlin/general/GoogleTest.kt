package general

import com.codeborne.selenide.Selenide
import io.kotest.matchers.shouldBe
import org.example.frontend.helpers.BaseUiTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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