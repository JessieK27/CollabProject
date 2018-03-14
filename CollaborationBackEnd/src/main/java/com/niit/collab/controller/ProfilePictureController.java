package com.niit.collab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.collab.dao.ProfilePictureDao;
import com.niit.collab.model.ProfilePicture;
import com.niit.collab.model.UserDetails;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;
		@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		UserDetails user=(UserDetails) session.getAttribute("user");
		if(user==null)		{
			    Error error=new Error("UnAuthorized user");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		} 
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setUsername(user.getName());
		profilePicture.setImage(image.getBytes());
		profilePictureDao.save(profilePicture);
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
		
		//http://localhost:8080/backend_project2/getimage/admin
		@RequestMapping(value="/getimage/{username}", method=RequestMethod.GET)
		public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
			UserDetails user=(UserDetails) session.getAttribute("user");
			if(user==null)
				return null;
			else
			{
				ProfilePicture profilePic=profilePictureDao.getProfilePic(username);
				if(profilePic==null)
					return null;
				else
					return profilePic.getImage();
			}
			
	}
}
