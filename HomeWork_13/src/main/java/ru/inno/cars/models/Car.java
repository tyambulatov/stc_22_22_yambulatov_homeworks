package ru.inno.cars.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private String manufacturer;
    private String model;
    private Integer modelYear;
    private String exteriorColor;
    private Integer mileage;
    private String vin;
}
