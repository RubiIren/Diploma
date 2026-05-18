package org.example.frontend.helpers

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import io.kotest.assertions.AssertionErrorBuilder.Companion.fail
import org.openqa.selenium.JavascriptExecutor

class Extensions {
    companion object {

        fun ElementsCollection.getFirstOrAsserted(text: String): SelenideElement {
            return this.firstOrNull { it.text == text }
                ?.shouldBe(visible)  // Ждём видимости найденного элемента
                ?: fail("Элемент с текстом '$text' не найден")
        }
    }
}

fun String.toPrice() = filter { it.isDigit() }.toDouble() / 100
