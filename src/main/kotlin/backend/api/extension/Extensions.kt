package org.example.backend.api.extension

import com.google.gson.Gson
import io.kotest.assertions.fail
import io.qameta.allure.Step
import retrofit2.Response

class Extensions {

    companion object {

        @Step("Проверка, что HTTP‑запрос выполнен успешно")
        fun <T> Response<T>.checkIsSuccessful(): Boolean {
            if (!this.isSuccessful) fail("Response is not successful: code: ${code()}")
            return true
        }

        @Step("Получение тела успешного ответа в виде объекта заданного типа {T}")
        inline fun <reified T> Response<T>.getAsObject(): T {
            return try {
                body()!!
            } catch (e: Exception) {
                throw Error(
                    "Response body is null or cannot be cast to the specified type: body: ${body()} | errorBody: ${errorBody()?.string()}",
                    e
                )
            }
        }

        @Step("Получение тела ошибки в объекте заданного типа {T}")
        inline fun <reified R> Response<*>.getErrorAsObject(): R {
            return try {
                Gson().fromJson(errorBody()?.string().orEmpty(), R::class.java)
            } catch (e: Exception) {
                throw Error(
                    "Error body is null or cannot be cast to the specified type: errorBody: ${errorBody()?.string()}",
                    e
                )
            }
        }

        @Step("Получение тела ошибки в виде строки")
        inline fun <reified T> Response<T>.getErrorBody(): String {
            return errorBody()?.string() ?: ""
        }

        @Step("Формирование Bearer‑токена из строки")
        fun String.toBearer(): String {
            return "Bearer $this"
        }
    }
}