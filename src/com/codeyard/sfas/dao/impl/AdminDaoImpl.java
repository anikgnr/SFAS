package com.codeyard.sfas.dao.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.codeyard.sfas.dao.AdminDao;
import com.codeyard.sfas.entity.AbstractBaseEntity;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.admin.UserVo;
 

@Repository
public class AdminDaoImpl implements AdminDao {
	private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @SuppressWarnings("unchecked")
    public List<User> getAllUserList(UserVo userVo){
    	String sql = "From User ";
    	boolean hasClause = false;
    	
    	if(!Utils.isNullOrEmpty(userVo.getFirstName())){
    		sql += (hasClause ? "AND ":"WHERE ") + "firstName LIKE '%"+userVo.getFirstName()+"%'";
    	}
    	if(!Utils.isNullOrEmpty(userVo.getLastName())){
    		sql += (hasClause ? "AND ":"WHERE ") + "lastName LIKE '%"+userVo.getLastName()+"%'";
    	}
    	if(!Utils.isNullOrEmpty(userVo.getUserName())){
    		sql += (hasClause ? "AND ":"WHERE ") + "userName LIKE '%"+userVo.getUserName()+"%'";
    	}
    	if(!Utils.isNullOrEmpty(userVo.getMobileNumber())){
    		sql += (hasClause ? "AND ":"WHERE ") + "mobileNumber LIKE '%"+userVo.getMobileNumber()+"%'";
    	}
    	if(!Utils.isNullOrEmpty(userVo.getRole())){
    		sql += (hasClause ? "AND ":"WHERE ") + "role = '"+userVo.getRole()+"'";
    	}
    	if(!Utils.isNullOrEmpty(userVo.getIsActive())){
    		sql += (hasClause ? "AND ":"WHERE ") + "active = '"+userVo.getIsActive()+"'";
    	}
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
		hibernateTemplate.saveOrUpdate(entity);
	}
    
	public void deleteEntityById(Long id, String className){
		hibernateTemplate.bulkUpdate("Delete From "+className+" where id = ?",id);
	}

}