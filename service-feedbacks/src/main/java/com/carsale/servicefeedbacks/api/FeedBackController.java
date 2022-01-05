package com.carsale.servicefeedbacks.api;

import com.carsale.servicefeedbacks.api.dto.FeedBackDto;
import com.carsale.servicefeedbacks.repo.model.FeedBack;
import com.carsale.servicefeedbacks.service.impl.FeedBackServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedBackController {

    private final FeedBackServiceImpl feedBackService;

    @GetMapping
    public ResponseEntity<List<FeedBack>> index(){
        final List<FeedBack> feedBacks = feedBackService.fetchAll();
        return ResponseEntity.ok(feedBacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedBack> showById(@PathVariable long id){
        try{
            final FeedBack feedBack = feedBackService.fetchFeedBackById(id);

            return ResponseEntity.ok(feedBack);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("seller/{id}")
    public ResponseEntity<List<FeedBack>> showFeedBacksBySellerId(@PathVariable long id){
        try{
            final List<FeedBack> feedBacks = feedBackService.fetchAllBySeller(id);

            return ResponseEntity.ok(feedBacks);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<List<FeedBack>> showFeedBacksByCustomerId(@PathVariable long id){
        try{
            final List<FeedBack> feedBacks = feedBackService.fetchAllByCustomer(id);

            return ResponseEntity.ok(feedBacks);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FeedBackDto feedBackDto){
        final long customerId = feedBackDto.getCustomerId();
        final long sellerId = feedBackDto.getSellerId();
        final String description = feedBackDto.getDescription();
        final double rating = feedBackDto.getRating();

        final long feedBackId = feedBackService.createFeedBack(customerId, sellerId, description, rating);
        String feedBackURI = String.format("/feedbacks/%d", feedBackId);

        return ResponseEntity.created(URI.create(feedBackURI)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody FeedBackDto feedBackDto ){
        final String description = feedBackDto.getDescription();
        final double rating = feedBackDto.getRating();
        try{
            feedBackService.updateFeedBack(id, description, rating);

            return ResponseEntity.noContent().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        feedBackService.deleteFeedBack(id);

        return ResponseEntity.noContent().build();
    }
}
