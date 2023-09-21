package service.acoesBancarias

import connection.Connect
import java.sql.SQLException

class ContaBancaria() {

    private val connection = Connect().creatConnect()

    fun verificarSaldo(contaId: Int): Double? {
        try {
            val sql = "SELECT saldo FROM conta_bancaria WHERE id = ?"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, contaId)

            val resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                return resultSet.getDouble("saldo")
            }

            resultSet.close()
            preparedStatement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return null
    }

    fun realizarDeposito(contaId: Int, valor: Double): Boolean {
        try {
            val saldoAtual = verificarSaldo(contaId)

            if (saldoAtual != null) {
                val novoSaldo = saldoAtual + valor
                val sql = "UPDATE conta_bancaria SET saldo = ? WHERE id = ?"
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setDouble(1, novoSaldo)
                preparedStatement.setInt(2, contaId)

                val rows = preparedStatement.executeUpdate()

                if (rows > 0) {
                    println("Depósito realizado com sucesso!")
                    return true
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        println("Erro ao realizar o depósito.")
        return false
    }

    fun realizarSaque(contaId: Int, valor: Double): Boolean {
        try {
            val saldoAtual = verificarSaldo(contaId)

            if (saldoAtual != null && saldoAtual >= valor) {
                val novoSaldo = saldoAtual - valor
                val sql = "UPDATE conta_bancaria SET saldo = ? WHERE id = ?"
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setDouble(1, novoSaldo)
                preparedStatement.setInt(2, contaId)

                val rows = preparedStatement.executeUpdate()

                if (rows > 0) {
                    println("Saque realizado com sucesso!")
                    return true
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        println("Erro ao realizar o saque.")
        return false
    }
}
