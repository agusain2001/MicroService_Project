package com.user.Service.imp;

import com.user.Service.Repositary.UserRepositary;
import com.user.Service.entities.Hotel;
import com.user.Service.entities.Rating;
import com.user.Service.entities.User;
import com.user.Service.exceptions.ResourceNotFoundException;
import com.user.Service.external.services.HotelService;
import com.user.Service.services.UserService;
import org.slf4j.Logger;
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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepositary userRepositary;
    @Override
    public User saveUser(User user) {
        String s = UUID.randomUUID().toString();
        user.setUserId(s);
        userRepositary.save(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users= userRepositary.findAll();
        for (User user:users){
            Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
            List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

            List<Rating> ratingList = ratings.stream().map(rating -> {
                Hotel hotel =hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);

                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);

        }
        return users;
    }

    @Override
    public User getUser(String userId) {

        User user = userRepositary.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        System.out.println(user);
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+userId, Rating[].class);

//        logger.info("{} ",ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
//            http://localhost:8081/hotels/c650f742-fc8a-4c35-9d65-c39a7ce24fbb
//            ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);

            Hotel hotel =hotelService.getHotel(rating.getHotelId());
//            logger.info("{} ",forEntity.getStatusCode());
            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());


        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteAllUser() {
        this.userRepositary.deleteAll();
    }

    @Override
    public void deleteUser(String userId) {
        userRepositary.deleteById(userId);
    }

    @Override
    public User updateUser(User u) {
        return null;
    }
}
