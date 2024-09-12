CREATE TABLE videos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    descricao varchar(1000) not null,
    url varchar(100) not null,
    ativo tinyint not null,

    primary key(id)
);