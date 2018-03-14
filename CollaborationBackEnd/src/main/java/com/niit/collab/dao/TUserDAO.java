package com.niit.collab.dao;
import java.util.List;

import com.niit.collab.model.UserDetails;
public interface TUserDAO 
{
	public boolean save(UserDetails user);
	public boolean update(UserDetails user);
	public UserDetails get(String i);
	public List<UserDetails> list();
	public UserDetails validate(String i,String password);
	

}
