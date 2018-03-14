package com.niit.collab.dao;

import java.util.List;

import com.niit.collab.model.ForumCategory;

public interface ForumCategoryDAO {
	
	public List<ForumCategory> getAllForumCategory();
	
	public boolean forumCategoryUpdate(ForumCategory forumcategory);
		
	public ForumCategory getForumCategoryByID(int fcid);

}
