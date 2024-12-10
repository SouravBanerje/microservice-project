package com.user_service.user_service.services.impl;

import com.user_service.user_service.entities.Hotel;
import com.user_service.user_service.entities.Rating;
import com.user_service.user_service.entities.User;
import com.user_service.user_service.exceptions.ResourceNotFoundException;
import com.user_service.user_service.external.service.HotelService;
import com.user_service.user_service.repositories.UserRepository;
import com.user_service.user_service.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user=  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found"));


        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel


            // RestTemplate
            // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();

            // feign
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

           //logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
