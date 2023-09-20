package service

import connection.Connect
import java.sql.SQLException

class TableTransferenciaService {
    private val connection = Connect().creatConnect()

    fun addTransferencia(data: String, valor: Double, contaOrigemId: Int, contaDestinoId: Int) {
        try {
            val sql = "INSERT INTO Transacao (data, valor, conta_origem_id, conta_destino_id) VALUES (?, ?, ?, ?)"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, data)
            preparedStatement.setDouble(2, valor)
            preparedStatement.setInt(3, contaOrigemId)
            preparedStatement.setInt(4, contaDestinoId)

            val rows = preparedStatement.executeUpdate()

            if (rows > 0) {
                println("Transferência adicionada com sucesso!")
            } else {
                println("Erro ao adicionar a transferência.")
            }

            preparedStatement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun deleteTransferencia(id: Int) {
        try {
            val sql = "DELETE FROM Transacao WHERE id=$id"
            val statement = connection.createStatement()
            val rows = statement.executeUpdate(sql)

            if (rows > 0) {
                println("Transferência com ID $id deletada com sucesso!")
            } else {
                println("Transferência com ID $id não encontrada.")
            }

            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun listTransferencias() {
        try {
            val statement = connection.createStatement()
            val resultSet =
                statement.executeQuery("SELECT id, data, valor, conta_origem_id, conta_destino_id FROM Transacao")

            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val data = resultSet.getString("data")
                val valor = resultSet.getDouble("valor")
                val contaOrigemId = resultSet.getInt("conta_origem_id")
                val contaDestinoId = resultSet.getInt("conta_destino_id")

                println("ID: $id | Data: $data | Valor: $valor | Conta Origem: $contaOrigemId | Conta Destino: $contaDestinoId")
            }

            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun updateTransferencia(
        id: Int,
        novaData: String,
        novoValor: Double,
        novaContaOrigemId: Int,
        novaContaDestinoId: Int
    ) {
        try {
            val sql = "UPDATE Transacao SET data=?, valor=?, conta_origem_id=?, conta_destino_id=? WHERE id=$id"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, novaData)
            preparedStatement.setDouble(2, novoValor)
            preparedStatement.setInt(3, novaContaOrigemId)
            preparedStatement.setInt(4, novaContaDestinoId)

            val rows = preparedStatement.executeUpdate()

            if (rows > 0) {
                println("Transferência com ID $id atualizada com sucesso!")
            } else {
                println("Transferência com ID $id não encontrada.")
            }

            preparedStatement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}

