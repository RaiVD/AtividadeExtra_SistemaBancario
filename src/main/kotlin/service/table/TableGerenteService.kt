package service.table

import connection.Connect
import model.ValidDataBaseModel.Companion.isValidEmail
import java.sql.SQLException

class TableGerenteService {
    companion object {
        private val connection = Connect().creatConnect()

        fun addGerente(nome: String, cpf: String, email: String, senha: Int) {
            try {
                if (!isValidEmail(email)){
                    println("Email invalido!")
                }
                val sql = "INSERT INTO Gerente (nome, cpf, email, senha) VALUES (?, ?, ?, ?)"
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, nome)
                preparedStatement.setString(2, cpf)
                preparedStatement.setString(3, email)
                preparedStatement.setInt(4, senha)

                val rows = preparedStatement.executeUpdate()

                if (rows > 0) {
                    println("Gerente adicionado com sucesso!")
                } else {
                    println("Erro ao adicionar o Gerente.")
                }

                preparedStatement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun deleteGerente(id: Int) {
            try {
                val sql = "DELETE FROM Gerente WHERE id=$id"
                val statement = connection.createStatement()
                val rows = statement.executeUpdate(sql)

                if (rows > 0) {
                    println("Gerente com ID $id deletado com sucesso!")
                } else {
                    println("Gerente com ID $id não encontrado.")
                }

                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun listGerentes() {
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT id, nome, cpf, email FROM Gerente")

                while (resultSet.next()) {
                    val id = resultSet.getInt("id")
                    val nome = resultSet.getString("nome")
                    val cpf = resultSet.getString("cpf")
                    val email = resultSet.getString("email")

                    println("ID: $id | Nome: $nome | CPF: $cpf | Email: $email")
                }

                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}

