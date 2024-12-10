package com.hotel_service.hotel_service.services;

import com.hotel_service.hotel_service.entities.Hotel;

import java.util.List;


public interface HotelService {

    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel get(String id);
}
