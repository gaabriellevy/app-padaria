CREATE DATABASE Padaria;

use Padaria;

CREATE TABLE produtos(
	id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    preco double NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE usuarios(
	id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    senha varchar(255) NOT NULL,
    cargo int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE itens(
	id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    preco double NOT NULL,
    qnt int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE vendas(
	id int NOT NULL AUTO_INCREMENT,
    valor int NOT NULL,
    data datetime NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE vendas_rel_itens(
	id int NOT NULL AUTO_INCREMENT,
    id_venda int NOT NULL,
    id_item int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_venda) REFERENCES vendas(id),
    FOREIGN KEY (id_item) REFERENCES itens(id)
);

INSERT INTO usuarios(nome, senha, cargo) VALUES ('gerente', '123', 1);