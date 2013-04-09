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
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DistributorOrderLi;
import com.codeyard.sfas.entity.Product;

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
    
    public boolean hasUnDeliveredOrderForDepo(Long depoId){
    	int count = jdbcTemplate.queryForInt("select count(id) from cy_re_depo_order where depo_id = ? and is_delivered = false ",depoId);
    	if(count > 0)
    		return true;
    	else
    		return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DepoOrderLi> getLiteDepoOrderLiList(Long depoOrderId){
    	final List<DepoOrderLi> orderLiList = new ArrayList<DepoOrderLi>();    		
    	try	{
    		jdbcTemplate.query("SELECT product_id, order_quantity FROM cy_re_depo_order_li where depo_order_id = "+depoOrderId, new RowMapper(){
				@Override
				public Object mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					DepoOrderLi orderLi = new DepoOrderLi();
					Product product = new Product();
					
					product.setId(rs.getLong("product_id"));
					orderLi.setProduct(product);
					orderLi.setQuantity(rs.getLong("order_quantity"));
					
					orderLiList.add(orderLi);
					return null;
				}
    		});
    		    		
    	} catch(Exception ex) {
    		logger.debug("getLiteDepoOrderLi error :: " + depoOrderId, ex);
    	}
    	return orderLiList;
    }
    
    public boolean hasUnDeliveredOrderForDistributor(Long distributorId){
    	int count = jdbcTemplate.queryForInt("select count(id) from cy_re_distributor_order where distributor_id = ? and is_delivered = false ",distributorId);
    	if(count > 0)
    		return true;
    	else
    		return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DistributorOrderLi> getLiteDistributorOrderLiList(Long distributorOrderId){
    	final List<DistributorOrderLi> orderLiList = new ArrayList<DistributorOrderLi>();    		
    	try	{
    		jdbcTemplate.query("SELECT product_id, order_quantity FROM cy_re_distributor_order_li where distributor_order_id = "+distributorOrderId, new RowMapper(){
				@Override
				public Object mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					DistributorOrderLi orderLi = new DistributorOrderLi();
					Product product = new Product();
					
					product.setId(rs.getLong("product_id"));
					orderLi.setProduct(product);
					orderLi.setQuantity(rs.getLong("order_quantity"));
					
					orderLiList.add(orderLi);
					return null;
				}
    		});
    		    		
    	} catch(Exception ex) {
    		logger.debug("getLiteDistributorOrderLi error :: " + distributorOrderId, ex);
    	}
    	return orderLiList;
    }
}