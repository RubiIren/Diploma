package general.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Calculator")
@Story("Division tests")
@Tags(Tag("calculator"), Tag("div"))
class Division {

    @Test
    @DisplayName("Test division")
    fun multiplication() {
        val a = 6
        val b = 3

        val expectedDiv = 2
        val actualDiv = 6 / 3

        expectedDiv shouldBe actualDiv

        println("$a * $b = $actualDiv")
    }
}
