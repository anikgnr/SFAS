package com.codeyard.sfas.dao.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.codeyard.sfas.dao.AdminDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.AbstractLookUpEntity;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.AdminSearchVo;
 
@Repository
public class AdminDaoImpl implements AdminDao {
	private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    	
	@SuppressWarnings("unchecked")
	public 	boolean hasUserByUserName(String userName){
		List<User> userList = hibernateTemplate.find("From User where userName = ?", userName);
		if(userList != null && userList.size() > 0)
			return true;
		return false;
	}
	
    @SuppressWarnings("unchecked")
    public List<AbstractBaseEntity> getEnityList(AdminSearchVo searchVo, String className){
    	String sql = searchVo.buildFilterQueryClauses("From "+className+" ");
    	logger.debug(sql);
    	return hibernateTemplate.find(sql);
    }

    @SuppressWarnings("unchecked")
	public AbstractBaseEntity loadEntityById(Long id, String className){	
		List<AbstractBaseEntity> entityList = hibernateTemplate.find("From "+className+" where id = ?", id);
		if(entityList != null && entityList.size() > 0)
			return entityList.get(0);
		return null;
    }
        
    public void saveOrUpdate(AbstractBaseEntity entity){
    	if(entity.getId() == null || entity.getId() == 0){
    		entity.setCreatedBy(Utils.getLoggedUser());
    		entity.setCreated(Utils.today());
    	}
    	entity.setLastModifiedBy(Utils.getLoggedUser());
    	entity.setLastModified(Utils.today());
		hibernateTemplate.saveOrUpdate(entity);		
	}
    
	public void deleteEntityById(Long id, String className){
		hibernateTemplate.bulkUpdate("Delete From "+className+" where id = ?",id);
	}

	@SuppressWarnings("unchecked")
	public List<AbstractLookUpEntity> getAllLookUpEntity(String className){
		return hibernateTemplate.find("FROM "+className);
	}
	
	@SuppressWarnings("unchecked")
	public AbstractLookUpEntity loadLookUpEntityById(Long id, String className){
		List<AbstractLookUpEntity> entityList = hibernateTemplate.find("From "+className+" where id = ?", id);
		if(entityList != null && entityList.size() > 0)
			return entityList.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<AbstractLookUpEntity> getLookUpEntityList(String className, String property, Object value){
		return hibernateTemplate.find("FROM "+className+" where "+property+" = ?",value);
	}
}