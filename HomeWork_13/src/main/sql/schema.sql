drop table if exists car;

create table car (
    id bigserial primary key,
    manufacturer varchar(20) not null,
    model varchar(50) not null,
    model_year int not null check (model_year >= 1886),
    exterior_color varchar(30) not null,
    mileage int check ( mileage >= 0 ) not null,
    vin char(17) unique not null
);