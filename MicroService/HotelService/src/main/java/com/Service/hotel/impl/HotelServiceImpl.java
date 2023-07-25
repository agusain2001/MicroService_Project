package com.Service.hotel.impl;

import com.Service.hotel.entities.Hotel;
import com.Service.hotel.exception.ResourceNotFoundException;
import com.Service.hotel.repositories.HotelRepository;
import com.Service.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return this.hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This is not found"));
    }
}
