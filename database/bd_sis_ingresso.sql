/*USUARIO*/
DROP USER IF EXISTS 'usuario'@'localhost';
CREATE USER 'usuario'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON *.* TO 'usuario'@'localhost' identified by '123456' with grant option;
FLUSH PRIVILEGES;

/*BANCO DE DADOS*/
DROP DATABASE IF EXISTS SisIngressos;
CREATE DATABASE SisIngressos CHARACTER SET utf8 COLLATE utf8_general_ci;
USE SisIngressos;
 
CREATE TABLE funcao (
    id_funcao	INT NOT NULL AUTO_INCREMENT,
    nm_funcao	VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_funcao)
)  DEFAULT CHARSET = utf8;

CREATE TABLE usuario (    
    nm_login_usuario 	VARCHAR(45),
    cd_senha_usuario	VARCHAR(128),
    nm_usuario			VARCHAR(45),
    id_funcao			INT,
    PRIMARY KEY (nm_login_usuario),
    FOREIGN KEY (id_funcao) REFERENCES funcao(id_funcao)
) DEFAULT CHARSET = utf8;

CREATE TABLE evento (
	id_evento			 		INT NOT NULL AUTO_INCREMENT,
    nm_evento					VARCHAR(45),
    dt_inicio_evento 			DATE,
    hr_inicio_evento			TIME,
	nm_endereco_evento			VARCHAR(150),
    qt_ingresso_evento			INT,
    vl_ingresso_evento			FLOAT,
    PRIMARY KEY (id_evento)
) DEFAULT CHARSET = utf8;

CREATE TABLE tipo_ingresso (
	id_tipo_ingresso 	INT NOT NULL AUTO_INCREMENT,
    nm_tipo_ingresso	VARCHAR(45),
    PRIMARY KEY (id_tipo_ingresso)
) DEFAULT CHARSET = utf8;

CREATE TABLE cliente (
	id_cliente				INT NOT NULL AUTO_INCREMENT,
    cd_cpf_cnpj_cliente 	VARCHAR(14),
    nm_cliente 				VARCHAR(45),
    PRIMARY KEY (id_cliente)
) DEFAULT CHARSET = utf8;

CREATE TABLE venda (
	id_venda				INT NOT NULL,
	dt_venda				DATETIME DEFAULT NOW(),
    id_cliente				INT,
    nm_login_usuario		VARCHAR(45),
    FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
    FOREIGN KEY (nm_login_usuario) REFERENCES usuario (nm_login_usuario),
    PRIMARY KEY (id_venda)
) DEFAULT CHARSET = utf8;

CREATE TABLE ingresso (
	id_ingresso			INT NOT NULL AUTO_INCREMENT, 
    id_venda			INT,
	id_evento			INT,    
    id_tipo_ingresso	INT,
    vl_ingresso			FLOAT,
    FOREIGN KEY (id_evento) REFERENCES evento (id_evento),
    FOREIGN KEY (id_tipo_ingresso) REFERENCES tipo_ingresso (id_tipo_ingresso),
    PRIMARY KEY (id_ingresso, id_venda)
) DEFAULT CHARSET = utf8;

INSERT INTO cliente (id_cliente, cd_cpf_cnpj_cliente, nm_cliente) VALUES (1, 0, 'Ignorado');
INSERT INTO funcao VALUES (1, 'Administrador');
INSERT INTO funcao VALUES (2, 'Vendedor');
INSERT INTO tipo_ingresso VALUES (1, 'Inteiro');
INSERT INTO tipo_ingresso VALUES (2, 'Meia Comum');
INSERT INTO tipo_ingresso VALUES (3, 'VIP');
INSERT INTO tipo_ingresso VALUES (4, 'Meia VIP');