package org.example.backend.controllers

open class Controllers {
    val auth get() = AuthController()
    val users get() = UsersController()
    val orders get() = OrdersController()
    val products get() = ProductsController()
}