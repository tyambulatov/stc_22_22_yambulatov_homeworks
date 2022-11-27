package ru.inno.cars.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.inno.cars.config.Config;
import ru.inno.cars.service.CarsService;

@Parameters(separators = "=")
public class Main {

    @Parameter(names = {"-action"})
    private String action;

    public static void main(String[] args) {
        Main main = new Main();
        fillAction(main, args);
        CarsService carsService = new Config().getCarsService();

        performAction(carsService, main.action);
    }

    private static void fillAction(Main main, String[] args) {
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
    }

    public static void performAction(CarsService carsService, String action) {
        switch (action) {
            case "read" -> carsService.printCars();
            case "write" -> carsService.writeCars();
            default -> throw new IllegalArgumentException("Incorrect action parameter");
        }
    }
}
