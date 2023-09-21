package view.gerenteView

import model.InputUserModel
import service.TipoConta
import service.table.TableContaBancariaService
import view.MenuPrincipalView
import java.util.*

class ContaBancariaView {
    private val inputUserModel = InputUserModel()
    private val tableContaBancariaService = TableContaBancariaService()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuPrincipalView()
                1 -> Adicionar()
                2 -> Deletar()
                3 -> ListarTodos()
                4 -> buscarContaPorId()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    fun Adicionar() {
        val clienteId = inputUserModel.readIntFromUser("ID do cliente: ")
        val tipo = inputUserModel.readStringFromUser("Tipo da conta (Corrente | Poupança | Salário): ")
        val senha = inputUserModel.readIntFromUser("Senha de até 4 dígitos: ")

        val tipoValidado = validarTipoConta(tipo)
        if (tipoValidado != null && validarSenha(senha)) {
            tableContaBancariaService.addContaBancaria(clienteId, tipoValidado, senha)
        } else {
            println("Erro ao cadastrar a conta.")
        }
    }


    fun Deletar() {
        val id = inputUserModel.readIntFromUser("Informe o ID da conta bancária que deseja deletar: ")
        tableContaBancariaService.deleteContaBancaria(id)
    }


    fun ListarTodos() {
        println("Lista de Contas Bancárias:")
        tableContaBancariaService.listContasBancarias()
    }

    fun buscarContaPorId() {
        val id = inputUserModel.readIntFromUser("Informe o ID da conta bancária que deseja buscar: ")
        tableContaBancariaService.listContaBancariaPorId(id)
    }

    private fun printMenu() {
        println(
            "\n0. Menu Principal | " +
                    "1. Adicionar | " +
                    "2. Deletar  | " +
                    "3. Listar Clientes | " +
                    "4. Buscar Conta Por ID"
        )
    }
    private fun validarTipoConta(tipo: String): TipoConta? {
        val tipoLowerCase = tipo.lowercase(Locale.getDefault())
        val tipoEnum = TipoConta.values().find { it.name.equals(tipoLowerCase, ignoreCase = true) }

        return if (tipoEnum != null) {
            tipoEnum
        } else {
            println("Tipo de conta inválido.")
            null
        }
    }

    private fun validarSenha(senha: Int): Boolean {
        val senhaStr = senha.toString()
        return senhaStr.length == 4
    }
}