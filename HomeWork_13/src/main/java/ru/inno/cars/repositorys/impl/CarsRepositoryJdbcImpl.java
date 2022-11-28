package ru.inno.cars.repositorys.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.cars.models.Car;
import ru.inno.cars.repositorys.CarsRepository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CarsRepositoryJdbcImpl implements CarsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from car order by id";

    //language=SQL
    private static final String SQL_INSERT = "insert into car(manufacturer, model, model_year, exterior_color, mileage, vin) " +
            "values (?, ?, ?, ?, ?, ?)";

    private final RowMapper<Car> carRowMapper = (row, rowNum) ->
            Car.builder()
                    .manufacturer(row.getString("manufacturer"))
                    .model(row.getString("model"))
                    .modelYear(row.getInt("model_year"))
                    .exteriorColor(row.getString("exterior_color"))
                    .mileage(row.getInt("mileage"))
                    .vin(row.getString("vin"))
                    .build();

    private final JdbcTemplate jdbcTemplate;

    private final int batchSize;

    public CarsRepositoryJdbcImpl(JdbcTemplate jdbcTemplate,
                                  @Value("#{${jdbc.batch.size}}") int batchSize) {
        this.jdbcTemplate = jdbcTemplate;
        this.batchSize = batchSize;
    }

    @Override
    @Transactional
    public void saveAll(List<Car> cars) {
        jdbcTemplate.batchUpdate(SQL_INSERT,
                cars,
                batchSize,
                (PreparedStatement statement, Car car) -> {
                    statement.setString(1, car.getManufacturer());
                    statement.setString(2, car.getModel());
                    statement.setInt(3, car.getModelYear());
                    statement.setString(4, car.getExteriorColor());
                    statement.setInt(5, car.getMileage());
                    statement.setString(6, car.getVin());
                });
    }

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, carRowMapper);
    }
}
