package com.carsale.serviceassortment.service;

import com.carsale.serviceassortment.api.dto.CommentDto;
import com.carsale.serviceassortment.repo.model.Ad;

import java.util.List;

public interface AdService {
    List<Ad> fetchAll();
    Ad fetchAdById(long id) throws IllegalArgumentException;
    long createAd(long seller_id,
                  String description,
                  double price,
                  String document,
                  String brand,
                  String model,
                  String color,
                  int year,
                  String transmission,
                  String driveUnit,
                  String fuel);
    void updateAd(long id,
                  String description,
                  double price) throws IllegalArgumentException;
    void deleteAd(long id);
    List<CommentDto> getCommentsByAd(long id) throws IllegalArgumentException;
    double getSellerRatingByAd(long id) throws IllegalArgumentException;
}
