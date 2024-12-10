package com.hotel_service.hotel_service.controllers;


import com.hotel_service.hotel_service.entities.Hotel;
import com.hotel_service.hotel_service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> createHotel(@PathVariable String hotelId){
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.get(hotelId));
    }

    @GetMapping
    public  ResponseEntity<List<Hotel>> getAll(){
        return  ResponseEntity.ok(hotelService.getAll());
    }

}
