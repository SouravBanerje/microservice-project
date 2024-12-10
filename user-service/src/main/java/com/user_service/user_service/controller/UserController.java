package com.user_service.user_service.controller;

import com.user_service.user_service.entities.User;
import com.user_service.user_service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<User>   createUser(@RequestBody User user)
    {
        User user1 = userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount  = 1;

    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService" , fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter" , fallbackMethod = "ratingHotelFallback")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry Count: {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    public  ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
   //     logger.info("Fallback is executed because service is down: ",ex.getMessage());
        User user =  User.builder().email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down")
                .userId("141444")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @GetMapping
    public  ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers = userService.getAllUser();
        return ResponseEntity.ok(allUsers);
    }

}
