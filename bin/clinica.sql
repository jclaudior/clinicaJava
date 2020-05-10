create database dbclinica;

use dbclinica;

create table PACIENTE (
	cpf varchar(16),
	nome varchar(60),
	sexo varchar(10),
	rua varchar(100),
	numero varchar(5),
	complemento varchar(20),
	cidade varchar(20),
	uf char(2),
	bairro varchar(40),
	celular varchar(15),
	dataNasc varchar(20),
	email varchar(50),
	senha varchar(20),
    
    constraint PK_Paciente primary key(cpf)
);

create table DENTISTA (
	codDentista int,
    nomeDentista varchar(50),
    especialidade varchar(30),
    
    constraint PK_Dentista primary key(codDentista)
);

create table CONSULTA (
	codConsulta int,
    paciente_FK varchar(16),
    dentista_FK int,
    dia DATE,
    horario varchar(10),
    obs text,
	constraint PK_Consulta primary key (codConsulta),
	constraint FK_Paciente foreign key(paciente_FK)
	references paciente(cpf),
	constraint FK_Dentista foreign key (dentista_FK)
	references dentista(codDentista)
);

create table PAGAMENTO (
	codPagamento int,
    paciente_FK varchar(16),
    valor decimal(5,2),
    dia int,
    formaPagamento varchar(10),
    
    constraint PK_Pagamento primary key (codPagamento),
    constraint FK_Paciente foreign key (paciente_FK)
    references paciente(cpf)
);
