    package model

class InputUserModel {
    fun readDoubleFromUser(mensagem: String): Double {
        while (true) {
            print(mensagem)
            val input = readlnOrNull()
            try {
                return input?.toDouble() ?: throw NumberFormatException()
            } catch (e: NumberFormatException) {
                println("Entrada inválida. Por favor, digite um número inteiro válido.")
            }
        }
    }
    fun readIntFromUser(mensagem: String): Int {
        while (true) {
            print(mensagem)
            val input = readlnOrNull()
            try {
                return input?.toInt() ?: throw NumberFormatException()
            } catch (e: NumberFormatException) {
                println("Entrada inválida. Por favor, digite um número inteiro válido.")
            }
        }
    }
    fun readStringFromUser(mensagem: String): String {
        while (true) {
            print(mensagem)
            val input = readlnOrNull()
            try {
                return input ?: throw NumberFormatException()
            } catch (e: NumberFormatException) {
                println("Entrada inválida. Por favor, digite um número válido.")
            }
        }
    }
}