package com.revature.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.profile.repo.ProfileRepo;
import com.revature.profile.service.ProfileService;

import junit.framework.Assert;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootConfiguration(TrelloV2ProfileServiceApplication.class)
//@SpringApplicationConfiguration(classes = TrelloV2ProfileServiceApplication.class)
@SpringBootTest
public class ProfileServiceTest {
	  @Autowired
	  private ProfileService profileService;
	  
	  @Autowired
	  private ProfileRepo profileRepo;
	  
	  @SuppressWarnings("deprecation")
	  @Test
	  public void testFindByUserId() {
	      Mockito.when(profileRepo.findAll()).thenReturn("Mock user name");        
	      String testName = profileService.getUserName("SomeId");
	      Assert.assertEquals("Mock user name", testName);
	  }
	 
}
