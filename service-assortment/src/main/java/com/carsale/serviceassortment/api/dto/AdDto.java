package com.carsale.serviceassortment.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdDto {
    private long id;
    private long seller_id;
    private String description;
    private double price;
    private String document;
    private String brand;
    private String model;
    private String color;
    private int year;
    private String transmission;
    private String driveUnit;
    private String fuel;
}
