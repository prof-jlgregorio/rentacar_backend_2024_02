--create the table
create table if not exists cities (
    id serial not null,
    name varchar(50) not null,
    state char(2) not null,
    constraint pk_city primary key (id)
);

