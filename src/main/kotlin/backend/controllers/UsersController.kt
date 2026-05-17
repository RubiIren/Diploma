package org.example.backend.controllers

import io.qameta.allure.Step
import okhttp3.ResponseBody
import org.example.backend.api.endpoints.Endpoints
import org.example.backend.api.extension.Extensions.Companion.getAsObject
import org.example.backend.api.models.users.CreateUserRequest
import org.example.backend.api.models.users.CreateUserResponse
import org.example.backend.api.models.users.UpdateRequest
import org.example.backend.helpers.AuthorizationHelper
import org.example.backend.helpers.GarbageCollector
import retrofit2.Response

class UsersController : Endpoints() {

    private val authHelper = AuthorizationHelper()

    @Step("Создание пользователя с username: {username} email: {email} and password: {password}")
    fun createUser(body: CreateUserRequest): Response<CreateUserResponse> {
        return users.createUser(body).execute()
            .also { GarbageCollector.user.add(it.getAsObject().id) }

    }

    @Step("Получить пользователя с id: {id}")
    fun getUserById(token: String = authHelper.getAdminToken(), id: Int): Response<CreateUserResponse> {
        return users.getUserById(token, id).execute()
    }

    @Step("Удалить пользователя с id: {id}")
    fun deleteUserById(token: String = authHelper.getAdminToken(), id: Int): Response<ResponseBody> {
        return users.deleteUserById(token, id).execute()
    }

    @Step("Изменить пользователя с id: {id}r")
    fun updateUserById(
        token: String = authHelper.getAdminToken(),
        id: Int,
        body: UpdateRequest
    ): Response<CreateUserResponse> {
        return users.putUserById(token, id, body).execute()
    }

    @Step("Получить всех пользователей")
    fun getAllUsers(token: String = authHelper.getAdminToken(), offset: Int = 0, limit: Int = 10): Response<List<CreateUserResponse>> {
        return users.getUsers(token, offset, limit).execute()
    }
}
