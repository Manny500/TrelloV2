package com.revature.profile;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;
import com.revature.profile.service.ProfileService;

/* Junit + Mockito test */

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
	private static final String updateFirstName = "Johnny";
	private static final String updateLastName = "SnowWhite";

	//initialize mock object
	@Before
	public void setUp() {
		profileService = new ProfileService();
		
		profileRepoMock = mock(ProfileRepo.class);
		profileService.setProfileRepo(profileRepoMock);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFindByUserId() {
		TV2User user = ProfileTestUtil.createModelObject(uId, firstName, lastName);
		
		//stubbing
		when(profileRepoMock.findByUserId(uId)).thenReturn(user);

		TV2User returned =  profileService.findByUserId(1);
		
		//verification
		verify(profileRepoMock, times(1)).findByUserId(uId);
		verifyNoMoreInteractions(profileRepoMock);
		
		assertEquals(user, returned);
		
	}
	
	@Test//(expected = UserNotFoundException.class)
	public void testSave() { //update
		TV2User updated = ProfileTestUtil.createModelObject(uId,updateFirstName,updateLastName);
		TV2User user = ProfileTestUtil.createModelObject(uId, firstName, lastName);
	
		//stubbing
		when(profileRepoMock.save(user)).thenReturn(updated);
		
		TV2User returned = profileService.save(user);
		
		//verification
		verify(profileRepoMock, times(1)).save(user);
		verifyNoMoreInteractions(profileRepoMock);
		
		assertUser(updated, returned);
	}
	
	@SuppressWarnings("deprecation")
	private void assertUser(TV2User expected, TV2User actual) {
		assertEquals(expected.getUserId(), actual.getUserId());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
	}
	
	//Exception handling
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
//		//Mock Alert: verify the method was called
//		verify(profileRepoMock).findAll();
//	}
}
