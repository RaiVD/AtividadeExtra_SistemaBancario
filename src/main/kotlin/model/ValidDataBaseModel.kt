package model

import connection.Connect
import java.sql.SQLException

class ValidDataBaseModel {
    companion object {
        private val connection = Connect().creatConnect()
//
//        fun isValidVendaId(id: Int): Boolean {
//            val sql = "SELECT COUNT(*) FROM venda WHERE id_venda=?"
//
//            try {
//                val preparedStatement = connection.prepareStatement(sql)
//                preparedStatement.setInt(1, id)
//                val resultSet = preparedStatement.executeQuery()
//                resultSet.next()
//                val count = resultSet.getInt(1)
//
//                resultSet.close()
//                preparedStatement.close()
//
//                return count > 0
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//
//            return false
//        }
//
//        fun isValidProdutoId(id: Int): Boolean {
//            val sql = "SELECT COUNT(*) FROM produto WHERE id_produto=?"
//
//            try {
//                val preparedStatement = connection.prepareStatement(sql)
//                preparedStatement.setInt(1, id)
//                val resultSet = preparedStatement.executeQuery()
//                resultSet.next()
//                val count = resultSet.getInt(1)
//
//                resultSet.close()
//                preparedStatement.close()
//
//                return count > 0
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//
//            return false
//        }
//
//        fun isValidVendedorId(id: Int): Boolean {
//            val sql = "SELECT COUNT(*) FROM vendedor WHERE id_vendedor= ?"
//
//            try {
//                val preparedStatement = connection.prepareStatement(sql)
//                preparedStatement.setInt(1, id)
//                val resultSet = preparedStatement.executeQuery()
//                resultSet.next()
//                val count = resultSet.getInt(1)
//
//                resultSet.close()
//                preparedStatement.close()
//
//                return count > 0
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//            return false
//        }
//
//        fun isValidClienteId(id: Int): Boolean {
//            val sql = "SELECT COUNT(*) FROM cliente WHERE id_cliente=?"
//
//            try {
//                val preparedStatement = connection.prepareStatement(sql)
//                preparedStatement.setInt(1, id)
//                val resultSet = preparedStatement.executeQuery()
//                resultSet.next()
//                val count = resultSet.getInt(1)
//
//                resultSet.close()
//                preparedStatement.close()
//
//                return count > 0
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//
//            return false
//        }
//
//        fun isValidProdutoInfo(nome_produto: String): Boolean {
//            return nome_produto.isNotBlank()
//        }
//
//        fun isValidVendedorInfo(nome_vendedor: String, email_vendedor: String, cpf: String): Boolean {
//            return nome_vendedor.isNotBlank() && email_vendedor.isNotBlank() && cpf.isNotBlank()
//        }
//
//        fun isValidClienteInfo(nome_cliente: String, email_cliente: String, cpf: String, endereco_cliente: String): Boolean {
//            return nome_cliente.isNotBlank() && email_cliente.isNotBlank() && cpf.isNotBlank() && endereco_cliente.isNotBlank()
//        }
//
//        fun validarQtd(qtd: Int): Boolean {
//            if (qtd <= 0) {
//                println("A quantidade do produto deve ser maior que zero.")
//                return false
//            }
//            return true
//        }
//        fun validarValor(qtd: Double): Boolean {
//            return qtd > 0
//        }
        // Validar entrada de email

        fun isValidEmail(email: String): Boolean {
            return email.contains("@")
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