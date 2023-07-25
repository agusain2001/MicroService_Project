package com.Rating.Service.service;

import com.Rating.Service.entities.Rating;
import com.Rating.Service.exception.ResourceNotFoundException;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rate);

    List<Rating> showRating(String userId) throws ResourceNotFoundException;

    List<Rating> showAllRating();

    List<Rating> getRatingByHotel(String hotelId);





}
