package frontend.orders

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.example.backend.controllers.Controllers
import org.example.frontend.components.HeaderComponent
import org.example.frontend.components.popup.OrderPopup
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.example.frontend.pages.ProductsPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Order")
@Story("Order change status")
@Tags(Tag("order"), Tag("regress"), Tag("ui"))
class CreateOrdersTest : BaseUiTest() {
    private val controllers = Controllers()

    @Test
    @DisplayName("Создание заказа с товаром из попуярных на главной")
    fun createOrderFromPopular() {
        MainPage()
            .open()
            .getPopularProducts()
            .first().btnIncrement.click()

        MainPage()
            .open()
            .navigateHeader()
            .clickCart()
            .navigateCartPopup()
            .createOrder()

        val popup = OrderPopup()
        val orderId = popup.getOrderId().toInt()
        val orderStatus = popup.getOrderStatus()

        val orderBackInfo = controllers.orders.getOrderById(id = orderId)

        orderBackInfo.id shouldBe orderId
        orderBackInfo.orderStatus shouldBe orderStatus
    }

    @Test
    @DisplayName("Создание заказа со старницы продуктов")
    fun createOrderFromProductPage() {
        ProductsPage()
            .open()
            .getProductsAsObjects()
            .first().btnIncrement.click()

        HeaderComponent()
            .clickCart()
            .navigateCartPopup()
            .createOrder()

        val popup = OrderPopup()
        val orderId = popup.getOrderId().toInt()
        val orderStatus = popup.getOrderStatus()

        val orderBackInfo = controllers.orders.getOrderById(id = orderId)

        orderBackInfo.id shouldBe orderId
        orderBackInfo.orderStatus shouldBe orderStatus
    }
}