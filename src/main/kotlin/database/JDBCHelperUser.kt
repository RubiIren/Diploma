package org.example.database

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class JDBCHelperUser {

    private val jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
    private val username: String = "postgres"
    private val password: String = "postgres"
    private val client = DriverManager.getConnection(jdbcUrl, username, password)

    fun getUsers(): List<User> {
        val users = mutableListOf<User>()

        try {
            val statement: Statement = client.createStatement()
            val resultSet: ResultSet = statement.executeQuery("SELECT * FROM table_users")

            while (resultSet.next()) {
                val user = User(
                    id = resultSet.getInt("id"),
                    username = resultSet.getString("username"),
                    password = resultSet.getString("password"),
                    email = resultSet.getString("email"),
                    phoneNumber = resultSet.getString("phoneNumber"),
                    createdAt = resultSet.getLong("createdAt")
                )
                users.add(user)
            }

            resultSet.close()
            statement.close()
        } catch (e: Exception) {
            println("Error fetching products: ${e.message}")
        }

        return users
    }

    fun deleteUserById(Id: Int): Boolean {
        try {
            val sql = "DELETE FROM table_users WHERE id = ?"
            val preparedStatement = client.prepareStatement(sql)
            preparedStatement.setInt(1, Id)

            val rowsAffected = preparedStatement.executeUpdate()
            preparedStatement.close()

            return rowsAffected > 0
        } catch (e: Exception) {
            println("Error deleting order with ID $Id: ${e.message}")
            return false
        }
    }
}

data class User(
    var id: Int,
    var username: String,
    var password: String,
    var email: String,
    var phoneNumber: String?,
    var createdAt: Long
)
