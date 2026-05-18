package org.example.frontend.pages

import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.*
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byTestGroup

private val inputOrderId get() = element("[placeholder='Order ID']").find(shadowCss("input"))
private val txtOrderId get() = elements(byTestGroup("order-id"))
private val orderStatus get() = elements(byTestGroup("order-status"))

class OrdersPage {
    @Step("Открыть страницу заказов")
    fun open(): OrdersPage {
        open("/orders")
        return this
    }

    @Step("Ввести номер заказа")
    fun inputOrderId(id: Int): OrdersPage {
        inputOrderId.click()
        inputOrderId.value = id.toString()
        inputOrderId.pressEnter()
        return this
    }

    @Step("Получить id заказа")
    fun getOrderId(): String {
        val fullText = txtOrderId.first().text()
        return fullText.split(": ").last()
    }

    @Step("Получить статус заказа")
    fun getOrderStatus(): String {
        val fullText = orderStatus.first().text()
        return fullText.split(": ").last()
    }
}