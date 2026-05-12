package org.example.backend.controllers

open class Controllers {
    protected val auth get() = AuthController()
    protected val users get() = UsersController()
    protected val orders get() = OrdersController()
    protected val products get() = ProductsController()
}