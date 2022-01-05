package com.carsale.serviceassortment.api;

import com.carsale.serviceassortment.api.dto.AdDto;
import com.carsale.serviceassortment.api.dto.CommentDto;
import com.carsale.serviceassortment.repo.model.Ad;
import com.carsale.serviceassortment.service.impl.AdServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdServiceImpl adService;

    @GetMapping
    public ResponseEntity<List<Ad>> index(){
        final List<Ad> ads = adService.fetchAll();
        return ResponseEntity.ok(ads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> showById(@PathVariable long id){
        try{
            final Ad ad = adService.fetchAdById(id);

            return ResponseEntity.ok(ad);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> showCommentsById(@PathVariable long id){
        try{
            final List<CommentDto> comments = adService.getCommentsByAd(id);
            return ResponseEntity.ok(comments);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/rating")
    public ResponseEntity<Double> showRatingById(@PathVariable long id){
        try{
            final double rating = adService.getSellerRatingByAd(id);
            return ResponseEntity.ok(rating);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AdDto adDto){
        final long seller_id = adDto.getSeller_id();
        final String description = adDto.getDescription();
        final double price = adDto.getPrice();
        final String document = adDto.getDocument();
        final String brand = adDto.getBrand();
        final String model = adDto.getModel();
        final String color = adDto.getColor();
        final int year = adDto.getYear();
        final String transmission = adDto.getTransmission();
        final String driveUnit = adDto.getDriveUnit();
        final String fuel = adDto.getFuel();
        final long adId = adService.createAd(
                seller_id,
                description,
                price,
                document,
                brand,
                model,
                color,
                year,
                transmission,
                driveUnit,
                fuel);
        final String adURI = String.format("ads/%d", adId);

        return ResponseEntity.created(URI.create(adURI)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody AdDto adDto){
        final String description = adDto.getDescription();
        final double price = adDto.getPrice();

        try {
            adService.updateAd(id, description, price);

            return ResponseEntity.noContent().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        adService.deleteAd(id);

        return ResponseEntity.noContent().build();
    }
}
