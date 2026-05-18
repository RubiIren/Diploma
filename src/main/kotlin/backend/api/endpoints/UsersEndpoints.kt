package org.example.backend.api.endpoints

import okhttp3.ResponseBody
import org.example.backend.api.endpoints.headers.Headers
import org.example.backend.api.models.users.CreateUserRequest
import org.example.backend.api.models.users.CreateUserResponse
import org.example.backend.api.models.users.UpdateRequest
import retrofit2.Call
import retrofit2.http.*

interface UsersEndpoints {
    @POST("users/create")
    fun createUser(@Body body: CreateUserRequest): Call<CreateUserResponse>

    @GET("users/{id}")
    fun getUserById(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Int): Call<CreateUserResponse>

    @DELETE("users/{id}")
    fun deleteUserById(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Int): Call<ResponseBody>

    @PUT("users/{id}")
    fun putUserById(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int,
        @Body body: UpdateRequest
    ): Call<CreateUserResponse>

    @GET("users/")
    fun getUsers(
        @Header(Headers.AUTHORIZATION) token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<CreateUserResponse>>
}