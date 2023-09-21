package service.table

import connection.Connect
import service.TipoConta
import java.sql.SQLException
import kotlin.random.Random

class TableContaBancariaService {

    private val connection = Connect().creatConnect()

    fun addContaBancaria(clienteId: Int, tipo: TipoConta, senha: Int) {
        try {
            val numeroConta = gerarNumeroConta()

            val sql =
                "INSERT INTO conta_bancaria (numero_conta, saldo, cliente_id, tipo_conta, senha) VALUES (?, ?, ?, ?, ?)"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, numeroConta)
            preparedStatement.setDouble(2, 0.00)
            preparedStatement.setInt(3, clienteId)
            preparedStatement.setString(4, tipo.name)
            preparedStatement.setInt(5, senha)

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
            val sql = "DELETE FROM conta_bancaria WHERE id=$id"
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
            val resultSet = statement.executeQuery("SELECT id, numero_conta, saldo, cliente_id, tipo_conta FROM conta_bancaria")

            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val numeroConta = resultSet.getString("numero_conta")
                val saldo = resultSet.getDouble("saldo")
                val clienteId = resultSet.getInt("cliente_id")
                val tipo = resultSet.getString("tipo_conta")


                println("ID: $id | Número da Conta: $numeroConta | Saldo: $saldo | ID do Cliente: $clienteId | Tipo da Conta: $tipo")
            }

            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun listContaBancariaPorId(id: Int) {
        try {
            val sql = "SELECT id, numero_conta, saldo, cliente_id, tipo_conta FROM conta_bancaria WHERE id=?"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)
            val resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                val numeroConta = resultSet.getString("numero_conta")
                val saldo = resultSet.getDouble("saldo")
                val clienteId = resultSet.getInt("cliente_id")
                val tipo = resultSet.getString("tipo_conta")

                println("ID: $id | Número da Conta: $numeroConta | Saldo: $saldo | ID do Cliente: $clienteId | Tipo da Conta: $tipo")
            } else {
                println("Conta Bancária com ID $id não encontrada.")
            }

            resultSet.close()
            preparedStatement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    private fun gerarNumeroConta(): String {
        val min = 100000
        val max = 999999
        return (Random.nextInt(max - min + 1) + min).toString()
    }

}
