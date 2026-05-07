package calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Calculator")
@Story("Addition tests")
@Tags(Tag("calculator"), Tag("regress"), Tag("add"))
class Addition {

    @Test
    @DisplayName("Test addition")
    fun addition() {
        val a = 2
        val b = 3

        val expectedAdd = 5
        val actualAdd = a + b

        expectedAdd shouldBe actualAdd

        println("$a + $b = $actualAdd")
    }
}