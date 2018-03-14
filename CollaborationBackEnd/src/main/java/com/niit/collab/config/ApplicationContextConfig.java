package com.niit.collab.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collab.dao.BlogDAO;

import com.niit.collab.dao.FriendDAO;
import com.niit.collab.dao.JobDAO;
import com.niit.collab.dao.TUserDAO;
import com.niit.collab.dao.UserForumDAO;
import com.niit.collab.dao.impl.BlogDAOImpl;
import com.niit.collab.dao.impl.ForumCategoryDAO_Impl;
import com.niit.collab.dao.impl.FriendDAOImpl;
import com.niit.collab.dao.impl.JobDAOImpl;
import com.niit.collab.dao.impl.TUserDAOImpl;
import com.niit.collab.dao.impl.UserForumDAO_Impl;
import com.niit.collab.model.Blog;
import com.niit.collab.model.Forum;
import com.niit.collab.model.ForumCategory;
import com.niit.collab.model.Friend;
import com.niit.collab.model.Job;
import com.niit.collab.model.JobApplication;
import com.niit.collab.model.ProfilePicture;
import com.niit.collab.model.UserDetails;
import com.niit.collab.model.UserForum;
import com.niit.collab.model.UserForumComments;

@Configuration
@ComponentScan("com.niit.collab")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() 
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("Niit"); //Schema name
		dataSource.setPassword("Niit");
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) 
	{
		LocalSessionFactoryBuilder lsf=new LocalSessionFactoryBuilder(getOracleDataSource());
		Properties hibernateProperties=new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		return lsf.addAnnotatedClasses(Blog.class)
				//.addAnnotatedClass(Forum.class)
				.addAnnotatedClass(ForumCategory.class)
				.addAnnotatedClass(Job.class)
				.addAnnotatedClass(Friend.class)
				.addAnnotatedClass(UserDetails.class)
				.addAnnotatedClass(JobApplication.class)
				.addAnnotatedClass(UserDetails.class)
				.addAnnotatedClass(UserForum.class)
				.addAnnotatedClass(UserForumComments.class)
				.addAnnotatedClass(ProfilePicture.class)
		   .buildSessionFactory();

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}

/*	@Autowired
	@Bean(name = "forumDAO")
	public UserForumDAO getForumDAO(SessionFactory sessionFactory) {
		return new UserForumDAO_Impl(sessionFactory);
	}*/

	@Autowired
	@Bean(name = "userDetailsDAO")
	public TUserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		return new TUserDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "friendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory) {
		return new FriendDAOImpl(sessionFactory);
	}
	
/*	@Autowired
	@Bean(name = "ForumCategoryDAO")
	public ForumCategoryDAO getForumCategoryDAO(SessionFactory sessionFactory) {
		return new ForumCategoryDAO_Impl(sessionFactory);
	}
	*/
	

}
