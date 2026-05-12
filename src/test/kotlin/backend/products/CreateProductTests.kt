package backend.products

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.extension.Extensions.Companion.getErrorAsObject
import org.example.backend.api.extension.Extensions.Companion.toBearer
import org.example.backend.api.models.ErrorResponse
import org.example.backend.api.models.products.CreateProductRequest
import org.example.backend.controllers.Controllers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Product")
@Story("Create product tests")
@Tags(Tag("product"), Tag("regress"), Tag("api"))
class CreateProductTests : Controllers() {

    @Test
    @DisplayName("Проверка создания товара")
    fun testCreateProduct() {
        val response =
            products.createProduct(
                product = CreateProductRequest(
                    "Coffee SPb",
                    7.49,
                    "Coffee from SPb with Love"
                )
            )
                .getAsObject()

        response.name shouldBe "Coffee SPb"
        response.description shouldBe "Coffee from SPb with Love"
        response.price shouldBe 7.49
    }

    @Test
    @DisplayName("Получение ошибки при передаче пустого поля имя: name cannot be null or empty")
    fun testCreateProductErrorNoName() {
        val response =
            products.createProduct(
                product = CreateProductRequest(
                    "",
                    7.49,
                    "Coffee from SPb with Love"
                )
            )
                .getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "name cannot be null or empty"
    }

    @Test
    @DisplayName("Получение ошибки при передаче пустого поля описание: description cannot be null or empty")
    fun testCreateProductErrorNoDescription() {
        val response =
            products.createProduct(
                product = CreateProductRequest(
                    "Coffee SPb",
                    7.49,
                    ""
                )
            )
                .getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "description cannot be null or empty"
    }
}