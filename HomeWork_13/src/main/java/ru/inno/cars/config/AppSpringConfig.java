package ru.inno.cars.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = "ru.inno.cars")
public class AppSpringConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        dataSource.setMaximumPoolSize(environment.getProperty("db.hikari.MaxPoolSize", Integer.class));

        return dataSource;
    }
}
