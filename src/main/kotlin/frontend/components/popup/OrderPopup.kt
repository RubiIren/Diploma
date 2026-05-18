package org.example.frontend.components.popup

import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byTestId

class OrderPopup {

    private val txtTitle get() = element(byTestId("order-popup-status"))
    private val txtOrderId get() = element(byTestId("order-popup-id"))
    private val orderStatus get() = element(byTestId("order-popup-status"))
    private val orderCloseBtn get() = element(byTestId("order-popup-close"))

    @Step("Получить статус оформления заказа")
    fun getOrderTitle(): String {
        return txtTitle.text()
    }

    @Step("Получить id заказа")
    fun getOrderId(): String {
        val fullText = txtOrderId.text()
        return fullText.split(": ").last()
    }

    @Step("Получить статус заказа")
    fun getOrderStatus(): String {
        val fullText = orderStatus.text()
        return fullText.split(": ").last()
    }

    @Step("Закрыть попап")
    fun close(): OrderPopup {
        orderCloseBtn.click()
        return this
    }
}