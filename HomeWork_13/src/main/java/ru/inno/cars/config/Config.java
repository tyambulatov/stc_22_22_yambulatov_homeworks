package ru.inno.cars.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import ru.inno.cars.app.Main;
import ru.inno.cars.repository.CarsRepository;
import ru.inno.cars.repository.impl.CarsRepositoryJdbcImpl;
import ru.inno.cars.service.CarsService;
import ru.inno.cars.service.impl.CarsServiceJdbcImpl;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Getter
public class Config {

    private final CarsService carsService;

    public Config() {
        Properties dbProperties = configProperties();
        DataSource dataSource = configHikariDataSource(dbProperties);
        CarsRepository carsRepository = new CarsRepositoryJdbcImpl(dataSource, 100);
        carsService = new CarsServiceJdbcImpl(carsRepository, "exit");
    }

    private static Properties configProperties() {
        Properties dbProperties = new Properties();

        try {
            dbProperties.load(new BufferedReader(
                    new InputStreamReader(Main.class.getResourceAsStream("/db.properties"))));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return dbProperties;
    }

    private static DataSource configHikariDataSource(Properties dbProperties) {
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
