package ru.inno.cars.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.zaxxer.hikari.HikariDataSource;
import ru.inno.cars.repository.CarsRepository;
import ru.inno.cars.repository.impl.CarsRepositoryJdbcImpl;
import ru.inno.cars.service.CarsService;
import ru.inno.cars.service.impl.CarsServiceJdbcImpl;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Parameters(separators = "=")
public class Main {

    @Parameter(names = {"-action"})
    private String action;

    public static void main(String[] args) {
        Main main = new Main();
        fillAction(main, args);
        CarsService carsService = setupCarsService();

        carsService.performAction(main.action);
    }

    private static void fillAction(Main main, String[] args) {
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
    }

    private static CarsService setupCarsService() {
        DataSource dataSource = setupDataSource();
        CarsRepository carsRepository = new CarsRepositoryJdbcImpl(dataSource);
        return new CarsServiceJdbcImpl(carsRepository);
    }

    private static DataSource setupDataSource() {
        Properties dbProperties = getProperties();
        return setupHikariDataSource(dbProperties);
    }

    private static Properties getProperties() {
        Properties dbProperties = new Properties();

        try {
            dbProperties.load(new BufferedReader(
                    new InputStreamReader(Main.class.getResourceAsStream("/db.properties"))));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return dbProperties;
    }

    private static DataSource setupHikariDataSource(Properties dbProperties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPassword(dbProperties.getProperty("db.password"));
        dataSource.setUsername(dbProperties.getProperty("db.username"));
        dataSource.setJdbcUrl(dbProperties.getProperty("db.url"));
        dataSource.setMaximumPoolSize(
                Integer.parseInt(
                        dbProperties.getProperty("db.hikari.MaxPoolSize")));
        return dataSource;
    }
}
