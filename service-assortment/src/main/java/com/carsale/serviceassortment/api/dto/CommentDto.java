package com.carsale.serviceassortment.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private long id;
    private long customerId;
    private long adId;
    private String description;
}
