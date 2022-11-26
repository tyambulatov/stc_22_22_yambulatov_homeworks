package ru.inno.cars.repository.impl;

import lombok.RequiredArgsConstructor;
import ru.inno.cars.models.Car;
import ru.inno.cars.repository.CarsRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CarsRepositoryJdbcImpl implements CarsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from car order by id";

    //language=SQL
    private static final String SQL_INSERT = "insert into car(manufacturer, model, model_year, exterior_color, mileage, vin) " +
            "values (?, ?, ?, ?, ?, ?)";

    private final DataSource dataSource;

    @Override
    public void save(Car car) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, car.getManufacturer());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getModelYear());
            statement.setString(4, car.getExteriorColor());
            statement.setInt(5, car.getMileage());
            statement.setString(6, car.getVin());

            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Can't insert Car");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
                while (resultSet.next()) {
                    Car car = parseCar(resultSet);
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return cars;
    }

    private static Car parseCar(ResultSet row) {
        try {
            return Car.builder()
                    .manufacturer(row.getString("manufacturer"))
                    .model(row.getString("model"))
                    .modelYear(row.getInt("model_year"))
                    .exteriorColor(row.getString("exterior_color"))
                    .mileage(row.getInt("mileage"))
                    .vin(row.getString("vin"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
