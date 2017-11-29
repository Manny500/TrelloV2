package com.revature.profile;

import com.revature.profile.bean.TV2User;

public class ProfileTestUtil {

	
//	public static TV2User createExpectedObject(int id, String firstName, String lastName) {
//		TV2User expected = new TV2User(id, firstName, lastName);
//		return expected;
//	}
	
	public static TV2User createModelObject(int id, String firstName, String lastName) {
		TV2User model = new TV2User(id, firstName, lastName);
		return model;
	}
}
