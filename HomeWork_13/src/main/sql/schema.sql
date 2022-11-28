drop table if exists car;

create table car (
    id bigserial primary key,
    manufacturer varchar(20) not null,
    model varchar(50) not null,
    model_year int not null,
    exterior_color varchar(30) not null,
    mileage int not null,
    vin varchar(17) unique not null
);