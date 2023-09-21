import view.MenuPrincipalView

fun main() {
//    Clientes Cadastrados:
//    id|nome  |cpf         |endereco                                         |
//    --+------+------------+-------------------------------------------------+
//    07|José  |12345678901 |Rua Benicio, 233, Pinheiros, São Paulo -SP       |
//    09|Julia |28328391823 |rua pedro pinho, 322, Jardim Amélia, Guarulhos-SP|
//    10|Raissa|209341029345|Rua santa clara, 32, Blumenal, São Paulo-SP      |
//-----------------------------------------------------------------------------------------------
//    Contas Bancarias:
//    id|numero_conta|saldo|cliente_id|tipo_conta|senha|
//    --+------------+-----+----------+----------+-----+
//    01|192835      | 0.00|         7|corrente  | 1234|
//    02|029312      | 0.00|         9|poupanca  | 4321|
//    03|982746      | 0.00|        10|salario   |  101|
//-----------------------------------------------------------------------------------------------
//    Gerentes:
//    id|nome  |cpf        |email        |senha|
//    --+------+-----------+-------------+-----+
//    01|Ana   |29381928321|ana@gmail.com| 1234|
//    02|Raissa|31092301923|rai@gmail.com| 4321|

    val menuPrincipalView = MenuPrincipalView()
    menuPrincipalView.start()
}