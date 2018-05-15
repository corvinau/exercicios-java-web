
INSERT INTO tb_usuario(login_usuario,senha_usuario,nome_usuario) 
VALUES 	('ArtVin','202CB962AC59075B964B07152D234B70','Arthur Vianna'),
		('tatiane','202CB962AC59075B964B07152D234B70','Tatiane Medeiros'),
        ('hey','202CB962AC59075B964B07152D234B70','Marcelo Hey');

INSERT INTO tb_cliente	(cpf_cliente,nome_cliente,
						 email_cliente,data_cliente,
						 rua_cliente,nr_cliente,
						 cep_cliente,id_cidade) 
VALUES  ('19800371591','Arthur','arthur@gmail.com','1998-07-31','rua tal',444,'11111111',4106902),
		('25648993072','Tatiane','tati@gmail.com','1998-08-15','rua lat',449,'22222222',4106902),
		('85408301591','Gustavo','gugu@gmail.com','1998-04-23','rua tabela',448,'33333333',4106902),
		('29218517090','Lucas','lucs@gmail.com','1998-03-31','rua numerica',447,'44444444',4106902),
		('17871307460','Luis','siul@gmail.com','1998-07-14','rua salgada',446,'55555555',4106902),
		('04244499270','Leticia','leticia@gmail.com','1998-02-01','rua java',445,'66766766',4106902),
		('65212520118','Gabriele','Gele@gmail.com','1998-01-02','rua web',444,'88888888',4106902),
		('47731493105','Renan','renamon@gmail.com','1998-10-29','rua hiper',443,'99999999',4106902),
		('46384464853','Razer','razer@gmail.com','1998-11-11','rua longa',442,'77777777',4106902),
		('78900268970','Sara','sare@gmail.com','1998-12-31','rua loca',441,'10101010',4106902);
        


INSERT INTO TB_PRODUTO (NOME_PRODUTO)
VALUES  ('Batata'),
		('Cenoura'),
		('Cebola'),
		('Alface'),
		('Brocolis'),
		('Picles'),
		('Batata Doce'),
		('Laranja'),
		('Maca'),
		('Mamao');
        
INSERT INTO TB_TIPO_ATENDIMENTO(NOME_TIPO_ATENDIMENTO)
VALUES ('Reclamacao'),
       ('Sugestao'),
       ('Duvida'),
	   ('Elogio');