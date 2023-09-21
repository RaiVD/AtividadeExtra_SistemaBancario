-- Tabela Cliente
CREATE TABLE Cliente (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
cpf VARCHAR(14) UNIQUE NOT NULL,
endereco TEXT,
-- Outros campos de informações do cliente, se necessário
);

-- Tabela ContaBancaria
CREATE TABLE ContaBancaria (
id INT PRIMARY KEY AUTO_INCREMENT,
numero_conta VARCHAR(20) UNIQUE NOT NULL,
saldo DECIMAL(10, 2) DEFAULT 0.00,
cliente_id INT,
FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

-- Tabela Transação
CREATE TABLE Transacao (
id INT PRIMARY KEY AUTO_INCREMENT,
data DATETIME NOT NULL,
valor DECIMAL(10, 2) NOT NULL,
conta_origem_id INT,
conta_destino_id INT,
FOREIGN KEY (conta_origem_id) REFERENCES ContaBancaria(id),
FOREIGN KEY (conta_destino_id) REFERENCES ContaBancaria(id)
);

Comandos SQL que foram usados:

- "SELECT saldo FROM conta_bancaria WHERE id = ?"
- "UPDATE conta_bancaria SET saldo = ? WHERE id = ?"
- "INSERT INTO Cliente (nome, cpf, endereco) VALUES ('$nome', '$cpf', ?)"
- "DELETE FROM Cliente WHERE id=$id"
- "SELECT id, nome, cpf, endereco FROM Cliente"
- "INSERT INTO conta_bancaria (numero_conta, saldo, cliente_id, tipo_conta, senha) VALUES (?, ?, ?, ?, ?)"
- "DELETE FROM conta_bancaria WHERE id=$id"
- "SELECT id, numero_conta, saldo, cliente_id, tipo_conta FROM conta_bancaria"
- "SELECT id, numero_conta, saldo, cliente_id, tipo_conta FROM conta_bancaria WHERE id=?"
- "INSERT INTO Transacao (data, valor, conta_origem_id, conta_destino_id) VALUES (?, ?, ?, ?)"
- "UPDATE conta_bancaria SET saldo = saldo - ? WHERE id = ?"
- "UPDATE conta_bancaria SET saldo = saldo + ? WHERE id = ?"
- "DELETE FROM Transacao WHERE id=$id"
- "SELECT id, data, valor, conta_origem_id, conta_destino_id FROM Transacao"
- "SELECT id, data, valor, conta_origem_id, conta_destino_id FROM Transacao WHERE id = ?"
