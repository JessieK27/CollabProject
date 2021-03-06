package com.niit.collab.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.dao.TUserDAO;
import com.niit.collab.model.UserDetails;

@Repository("tUserDAO")
public class TUserDAOImpl implements  TUserDAO {
	
	 private static final Logger log = LoggerFactory.getLogger(TUserDAOImpl.class);
		
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserDetails user;
	
	
	public TUserDAOImpl(SessionFactory sessionFactory)
	{
		log.debug("Starting of the method  TUserDAOImpl");
		this.sessionFactory = sessionFactory;
		log.debug("Ending of the method  TUserDAOImpl");
		
	}
	@Transactional
	public boolean save(UserDetails user) {
		log.debug("Starting of the method  save");
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	@Transactional
	public boolean update(UserDetails user) {
		log.debug("Starting of the method  update");
		log.debug("update the UserDetails " + user);
		try {
			sessionFactory.openSession().update(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	@Transactional
	public UserDetails get(String id) {
		log.debug("Starting of the method  get with the id :"+ id);
		
		user =	(UserDetails)sessionFactory.getCurrentSession().get(UserDetails.class, id);
		
		log.debug("user:" + user);
				return user;
		
		
		
	}

	@Transactional
	public List<UserDetails> list() {
		log.debug("Starting of the method  list");
		String hql = "from UserDetails";
		
		Query query=  sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method  TUserDAOImpl");
		return  query.list();
		
		
	}

	@Transactional
	public UserDetails validate(String id, String password) {
		log.debug("Starting of the method  validate");
	
		String hql = "from UserDetails  where id= '" + id +"' and password = '" + password+"'";
		
		Query query=  sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method  TUserDAOImpl");
		return  (UserDetails)query.uniqueResult();
		
		
	}


}






