package com.carsale.serviceassortment.repo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long sellerId;
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
