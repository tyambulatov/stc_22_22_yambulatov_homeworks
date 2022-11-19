drop table if exists ride;
drop table if exists car;
drop table if exists user_table;

create table user_table (
    id bigserial primary key,
    first_name varchar(20) not null ,
    last_name varchar(20) not null ,
    phone_number varchar(20) not null ,
    driving_experience integer check ( driving_experience >= 0 and driving_experience <= 120 ),
    age integer check ( age >= 0 and age <= 120 ) not null,
    got_driving_licence bool,
    driving_licence_category varchar(20),
    rating integer check ( rating >= 0 and rating <= 5 )
);

create table car (
    id bigserial primary key,
    model varchar(20),
    colour varchar(20),
    license_plate varchar(20),
    driver_id bigint not null,
    foreign key (driver_id) references user_table(id)
);

create table ride (
    driver_id bigserial not null ,
    car_id bigserial not null,
    ride_date date not null ,
    duration interval not null,
    foreign key (driver_id) references user_table(id),
    foreign key (car_id) references car(id)
);
