package com.revature.profile;

import com.revature.profile.bean.TV2User;

public class ProfileTestUtil {

	
//	public static TV2User createDTO(int id, String firstName, String lastName) {
//		
//	}
	
	public static TV2User createModelObject(int id, String firstName, String lastName) {
		TV2User model = new TV2User(id, firstName, lastName);
		return model;
	}
}
