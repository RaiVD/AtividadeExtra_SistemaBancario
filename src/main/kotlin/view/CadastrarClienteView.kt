package view

import model.InputUserModel
import service.TipoConta
import service.table.TableClienteService
import service.table.TableContaBancariaService
import java.util.*

class CadastrarClienteView {
    val inputUserModel = InputUserModel()

    fun cadastroCliente (){

    }
//
//    fun cadastrarConta() {
//        val tipo = inputUserModel.readStringFromUser("Tipo da conta (Corrente | Poupanca | Salario): ")
//        val senha = inputUserModel.readIntFromUser("Cadastre uma senha de até 4 dígitos: ")
//
//        val tipoValidado = validarTipoConta(tipo)
//        if (tipoValidado != null) {
//            if (validarSenha(senha)) {
//                TableContaBancariaService.addContaBancaria(clienteId, tipoValidado, senha)
//                println("Conta aberta com sucesso!\n Dados da Conta:")
//                TableContaBancariaService.listContaBancariaPorId(clienteId)
//            } else {
//                println("Senha deve conter 4 dígitos.")
//            }
//        } else {
//            println("Tipo de conta inválido.")
//        }
//    }
//
//    private fun validarTipoConta(tipo: String): TipoConta? {
//        val tipoLowerCase = tipo.lowercase(Locale.getDefault())
//        val tipoEnum = TipoConta.values().find { it.name.equals(tipoLowerCase, ignoreCase = true) }
//
//        return if (tipoEnum != null) {
//            tipoEnum
//        } else {
//            println("Tipo de conta inválido.")
//            null
//        }
//    }
//
//    private fun validarSenha(senha: Int): Boolean {
//        val senhaStr = senha.toString()
//        return senhaStr.length == 4
//    }
}

