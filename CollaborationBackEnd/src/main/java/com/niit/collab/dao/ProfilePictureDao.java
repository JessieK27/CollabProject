package com.niit.collab.dao;

import com.niit.collab.model.ProfilePicture;

public interface ProfilePictureDao {
	void save(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String username);
}
