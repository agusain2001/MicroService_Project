package com.Rating.Service.controllers;

import com.Rating.Service.entities.Rating;
import com.Rating.Service.exception.ResourceNotFoundException;
import com.Rating.Service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RateController {
    @Autowired
    ServiceImpl service;
    @PostMapping
    ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addRating(rating));
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<List<Rating>> showRating(@PathVariable String userId) {
        return ResponseEntity.ok(service.showRating(userId));
    }

    @GetMapping
    ResponseEntity<List<Rating>> showAllRating(){
        return ResponseEntity.ok(service.showAllRating());
    }

    @GetMapping("/hotels/{hotelId}")
    ResponseEntity<List<Rating>> getHotelById(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getRatingByHotel(hotelId));
    }


}
