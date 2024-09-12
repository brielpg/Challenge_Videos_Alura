CREATE TABLE categorias(
    id bigint not null auto_increment,
    titulo varchar(50) not null,
    cor varchar(50) not null,
    ativo tinyint not null,

    primary key(id)
);