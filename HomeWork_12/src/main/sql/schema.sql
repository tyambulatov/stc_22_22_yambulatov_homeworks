drop table if exists ride;
drop table if exists car;
drop table if exists account;

create table account (
    id bigserial primary key,
    first_name varchar(20) not null ,
    last_name varchar(20) not null ,
    phone_number varchar(20) not null ,
    driving_experience integer,
    age integer not null,
    has_license bool,
    driving_licence_category varchar(20),
    rating real check ( rating >= 0 and rating <= 5 )
);

create table car (
    id bigserial primary key,
    model varchar(20),
    color varchar(20),
    license_plate varchar(20),
    owner_id bigint not null,
    foreign key (owner_id) references account(id)
);

create table ride (
    owner_id bigserial not null ,
    car_id bigserial not null,
    ride_date date not null ,
    duration interval not null,
    foreign key (owner_id) references account(id),
    foreign key (car_id) references car(id)
);
