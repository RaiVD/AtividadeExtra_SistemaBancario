package model

import connection.Connect
import java.sql.SQLException

class ValidDataBaseModel {
    companion object {
        private val connection = Connect().creatConnect()

        fun isValidClienteInfo(nome: String, cpf: String, endereco: String): Boolean {
            return nome.isNotBlank() && cpf.isNotBlank() && endereco.isNotBlank()
        }
        fun isValidClienteCredentials(numeroDaConta: String, senha: Int): Boolean {
            val sql = """
                SELECT COUNT(*) 
                FROM conta_bancaria 
                WHERE (numero_conta=? AND senha=?)
            """.trimIndent()

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, numeroDaConta)
                preparedStatement.setInt(2, senha)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            return false
        }
        fun isValidGerenteCredentials(email_user: String, senha: Int): Boolean {
            val sql = """
                SELECT COUNT(*) 
                FROM gerente 
                WHERE (email=? AND senha=?)
            """.trimIndent()

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, email_user)
                preparedStatement.setInt(2, senha)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            return false
        }
    }
}