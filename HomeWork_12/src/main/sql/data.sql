insert into account (first_name, last_name, phone_number, driving_experience, age, has_license, driving_licence_category, rating)
values ('Jean-Paul', 'Marat', '+337942768899', 4, 50, true, 'B', 3.3),
       ('Maximilien', 'Robespierre', '+339982289901', 10, 36, true, 'A', 5),
       ('Louis', 'Saint-Just', '+334449092990', 11, 27, true, 'C', 2.1),
       ('Georges', 'Danton', '+339912229922', 3, 45, true, 'C', 0);
;

insert into account (first_name, last_name, phone_number, age, has_license)
values ('Louis', 'Capet', '+339920099231', 38, false);

insert into car (model, color, license_plate, owner_id)
values ('Adder', 'Black', 'AA-229-DB', 5),
       ('Brawler', 'Ivory', 'CA-221-AL', 4),
       ('Comet', 'Green', 'KL-429-LA', 4),
       ('Daemon', 'Navy', 'LK-555-LL', 1),
       ('Picador', 'Blue', 'LA-525-AL', 2);

insert into ride (owner_id, car_id, ride_date, duration)
values (1, 1, '1791-01-02', '04:04:01'),
       (2, 5, '1931-01-14', '15:09:01'),
       (3, 5, '1945-11-10', '09:09:01'),
       (3, 1, '2012-09-11', '00:09:01'),
       (4, 3, '2022-12-19', '12:09:01');