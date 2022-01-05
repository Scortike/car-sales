package com.carsale.servicefeedbacks.service.impl;

import com.carsale.servicefeedbacks.repo.FeedBackRepo;
import com.carsale.servicefeedbacks.repo.model.FeedBack;
import com.carsale.servicefeedbacks.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackRepo feedBackRepo;

    public List<FeedBack> fetchAll(){
        return feedBackRepo.findAll();
    }

    public FeedBack fetchFeedBackById(long id){
        final Optional<FeedBack> maybeFeedBack = feedBackRepo.findById(id);
        if(maybeFeedBack.isPresent())
            return maybeFeedBack.get();
        else
            throw new IllegalArgumentException("Invalid feedBack id");
    }

    public List<FeedBack> fetchAllBySeller(long id) throws IllegalArgumentException{
        final List<FeedBack> maybeFeedBacks = feedBackRepo.findBySellerId(id);
        if(maybeFeedBacks.isEmpty()) throw new IllegalArgumentException("Invalid Seller ID");
        else return maybeFeedBacks;
    }

    public List<FeedBack> fetchAllByCustomer(long id) throws IllegalArgumentException{
        final List<FeedBack> maybeFeedBacks = feedBackRepo.findByCustomerId(id);
        if(maybeFeedBacks.isEmpty()) throw new IllegalArgumentException("Invalid Customer ID");
        else return maybeFeedBacks;
    }

    public long createFeedBack(long customer_id, long seller_id, String description, double rating) {
        final FeedBack feedBack = FeedBack.builder()
                .customerId(customer_id)
                .sellerId(seller_id)
                .description(description)
                .rating(rating).build();
        final FeedBack saveFeedBack = feedBackRepo.save(feedBack);

        return saveFeedBack.getId();
    }

    public void updateFeedBack(long id, String description, double rating){
        final Optional<FeedBack> maybeFeedBack = feedBackRepo.findById(id);
        if(maybeFeedBack.isEmpty()) throw new IllegalArgumentException("Invalid FeedBack ID");

        final FeedBack feedBack = maybeFeedBack.get();
        if(description != null && !description.isBlank()) feedBack.setDescription(description);
        if(rating >= 0.0 && rating <= 5.0) feedBack.setRating(rating);

        feedBackRepo.save(feedBack);
    }

    public void deleteFeedBack(long id) {
        feedBackRepo.deleteById(id);
    }
}
