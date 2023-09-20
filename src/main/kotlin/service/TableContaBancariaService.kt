package service

import connection.Connect
import java.sql.SQLException

class TableContaBancariaService {
    companion object {
        private val connection = Connect().creatConnect()

        fun addContaBancaria(numeroConta: String, saldo: Double, clienteId: Int) {
            try {
                val sql = "INSERT INTO ContaBancaria (numero_conta, saldo, cliente_id) VALUES (?, ?, ?)"
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, numeroConta)
                preparedStatement.setDouble(2, saldo)
                preparedStatement.setInt(3, clienteId)

                val rows = preparedStatement.executeUpdate()

                if (rows > 0) {
                    println("Conta Bancária adicionada com sucesso!")
                } else {
                    println("Erro ao adicionar a Conta Bancária.")
                }

                preparedStatement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun deleteContaBancaria(id: Int) {
            try {
                val sql = "DELETE FROM ContaBancaria WHERE id=$id"
                val statement = connection.createStatement()
                val rows = statement.executeUpdate(sql)

                if (rows > 0) {
                    println("Conta Bancária com ID $id deletada com sucesso!")
                } else {
                    println("Conta Bancária com ID $id não encontrada.")
                }

                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun listContasBancarias() {
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT id, numero_conta, saldo, cliente_id FROM ContaBancaria")

                while (resultSet.next()) {
                    val id = resultSet.getInt("id")
                    val numeroConta = resultSet.getString("numero_conta")
                    val saldo = resultSet.getDouble("saldo")
                    val clienteId = resultSet.getInt("cliente_id")

                    println("ID: $id | Número da Conta: $numeroConta | Saldo: $saldo | ID do Cliente: $clienteId")
                }

                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun updateContaBancaria(id: Int, novoNumeroConta: String, novoSaldo: Double, novoClienteId: Int) {
            try {
                val sql = "UPDATE ContaBancaria SET numero_conta=?, saldo=?, cliente_id=? WHERE id=$id"
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, novoNumeroConta)
                preparedStatement.setDouble(2, novoSaldo)
                preparedStatement.setInt(3, novoClienteId)

                val rows = preparedStatement.executeUpdate()

                if (rows > 0) {
                    println("Conta Bancária com ID $id atualizada com sucesso!")
                } else {
                    println("Conta Bancária com ID $id não encontrada.")
                }

                preparedStatement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}
