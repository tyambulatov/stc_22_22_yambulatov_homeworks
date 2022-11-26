package ru.inno.cars.repository;

import ru.inno.cars.models.Car;

import java.util.List;

public interface CarsRepository {
    void save(Car car);

    List<Car> findAll();
}
