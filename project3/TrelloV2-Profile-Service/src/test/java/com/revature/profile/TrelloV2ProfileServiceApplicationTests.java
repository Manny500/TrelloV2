package com.revature.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import org.junit.Assert;

import com.revature.profile.bean.TV2User;
import com.revature.profile.service.ProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloV2ProfileServiceApplicationTests {

	@Autowired
	private ProfileService service;
	
	@Test
	public void contextLoads() {
		assertNotNull(service);
	}

	@Test
	public void findByUserId() {
		int userId = 1;
		TV2User user = service.findByUserId(userId);
		Assert.assertEquals("John",user.getFirstName());
		
	}
}
