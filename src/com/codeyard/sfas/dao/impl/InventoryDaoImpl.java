package com.codeyard.sfas.dao.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.codeyard.sfas.dao.InventoryDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.SearchVo;
 

@Repository
public class InventoryDaoImpl implements InventoryDao {
	private static Logger logger = Logger.getLogger(InventoryDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    	
}