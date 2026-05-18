package general.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Calculator")
@Story("Multiplication tests")
@Tags(Tag("calculator"), Tag("mult"))
class Multiplication {

    @Test
    @DisplayName("Test multiplication")
    fun multiplication() {
        val a = 2
        val b = 3

        val expectedMult = 6
        val actualMult = a * b

        expectedMult shouldBe actualMult

        println("$a * $b = $actualMult")
    }
}