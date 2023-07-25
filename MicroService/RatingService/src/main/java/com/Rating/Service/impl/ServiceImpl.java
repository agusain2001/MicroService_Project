package com.Rating.Service.impl;

import com.Rating.Service.entities.Rating;
import com.Rating.Service.repository.RatingRepository;
import com.Rating.Service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating addRating(Rating rate) {
        rate.setRatingId(UUID.randomUUID().toString());
        return ratingRepository.save(rate);
    }

    @Override
    public List<Rating> showRating(String userId){
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> showAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
