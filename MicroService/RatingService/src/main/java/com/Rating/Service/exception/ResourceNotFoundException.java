package com.Rating.Service.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(){

        super("Resource not found on server!!");
    }
    public ResourceNotFoundException(String name){
        super(name);
    }
}
