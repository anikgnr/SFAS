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
import com.codeyard.sfas.entity.Region;
import com.codeyard.sfas.entity.User;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.SearchVo;
 

@Repository
public class AdminDaoImpl implements AdminDao {
	private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private HibernateTemplate hibernateTemplate;
    
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
    	hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @SuppressWarnings("unchecked")
    public List<AbstractBaseEntity> getEnityList(SearchVo searchVo, String className){
    	String sql = "From "+className+" ";
    	boolean hasClause = false;
    	
    	if(!Utils.isNullOrEmpty(searchVo.getFirstName())){
    		sql += (hasClause ? "AND ":"WHERE ") + "firstName LIKE '%"+searchVo.getFirstName()+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(searchVo.getLastName())){
    		sql += (hasClause ? "AND ":"WHERE ") + "lastName LIKE '%"+searchVo.getLastName()+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(searchVo.getUserName())){
    		sql += (hasClause ? "AND ":"WHERE ") + "userName LIKE '%"+searchVo.getUserName()+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(searchVo.getMobileNumber())){
    		sql += (hasClause ? "AND ":"WHERE ") + "mobileNumber LIKE '%"+searchVo.getMobileNumber()+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(searchVo.getRole())){
    		sql += (hasClause ? "AND ":"WHERE ") + "role = '"+searchVo.getRole()+"'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(searchVo.getIsActive())){
    		sql += (hasClause ? "AND ":"WHERE ") + "active = '"+searchVo.getIsActive()+"'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(searchVo.getAddress())){
    		sql += (hasClause ? "AND ":"WHERE ") + "address LIKE '%"+searchVo.getAddress()+"%'";
    		hasClause = true;
    	}
    	if(searchVo.getRegionId() != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "region.id = '"+searchVo.getRegionId()+"'";
    		hasClause = true;
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
	public List<Region> getAllRegions(){
		return hibernateTemplate.find("FROM Region");
	}
	
	public AbstractLookUpEntity loadLookUpEntityById(Long id, String className){
		List<AbstractLookUpEntity> entityList = hibernateTemplate.find("From "+className+" where id = ?", id);
		if(entityList != null && entityList.size() > 0)
			return entityList.get(0);
		return null;
	}
}