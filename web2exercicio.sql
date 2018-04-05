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
VALUES  ('11111111111','Arthur','arthur@gmail.com','1998-07-31','rua tal',444,'11111111','Curitiba','PR'),
		('22222222222','Tatiane','tati@gmail.com','1998-08-15','rua lat',449,'22222222','Curitiba','PR'),
		('33333333333','Gustavo','gugu@gmail.com','1998-04-23','rua tabela',448,'33333333','Curitiba','PR'),
		('44444444444','Lucas','lucs@gmail.com','1998-03-31','rua numerica',447,'44444444','Curitiba','PR'),
		('55555555555','Luis','siul@gmail.com','1998-07-14','rua salgada',446,'55555555','Curitiba','PR'),
		('66766766766','Leticia','leticia@gmail.com','1998-02-01','rua java',445,'66766766','Curitiba','PR'),
		('77777777777','Gabriele','Gele@gmail.com','1998-01-02','rua web',444,'88888888','Curitiba','PR'),
		('88888888888','Renan','renamon@gmail.com','1998-10-29','rua hiper',443,'99999999','Curitiba','PR'),
		('99999999999','Razer','razer@gmail.com','1998-11-11','rua longa',442,'77777777','Curitiba','PR'),
		('10101010101','Sara','sare@gmail.com','1998-12-31','rua loca',441,'10101010','Curitiba','PR');