package com.revature.profile;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;
import com.revature.profile.service.ProfileService;

import junit.framework.Assert;


//@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrelloV2ProfileServiceApplication.class)
public class ProfileServiceTest {
	
	
	@Autowired
	private ProfileService profileService;
	@Autowired
	private ProfileRepo profileRepoMock;
	
	private static final int uId = 1;
	private static final String firstName = "John";
	private static final String lastName = "Snow";

	@Before
	public void setUp() {
		profileService = new ProfileService();
		
		profileRepoMock = mock(ProfileRepo.class);
		profileService.setProfileRepo(profileRepoMock);
	}
	
	@Test
	public void testFindByUserId() {
		TV2User user = ProfileTestUtil.createModelObject(uId, firstName, lastName);
		when(profileRepoMock.findByUserId(uId)).thenReturn(user);
		
		TV2User returned =  profileService.findByUserId(uId);
		
		verify(profileRepoMock, times(1)).findByUserId(uId);
		verifyNoMoreInteractions(profileRepoMock);
		
		assertEquals(user, returned);
		
	}

	
//	@Test
//	public void testFindAll() {
//		List all = new LinkedList();
//		all.add(new TV2User(uId, firstName, lastName));
//		all.add(new TV2User(2, "Margaery", "Tyrell"));
//		
//		//Mock Alert: return mocked result set on find
//		when(profileRepoMock.findAll()).thenReturn(all);
//		
//		//call the main method you want to test
//		List result = profileService.findAll();
//		
//		//Mock Alert: verify the method was caled
//		verify(profileRepoMock).findAll();
//	}
}
