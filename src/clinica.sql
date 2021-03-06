CREATE DATABASE DBCLINICA;

USE DBCLINICA;

CREATE TABLE CAD_PACIENTE (
	CPF_PACIENTE VARCHAR(16),
	NOME_PACIENTE VARCHAR(60),
	SEXO_PACIENTE VARCHAR(10),
	RUA_PACIENTE VARCHAR(100),
	NUMERO_PACIENTE VARCHAR(5),
	COMPLE_PACIENTE VARCHAR(20),
	CIDADE_PACIENTE VARCHAR(20),
	UF_PACIENTE CHAR(2),
	BAIRRO_PACIENTE VARCHAR(40),
	CEL_PACIENTE VARCHAR(15),
	DTANASC_PACIENTE DATE,
	EMAIL_PACIENTE VARCHAR(50),
	SENHA_PACIENTE VARCHAR(20),
    CONSTRAINT PK_CAD_PACIENTE PRIMARY KEY(CPF_PACIENTE)
);

CREATE TABLE CAD_DENTISTA (
    COD_DENTISTA INT,
    NOME_DENTISTA VARCHAR(50),
    ESPEC_DENTISTA VARCHAR(30),
    
    CONSTRAINT PK_CAD_DENTISTA PRIMARY KEY(COD_DENTISTA)
);

CREATE TABLE CAD_CONSULTA (
    COD_CONSULTA INT,
    PACIENTE_CONSULTA VARCHAR(16),
    DENTISTA_CONSULTA INT,
    DTA_CONSULTA DATE,
    HORARIO_CONSULTA VARCHAR(10),
    OBS_CONSULTA TEXT,
	CONSTRAINT PK_CAD_CONSULTA PRIMARY KEY (COD_CONSULTA),
	CONSTRAINT FK_CAD_CONSULTA_CAD_PACIENTE FOREIGN KEY(PACIENTE_CONSULTA)
	REFERENCES CAD_PACIENTE(CPF_PACIENTE),
	CONSTRAINT FK_CAD_DENTISTA_CAD_CONSULTA FOREIGN KEY (DENTISTA_CONSULTA)
	REFERENCES CAD_DENTISTA(COD_DENTISTA)
);

CREATE TABLE CAD_PAG(
    COD_PAG INT AUTO_INCREMENT,
    PACIENTE_PAG VARCHAR(16),
    VL_PAG DECIMAL(5,2),
    DTA_PAG DATE,
    FORMA_PAG VARCHAR(10),
    CONSTRAINT PK_CAD_PAG PRIMARY KEY (COD_PAG),
    CONSTRAINT FK_CAD_PAG_CAD_PACINETE FOREIGN KEY (PACIENTE_PAG)
    REFERENCES CAD_PACIENTE(CPF_PACIENTE)
);

