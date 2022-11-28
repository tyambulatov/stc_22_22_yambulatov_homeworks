package ru.inno.cars.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.inno.cars.config.AppSpringConfig;
import ru.inno.cars.service.CarsService;

@Parameters(separators = "=")
public class Main {

    @Parameter(names = {"-action"})
    private String action;

    public static void main(String[] args) {
        Main main = new Main();
        setAction(main, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);
        CarsService carsService = context.getBean(CarsService.class);

        performAction(carsService, main.action);
    }

    private static void setAction(Main main, String[] args) {
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
