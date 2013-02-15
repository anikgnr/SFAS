package com.codeyard.sfas.dao.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.codeyard.sfas.dao.AdminDao;
import com.codeyard.sfas.entity.User;
 

@Repository
public class AdminDaoImpl implements AdminDao {
	private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @SuppressWarnings("unchecked")
    public List<User> getAllUserList(){
		return hibernateTemplate.find("From User");
    }
    	
}