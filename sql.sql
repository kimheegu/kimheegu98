create database servletex;
use servletex;

create table rev
(
id int auto_increment primary key,
title varchar(50) not null,
content varchar(50) not null,
num varchar(50) not null,
chain varchar(50) not null,
numm int not null
);

create table movieList
(
id int auto_increment primary key,
title varchar(40) not null,
content varchar(40) not null,
num long not null
);