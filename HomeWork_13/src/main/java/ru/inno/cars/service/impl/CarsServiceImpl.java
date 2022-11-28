package ru.inno.cars.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.inno.cars.models.Car;
import ru.inno.cars.repositorys.CarsRepository;
import ru.inno.cars.service.CarsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;

    private final String inputEndLine;

    public CarsServiceImpl(CarsRepository carsRepository,
                           @Value("${input.end.line}") String inputEndLine) {
        this.carsRepository = carsRepository;
        this.inputEndLine = inputEndLine;
    }

    @Override
    public void printCars() {
        List<Car> carList = carsRepository.findAll();
        carList.forEach(System.out::println);
    }

    @Override
    public void writeCars() {
        printMessage();
        List<Car> carList = readCars();
        carsRepository.saveAll(carList);
    }

    private void printMessage() {
        System.out.println("Enter car lines separated by next line.\nTo indicate end of input type keyword: '" + inputEndLine + "'\n" +
                "Car line consists of data separated by comma in format:\n" +
                "'manufacturer','model','model year','exterior color','mileage','vin'");
    }

    private List<Car> readCars() {
        List<Car> carList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String carLine;
        while (!(carLine = scanner.nextLine()).equals(inputEndLine)) {
            Car car = parseCar(carLine);
            carList.add(car);
        }
        scanner.close();

        return carList;
    }

    private Car parseCar(String carLine) {
        String[] carParameters = carLine.split(",");
        return Car.builder()
                .manufacturer(carParameters[0])
                .model(carParameters[1])
                .modelYear(Integer.parseInt(carParameters[2]))
                .exteriorColor(carParameters[3])
                .mileage(Integer.parseInt(carParameters[4]))
                .vin(carParameters[5])
                .build();
    }
}
