package view.gerenteView

import model.InputUserModel
import service.acoesBancarias.ContaBancaria
import service.table.TableTransferenciaService
import view.MenuPrincipalView

class MenuGerente {
    private val inputUserModel = InputUserModel()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuPrincipalView()
                1 -> ClienteView().start()
                2 -> ContaBancariaView().start()
                3 -> TransferenciasView().start()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    private fun printMenu() {
        println(
            "\n0. Menu Principal | " +
                    "1. Cliente | " +
                    "2. Contas Bancarias | " +
                    "3. Transferencias"
        )
    }
}