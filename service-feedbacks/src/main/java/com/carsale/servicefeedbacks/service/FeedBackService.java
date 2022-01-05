package com.carsale.servicefeedbacks.service;

import com.carsale.servicefeedbacks.repo.model.FeedBack;

import java.util.List;

public interface FeedBackService {
    List<FeedBack> fetchAll();
    FeedBack fetchFeedBackById(long id) throws IllegalArgumentException;
    List<FeedBack> fetchAllBySeller(long id) throws IllegalArgumentException;
    List<FeedBack> fetchAllByCustomer(long id) throws IllegalArgumentException;
    long createFeedBack(long customer_id,
                        long seller_id,
                        String description,
                        double rating);
    void updateFeedBack(long id,
                        String description,
                        double rating) throws IllegalArgumentException;
    void deleteFeedBack(long id);
}
