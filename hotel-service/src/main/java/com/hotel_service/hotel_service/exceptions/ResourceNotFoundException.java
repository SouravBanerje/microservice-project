package com.hotel_service.hotel_service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }
    public  ResourceNotFoundException(){
        super("Resources not Found !!");
    }
}
