package com.codeyard.sfas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.codeyard.sfas.dao.JdbcDao;

@Repository
public class JdbcDaoImpl implements JdbcDao {
	
	private static Logger logger = Logger.getLogger(JdbcDaoImpl.class); 

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
        
    public boolean isStockInAlreadyUsedById(Long stockInId){
    	int count = jdbcTemplate.queryForInt("select count(id) from cy_re_stocks_out where stock_in_id = ?",stockInId);
    	if(count > 0)
    		return true;
    	else
    		return false;
    }
}