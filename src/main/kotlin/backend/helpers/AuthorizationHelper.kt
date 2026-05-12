package org.example.backend.helpers

import io.qameta.allure.Step
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.extension.Extensions.Companion.toBearer
import org.example.backend.api.models.auth.defaultAdmin
import org.example.backend.controllers.Controllers

class AuthorizationHelper: Controllers() {

    @Step("Получить токен авторизации")
    fun getToken(email: String, password: String): String {
        return auth.login(email, password).getAsObject().accessToken.toBearer()
    }

    @Step("Получить токен админа")
    fun getAdminToken(): String {
        return auth.login(email = defaultAdmin.email, password = defaultAdmin.password).getAsObject().accessToken.toBearer()
    }
}