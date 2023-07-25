package com.user.Service.controllers;

import com.user.Service.entities.User;
import com.user.Service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static org.springframework.aop.scope.ScopedProxyBeanRegistrationAotProcessor.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
//    @CircuitBreaker(name= "ratingHotelBeaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name= "ratingHotelBeaker", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user= userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
int count=0;
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
//        logger.info("Fallback is executed because service is down",ex.getMessage());
        Logger logger = LoggerFactory.getLogger(UserController.class);
        // Replace YourClassName with the name of the current class where the method is defined.

//        logger.info("This count" + count, ex.getMessage());
//        count++;
        User user= User.builder().email("dummy@gmail.com").name("dummy").about("this is dummy").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }


}
