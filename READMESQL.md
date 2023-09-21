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