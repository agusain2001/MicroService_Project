package com.user.Service;

import com.user.Service.entities.Rating;
import com.user.Service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void CreteRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("this is test case").build();
		ratingService.createRating(rating)
		System.out.println("new created");
	}


}
