package org.example.database

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class JDBCHelperOrder {

    private val jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
    private val username: String = "postgres"
    private val password: String = "postgres"
    private val client = DriverManager.getConnection(jdbcUrl, username, password)

    fun getOrders(): List<Order> {
        val orders = mutableListOf<Order>() // Создаём пустой список для хранения заказов

        try {
            val statement: Statement = client.createStatement() // Создаём объект для выполнения SQL‑запросов
            val resultSet: ResultSet =
                statement.executeQuery("SELECT * FROM table_orders") // Выполняем запрос на получение всех заказов из таблицы table_orders

            while (resultSet.next()) {             // Проходим по каждой строке результата запроса
                val order = Order(
                    id = resultSet.getInt("id"),
                    userId = resultSet.getInt("userId"),
                    orderStatus = resultSet.getString("orderStatus"),
                    products = resultSet.getString("products"),
                    totalAmount = resultSet.getDouble("totalAmount"),
                    createdAt = resultSet.getLong("createdAt"),
                    updatedAt = resultSet.getLong("updatedAt")
                )
                orders.add(order)
            }

            resultSet.close()
            statement.close()
        } catch (e: Exception) {
            println("Error fetching orders: ${e.message}")
        }

        return orders
    }

    fun deleteOrderById(orderId: Int): Boolean {
        try {
            val sql = "DELETE FROM table_orders WHERE id = ?"
            val preparedStatement = client.prepareStatement(sql)
            preparedStatement.setInt(1, orderId)

            val rowsAffected = preparedStatement.executeUpdate()
            preparedStatement.close()

            return rowsAffected > 0
        } catch (e: Exception) {
            println("Error deleting order with ID $orderId: ${e.message}")
            return false
        }
    }
}

data class Order(
    var id: Int,
    val userId: Int?,
    var orderStatus: String,
    var products: String?,
    var totalAmount: Double,
    var createdAt: Long,
    var updatedAt: Long
)

data class Product(
    var id: Int,
    var name: String,
    var price: Double,
    var description: String
)