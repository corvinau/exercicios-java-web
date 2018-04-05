CREATE DATABASE WEB2;
USE WEB2;

CREATE TABLE tb_usuario(
	id_usuario int(11) PRIMARY KEY AUTO_INCREMENT,
	login_usuario varchar(20) DEFAULT NULL,
	senha_usuario varchar(20) DEFAULT NULL,
	nome_usuario varchar(50) DEFAULT NULL
)ENGINE=InnoDB;

CREATE TABLE tb_cliente(
	id_cliente INT(11) PRIMARY KEY AUTO_INCREMENT , 
	cpf_cliente VARCHAR(11), 
	nome_cliente VARCHAR(100), 
	email_cliente VARCHAR(100), 
	data_cliente DATE, 
	rua_cliente VARCHAR(100), 
	nr_cliente INT, 
	cep_cliente CHAR(8), 
	cidade_cliente VARCHAR(100), 
	uf_cliente CHAR(2)	
);

INSERT INTO tb_usuario(login_usuario,senha_usuario,nome_usuario) 
VALUES 	('ArtVin','123','Arthur Vianna'),
		('tatiane','123456','Tatiane Medeiros'),
        ('hey','123456','Marcelo Hey');

INSERT INTO tb_cliente	(cpf_cliente,nome_cliente,
						 email_cliente,data_cliente,
						 rua_cliente,nr_cliente,
						 cep_cliente,cidade_cliente,
						 uf_cliente) 
VALUES ('11111111111','Arthur','arthur@gmail.com','1998-07-31','rua tal',444,'11111111','Curitiba','PR');