package backend.orders

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.example.backend.api.models.orders.CreateOrderRequest
import org.example.backend.api.models.orders.UpdateOrderRequest
import org.example.backend.controllers.Controllers
import org.example.database.JDBCHelperOrder
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.OrdersPage
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Feature("Order")
@Story("Order change status")
@Tags(Tag("order"), Tag("regress"), Tag("api"))
class OrderStatusChangeTest : BaseUiTest() {
    private val controllers = Controllers()

    @ParameterizedTest(name = "Изменение статуса заказа {0}")
    @ValueSource(strings = ["PENDING", "IN_PROGRESS", "COMPLETED"])
    fun orderStatusChangeTest(newStatus: String) {

        val createOrder = CreateOrderRequest(
            userId = null,
            products = listOf(CreateOrderRequest.Product(id = 228))
        )
        val orderId = controllers.orders.createOrder(body = createOrder).id

        val updateRequest = UpdateOrderRequest(orderStatus = newStatus)
        val response = controllers.orders.updateOrderById(
            id = orderId,
            body = updateRequest
        )

        val orderUiStatus = OrdersPage()
            .open()
            .inputOrderId(orderId)
            .getOrderStatus()

        println("Создан заказ с ID: $orderId")
        println("Статус заказа $orderId изменён на: ${response.orderStatus}")
        println("Статус заказа на UI $orderUiStatus")

        orderUiStatus shouldBe response.orderStatus
    }
}