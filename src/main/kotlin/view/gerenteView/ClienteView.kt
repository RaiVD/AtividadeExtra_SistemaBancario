package view.gerenteView

import model.InputUserModel
import service.table.TableClienteService
import view.MenuPrincipalView

class ClienteView {
    private val inputUserModel = InputUserModel()
    private val tableClienteService = TableClienteService()
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
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    fun Adicionar() {
        val nome = inputUserModel.readStringFromUser("Nome: ")
        val cpf = inputUserModel.readStringFromUser("CPF (apenas números): ")
        val endereco = inputUserModel.readStringFromUser("Endereço (Rua, número, Bairro, Cidade): ")

        tableClienteService.addCliente(nome, cpf, endereco)
    }

    fun Deletar() {
        val id = inputUserModel.readIntFromUser("Informe o ID do cliente que deseja deletar: ")
        tableClienteService.deleteCliente(id)
    }

    fun ListarTodos() {
        println("Lista de Clientes:")
        tableClienteService.listClientes()
    }


    private fun printMenu() {
        println(
            "\n0. Menu Principal | " +
                    "1. Adicionar | " +
                    "2. Deletar  | " +
                    "3. Listar Clientes"
        )
    }

}