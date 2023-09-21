package view.gerenteView

import model.InputUserModel
import service.acoesBancarias.ContaBancaria
import service.table.TableTransferenciaService
import view.MenuPrincipalView

class MenuGerente {
    private val inputUserModel = InputUserModel()
    private val transferenciaService = TableTransferenciaService()
    private val contaBancaria = ContaBancaria()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuPrincipalView()
                1 -> transferencia()
                2 -> deposito()
                3 -> saque()
                4 -> verSaldo()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    fun transferencia() {
        val valor = inputUserModel.readDoubleFromUser("Qual o valor da Transferencia: ")
        val idDestino = inputUserModel.readIntFromUser("Qual o ID da conta de destino: ")
        val idOrigem = inputUserModel.readIntFromUser("Qual o ID da sua conta: ")

        transferenciaService.addTransferencia(valor, idOrigem, idDestino)
    }

    fun deposito() {
        val valor = inputUserModel.readDoubleFromUser("Qual o valor do Depósito: ")
        val contaId = inputUserModel.readIntFromUser("Qual o ID da sua conta: ")

        contaBancaria.realizarDeposito(contaId, valor)
    }

    fun saque() {
        val valor = inputUserModel.readDoubleFromUser("Qual o valor do Saque: ")
        val contaId = inputUserModel.readIntFromUser("Qual o ID da sua conta: ")

        contaBancaria.realizarSaque(contaId, valor)
    }

    fun verSaldo() {
        val contaId = inputUserModel.readIntFromUser("Qual o ID da sua conta: ")

        val saldo = contaBancaria.verificarSaldo(contaId)

        if (saldo != null) {
            println("Saldo da conta: R$ $saldo")
        } else {
            println("Conta não encontrada.")
        }
    }

    private fun printMenu() {
        println(
            "\n0. Menu Principal | " +
                    "1. Cliente | " +
                    "2. Contas Bancarias | " +
                    "3. Transferencias | " +
                    "4. Verificar Saldo"
        )
    }
}