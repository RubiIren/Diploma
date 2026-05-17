package org.example.backend.controllers

import io.qameta.allure.Step
import org.example.backend.api.endpoints.Endpoints
import org.example.backend.api.models.auth.LoginRequest
import org.example.backend.api.models.auth.LoginResponse
import retrofit2.Response

class AuthController: Endpoints() {

    @Step("Авторизация с email: {email} и паролем: {password}")
    fun login(email: String, password: String): Response<LoginResponse> {
        return auth.postLogin(body = LoginRequest(email = email, password = password)).execute()
    }
}