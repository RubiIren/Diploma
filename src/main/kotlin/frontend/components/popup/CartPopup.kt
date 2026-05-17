package org.example.frontend.components.popup

import com.codeborne.selenide.Selectors.byTestId
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import io.qameta.allure.Step
import org.example.frontend.components.list.CartItem
import org.example.frontend.components.list.CartItems
import org.example.frontend.helpers.Wrappers.Companion.byTestGroup
import org.example.frontend.helpers.toPrice

class CartPopup {
    private val txtTotalPrice get() = element(byTestId("cart-total-price"))
    private val btnCheckout get() = element(byTestId("cart-checkout"))
    private val newCartItems get() = elements(byTestGroup("cart-item"))

    @Step("Получить список товаров в корзине")
    fun getCartProducts(): List<CartItem> {
        return CartItems(newCartItems).getItems()
    }

    @Step("Получить общую стоимость товаров в корзине")
    fun getTotalPrice(): Double {
        return txtTotalPrice.text.toPrice()
    }
}