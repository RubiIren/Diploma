package calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

@Feature("Calculator")
@Story("Square tests")
@Tags(Tag("calculator"), Tag("regress"), Tag("square"))
class Square {

    @Test
    @DisplayName("Test square")
    fun square() {
        val a = 12

        val expectedSquare = 144
        val actualSquare = a * a

        expectedSquare shouldBe actualSquare

        println("$a ^ 2 = $actualSquare")
    }
}