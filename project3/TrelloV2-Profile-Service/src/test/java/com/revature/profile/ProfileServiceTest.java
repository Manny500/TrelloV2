package com.revature.profile;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;
import com.revature.profile.service.ProfileService;

import junit.framework.Assert;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrelloV2ProfileServiceApplication.class)
public class ProfileServiceTest {
	
	@Autowired
	private ProfileService profileService;
	@Autowired
	private ProfileRepo profileRepo;

	@SuppressWarnings("deprecation")
	@Test
	public void findByUserId() {
		TV2User mockUser = new TV2User();
		Mockito.when( Mockito.mock(ProfileRepo.class).findByUserId(1)).thenReturn(mockUser);
		TV2User user = profileService.findByUserId(1);
		Assert.assertEquals("John", user.getFirstName());
	}
}
