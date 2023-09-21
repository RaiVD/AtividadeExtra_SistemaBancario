package view.gerenteView

import model.InputUserModel
import service.table.TableTransferenciaService
import view.MenuPrincipalView

class TransferenciasView {
    private val inputUserModel = InputUserModel()
    private val tableTransferenciaService = TableTransferenciaService()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuPrincipalView()
                1 -> Deletar()
                2 -> ListarTodos()
                3 -> listarporId()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    fun Deletar() {
        val id = inputUserModel.readIntFromUser("Informe o ID da transferência que deseja excluir: ")
        tableTransferenciaService.deleteTransferencia(id)
    }
    fun ListarTodos() {
        println("Lista de Transferências:")
        tableTransferenciaService.listTransferencias()
    }
    fun listarporId() {
        val id = inputUserModel.readIntFromUser("Informe o ID da transferência que deseja buscar: ")
        tableTransferenciaService.listTransferenciaPorId(id)
    }

    private fun printMenu() {
        println(
            "\n0. Menu Principal | " +
                    "1. Cancelar Transferencia  | " +
                    "2. Listar Transferencias | " +
                    "3. Listar Transferencia por ID"
        )
    }
}