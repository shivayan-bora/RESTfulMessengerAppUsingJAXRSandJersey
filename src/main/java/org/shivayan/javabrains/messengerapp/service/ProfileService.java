package org.shivayan.javabrains.messengerapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.shivayan.javabrains.messengerapp.database.DatabaseClass;
import org.shivayan.javabrains.messengerapp.model.Profile;

public class ProfileService {
	

	private static Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("shivayanbora123", new Profile(1,"shivayanbora123", "Shivayan", "Bora", new Date()));
		profiles.put("preetiyadav123", new Profile(2,"preetiyadav123", "Preeti", "Yadav", new Date()));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName() == null || profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}

}
