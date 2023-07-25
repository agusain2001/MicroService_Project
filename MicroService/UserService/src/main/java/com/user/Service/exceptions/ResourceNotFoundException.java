package com.user.Service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found on server!!");
    }
    public ResourceNotFoundException(String name){
        super(name);
    }
}
