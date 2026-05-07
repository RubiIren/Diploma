package calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import kotlin.math.sqrt

@Feature("Calculator")
@Story("Square root tests")
@Tags(Tag("calculator"), Tag("regress"), Tag("root"))
class SquareRoot {

    @Test
    @DisplayName("Test square root")
    fun squareRoot() {
        val a = 144

        val expectedSquareRoot = 12
        val actualSquareRoot = sqrt(a.toDouble())

        expectedSquareRoot shouldBe actualSquareRoot

        println("√$a = $actualSquareRoot")
    }
}