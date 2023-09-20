package service

import connection.Connect
import java.sql.SQLException


class TableClienteService {
    private val connection = Connect().creatConnect()

    fun addCliente(nome: String, cpf: String, endereco: String?) {
        try {
            val sql = "INSERT INTO Cliente (nome, cpf, endereco) VALUES ('$nome', '$cpf', ?)"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, endereco)

            val rows = preparedStatement.executeUpdate()

            if (rows > 0) {
                println("Cliente $nome adicionado com sucesso!")
            } else {
                println("Erro ao adicionar o cliente $nome.")
            }

            preparedStatement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun deleteCliente(id: Int) {
        try {
            val sql = "DELETE FROM Cliente WHERE id=$id"
            val statement = connection.createStatement()
            val rows = statement.executeUpdate(sql)

            if (rows > 0) {
                println("Cliente com ID $id deletado com sucesso!")
            } else {
                println("Cliente com ID $id não encontrado.")
            }

            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun listClientes() {
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT id, nome, cpf, endereco FROM Cliente")

            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val nome = resultSet.getString("nome")
                val cpf = resultSet.getString("cpf")
                val endereco = resultSet.getString("endereco")

                println("ID: $id | Nome: $nome | CPF: $cpf | Endereço: $endereco")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun updateCliente(id: Int, novoNome: String, novoCpf: String, novoEndereco: String?) {
        try {
            val sql = "UPDATE Cliente SET nome='$novoNome', cpf='$novoCpf', endereco=? WHERE id=$id"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, novoEndereco)

            val rows = preparedStatement.executeUpdate()

            if (rows > 0) {
                println("Cliente com ID $id atualizado com sucesso!")
            } else {
                println("Cliente com ID $id não encontrado.")
            }

            preparedStatement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}
