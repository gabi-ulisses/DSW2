/*
 * SGBD: MySQL
 * 
 * Este arquivo contém:
 * - Definição da estrutura do banco de dados `loja` (tabelas).
 * - Massa de dados para testes.
 * - Exemplos de consultas. 
 */

DROP DATABASE IF EXISTS loja;
CREATE DATABASE loja;
USE loja;


-- tabelas

create table produto (
	id int not null auto_increment,
	descricao varchar(40) not null,
    preco decimal(7, 2) not null,
    primary key (id)
);

create table cliente (
	id int not null auto_increment,
    nome varchar(40) not null,
    email varchar(80),
    primary key (id)
);

create table pedido (
	id int not null auto_increment,
    cliente_id int not null,
    dt_criacao datetime not null,
    primary key (id)
);
alter table pedido add constraint fk_cliente foreign key (cliente_id) references cliente(id);

create table pedido_item (
	id int not null auto_increment,
    pedido_id int not null,
    produto_id int not null,
    quantidade int not null,
    primary key (id)
);
alter table pedido_item add constraint fk_pedido foreign key (pedido_id) references pedido(id);
alter table pedido_item add constraint fk_produto foreign key (produto_id) references produto(id);
alter table pedido_item add constraint ck_quantidade check (quantidade > 0);



-- dados de teste --

-- produtos
insert into produto (id, descricao, preco) values (1, 'Teclado USB', 142.50);
insert into produto (id, descricao, preco) values (2, 'Fone Bluetooth', 175);
insert into produto (id, descricao, preco) values (3, 'Pen drive', 62.10);
insert into produto (id, descricao, preco) values (4, 'Monitor HDMI', 780);

-- clientes
insert into cliente (id, nome, email) values (1, 'Juvenal Junqueira', 'juvenal@freemail.com');
insert into cliente (id, nome, email) values (2, 'Mário Martins', 'mario@freemail.com');
insert into cliente (id, nome, email) values (3, 'Norberto Nogueira', 'norberto@freemail.com');

-- pedido com itens
insert into pedido (id, cliente_id, dt_criacao) values (1, 1, now());
insert into pedido_item(id, pedido_id, produto_id, quantidade) values (1, 1, 1, 2);
insert into pedido_item(id, pedido_id, produto_id, quantidade) values (2, 1, 2, 1);
insert into pedido_item(id, pedido_id, produto_id, quantidade) values (3, 1, 3, 3);


-- consultas de teste
select id, descricao from produto;
select * from pedido_item;
select * from cliente;

select p.descricao, it.quantidade, p.preco, (it.quantidade * p.preco) as subtotal 
from produto p, pedido_item it where it.produto_id = p.id and it.pedido_id = 1;
