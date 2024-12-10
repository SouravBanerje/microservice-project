package com.rating_service.rating_service.repositories;

import com.rating_service.rating_service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating,String> {

    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}
