package view

import model.InputUserModel
import model.ValidDataBaseModel
import view.clienteView.MenuCliente
import view.gerenteView.MenuGerente

class MenuPrincipalView {
    private val inputUserModel = InputUserModel()
    fun start() {
        println("\n========================== Gerenciamento de Vendas - SimCity ============================")
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> println("Encerrando o programa...")
                1 -> {
                    val numeroDaConta = inputUserModel.readStringFromUser("Numero da Conta: ")
                    val password_user = inputUserModel.readIntFromUser("Senha: ")
                    if (ValidDataBaseModel.isValidClienteCredentials(numeroDaConta, password_user)) {
                        MenuCliente().start()
                    } else {
                        println("E-mail ou Senha invalidos!")
                    }
                }

                2 -> {
                    val email_user = inputUserModel.readStringFromUser("E-mail: ")
                    val password_user = inputUserModel.readIntFromUser("Senha: ")
                    if (ValidDataBaseModel.isValidGerenteCredentials(email_user, password_user)) {
                        MenuGerente()
                    } else {
                        println("E-mail ou Senha invalidos!")
                    }
                }
                3 -> {
                    CadastrarClienteView().cadastroCliente()
                }
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    private fun printMenu() {
        println("\n0. Sair | 1. Login Cliente | 2. Login Gerente | 3. Criar Conta")
    }
}