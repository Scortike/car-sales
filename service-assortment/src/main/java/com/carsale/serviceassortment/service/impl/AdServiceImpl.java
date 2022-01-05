package com.carsale.serviceassortment.service.impl;

import com.carsale.serviceassortment.api.dto.CommentDto;
import com.carsale.serviceassortment.api.dto.FeedBackDto;
import com.carsale.serviceassortment.repo.AdRepo;
import com.carsale.serviceassortment.repo.model.Ad;
import com.carsale.serviceassortment.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class AdServiceImpl implements AdService {

    private final AdRepo adRepo;

    private String commentsUrlAddress ="http://service-comments:8083/comments/ad/";
    private String feedbacksUrlAddress = "http://service-feedbacks:8081/feedbacks/seller/";

    public List<Ad> fetchAll(){
        return adRepo.findAll();
    }

    public Ad fetchAdById(long id) throws IllegalArgumentException{
        final Optional<Ad> maybeAd = adRepo.findById(id);

        if(maybeAd.isPresent())
            return maybeAd.get();
        else
            throw new IllegalArgumentException("Invalid ad ID");
    }

    public long createAd(long seller_id,
                  String description,
                  double price,
                  String document,
                  String brand,
                  String model,
                  String color,
                  int year,
                  String transmission,
                  String driveUnit,
                  String fuel){
        final Ad ad = Ad.builder()
                .sellerId(seller_id)
                .description(description)
                 .price(price)
                .document(document)
                .brand(brand)
                .model(model)
                .color(color)
                .year(year)
                .transmission(transmission)
                .driveUnit(driveUnit)
                .fuel(fuel)
                .build();
        final Ad savedAd = adRepo.save(ad);
        return savedAd.getId();

    }

    public void updateAd(long id,
                  String description,
                  double price) throws IllegalArgumentException{
        final Optional<Ad> maybeAd = adRepo.findById(id);
        if(maybeAd.isEmpty()) throw new IllegalArgumentException("Ad not found");

        final Ad ad = maybeAd.get();
        if(description != null && !description.isBlank()) ad.setDescription(description);
        if(price >= 0.0) ad.setPrice(price);

        adRepo.save(ad);
    }

    public void deleteAd(long id){
        adRepo.deleteById(id);
    }

    public List<CommentDto> getCommentsByAd(long id) throws IllegalArgumentException{
        final Optional<Ad> maybeAd = adRepo.findById(id);
        if(maybeAd.isEmpty()) throw new IllegalArgumentException("Invalid Ad ID");
        else{
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Long> adRequest = new HttpEntity<>(id);
            final ResponseEntity<List<CommentDto>> adResponse = restTemplate.exchange(commentsUrlAddress + id,
                    HttpMethod.GET, adRequest, new ParameterizedTypeReference<List<CommentDto>>() {
                    });
            List<CommentDto> commentList = adResponse.getBody();
            if(commentList.isEmpty()) throw  new IllegalArgumentException("Comments not found");
            else
                return commentList;
        }
    }

    public double getSellerRatingByAd(long id) throws IllegalArgumentException{
        final Optional<Ad> maybeAd = adRepo.findById(id);
        if(maybeAd.isEmpty()) throw new IllegalArgumentException("Invalid Ad ID");
        else{
            final long sellerId = maybeAd.get().getSellerId();
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Long> adRequest = new HttpEntity<>(sellerId);
            final ResponseEntity<List<FeedBackDto>> adResponse = restTemplate.exchange(feedbacksUrlAddress + id,
                    HttpMethod.GET, adRequest, new ParameterizedTypeReference<List<FeedBackDto>>() {
                    });
            List<FeedBackDto> feedbacksList = adResponse.getBody();
            if(feedbacksList.isEmpty()) throw  new IllegalArgumentException("Comments not found");
            else {
                double sumRating = 0;
                for(int i = 0; i < feedbacksList.size(); i++){
                    sumRating += feedbacksList.get(i).getRating();
                }
                double averageRating = sumRating/feedbacksList.size();
                return averageRating;
            }
        }
    }
}
