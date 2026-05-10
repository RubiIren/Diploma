package general.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Calculator")
@Story("Subtraction tests")
@Tags(Tag("calculator"), Tag("sub"))
class Subtraction {

    @Test
    @DisplayName("Test subtraction")
    fun subtraction() {
        val a = 83
        val b = 3

        val expectedSub = 80
        val actualSub = a - b

        expectedSub shouldBe actualSub

        println("$a - $b = $actualSub")
    }
}