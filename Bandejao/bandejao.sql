create database bandejao;

use bandejao;

create table DEPARTAMENTO(
id int primary key auto_increment,
nome varchar(100),
sigla varchar(5));

create table CURSO(
id int primary key auto_increment,
nome varchar(100),
sigla varchar(5),
id_departamento int,
foreign key(id_departamento) references DEPARTAMENTO(id));

create table REFEICAO(
id int primary key auto_increment,
turno varchar(20),
descricao varchar(255),
opcao_vegetariana varchar(255),
tipo varchar(30));

create table CONSUMIDOR(
id int primary key auto_increment,
nome varchar(255),
matricula int not null,
ano_ingresso int,
sexo varchar(20),
titulo varchar(30),
cpf varchar(11),
id_curso int,
id_departamento int,
foreign key(id_curso) references CURSO(id),
foreign key(id_departamento) references DEPARTAMENTO(id));

create table TICKET(
id int primary key auto_increment,
id_consumidor int,
valor float,
id_refeicao int,
pago int,
foreign key(id_consumidor) references CONSUMIDOR(id),
foreign key(id_refeicao) references REFEICAO(id));
