package com.carsale.servicefeedbacks.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedBackDto {
    private long id;
    private long customerId;
    private long sellerId;
    private String description;
    private double rating;
}
