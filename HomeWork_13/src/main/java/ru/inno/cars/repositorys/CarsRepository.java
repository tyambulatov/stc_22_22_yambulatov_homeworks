package ru.inno.cars.repositorys;

import ru.inno.cars.models.Car;

import java.util.List;

public interface CarsRepository {
    void saveAll(List<Car> cars);

    List<Car> findAll();
}
