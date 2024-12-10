package com.rating_service.rating_service.services;

import com.rating_service.rating_service.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    List<Rating> getRating();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);
}
