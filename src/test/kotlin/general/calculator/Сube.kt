package general.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import kotlin.math.pow

@Feature("Calculator")
@Story("Cube tests")
@Tags(Tag("calculator"), Tag("cube"))
class Сube {

    @Test
    @DisplayName("Test cube")
    fun cube() {
        val a = 3
        val b = 3

        val expectedCube = 27
        val actualCube = b.toDouble().pow(a)

        expectedCube shouldBe actualCube

        println("$a ^ $b = $actualCube")
    }
}