package ru.inno.cars.service.impl;

import lombok.RequiredArgsConstructor;
import ru.inno.cars.models.Car;
import ru.inno.cars.repository.CarsRepository;
import ru.inno.cars.service.CarsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CarsServiceJdbcImpl implements CarsService {

    private final CarsRepository carsRepository;

    @Override
    public void performAction(String action) {
        switch (action) {
            case "read" -> printCars();
            case "write" -> writeCar();
            default -> throw new IllegalArgumentException("Incorrect action parameter");
        }
    }

    private void printCars() {
        List<Car> carList = carsRepository.findAll();
        carList.forEach(System.out::println);
    }

    private void writeCar() {
        printMessage();
        Car car = readCar();
        carsRepository.save(car);
    }

    private void printMessage() {
        System.out.println("""
                Enter car data separated by new line in format:
                'manufacturer'
                'model'
                'model year'
                'exterior color'
                'mileage'
                'vin'""");
    }

    private Car readCar() {
        List<String> carList = readCarList();
        return buildCar(carList);
    }

    private List<String> readCarList() {
        List<String> carList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int numberOfColumns = 6;
        for (int i = 0; i < numberOfColumns; i++) {
            carList.add(scanner.nextLine());
        }
        scanner.close();

        return carList;
    }

    private Car buildCar(List<String> carList) {
        return Car.builder()
                .manufacturer(carList.get(0))
                .model(carList.get(1))
                .modelYear(Integer.parseInt(carList.get(2)))
                .exteriorColor(carList.get(3))
                .mileage(Integer.parseInt(carList.get(4)))
                .vin(carList.get(5))
                .build();
    }
}
