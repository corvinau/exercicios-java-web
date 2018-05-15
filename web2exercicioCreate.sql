CREATE DATABASE WEB2;
USE WEB2;

CREATE TABLE tb_usuario(
	id_usuario int(11) PRIMARY KEY AUTO_INCREMENT,
	login_usuario varchar(20) DEFAULT NULL UNIQUE,
	senha_usuario varchar(80) DEFAULT NULL,
	nome_usuario varchar(50) DEFAULT NULL
)ENGINE=InnoDB;
 
CREATE TABLE tb_estado(
id_estado INT PRIMARY KEY AUTO_INCREMENT,
 nome_estado VARCHAR(100),
 sigla_estado VARCHAR(2)
 );
 
 CREATE TABLE tb_cidade(
id_cidade INT PRIMARY KEY AUTO_INCREMENT, 
nome_cidade VARCHAR(100),
 id_estado INT,
 CONSTRAINT FKESTADO FOREIGN KEY (ID_ESTADO) REFERENCES TB_ESTADO (ID_ESTADO)
 );

CREATE TABLE tb_cliente(
	id_cliente INT(11) PRIMARY KEY AUTO_INCREMENT , 
	cpf_cliente VARCHAR(11) UNIQUE, 
	nome_cliente VARCHAR(100), 
	email_cliente VARCHAR(100) UNIQUE, 
	data_cliente DATE, 
	rua_cliente VARCHAR(100), 
	nr_cliente INT, 
	cep_cliente CHAR(8), 
	ID_CIDADE INT,
    CONSTRAINT FKCIDADE FOREIGN KEY (ID_CIDADE) REFERENCES TB_CIDADE (ID_CIDADE)
);


CREATE TABLE tb_produto(
id_Produto int PRIMARY KEY auto_increment,
nome_Produto VARCHAR(100)
);

CREATE TABLE tb_tipo_atendimento(
id_tipo_atendimento int primary key auto_increment,
nome_tipo_atendimento varchar(100)
);

CREATE TABLE tb_atendimento(
id_atendimento int primary key auto_increment,
dt_hr_atendimento timestamp,
dsc_atendimento varchar(255),
id_produto int,
id_tipo_atendimento int,
id_usuario int,
id_cliente int,
res_atendimento varchar(1),
CONSTRAINT FKPRODUTO FOREIGN KEY (ID_PRODUTO) REFERENCES TB_PRODUTO(ID_PRODUTO),
CONSTRAINT FKTIPOATENDIMENTO FOREIGN KEY (ID_TIPO_ATENDIMENTO) REFERENCES TB_TIPO_ATENDIMENTO(ID_TIPO_ATENDIMENTO),
CONSTRAINT FKUSUARIO FOREIGN KEY (ID_USUARIO) REFERENCES TB_USUARIO(ID_USUARIO),
CONSTRAINT FKCLIENTE FOREIGN KEY (ID_CLIENTE) REFERENCES TB_CLIENTE(ID_CLIENTE)
);



