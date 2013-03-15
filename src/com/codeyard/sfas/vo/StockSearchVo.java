package com.codeyard.sfas.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.Utils;

public class StockSearchVo extends SearchVo{
	
	private Long productId;
	private String createdBy;
	private Long quantity;
	private Date stockInFromDate;
	private Date stockInToDate;
	
	public StockSearchVo(){
		this.productId = 0L;
		this.createdBy = null;
		this.quantity = 0L;
		this.stockInFromDate = null;
		this.stockInToDate = null;
	}
	
	
	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Date getStockInFromDate() {
		return stockInFromDate;
	}


	public void setStockInFromDate(Date stockInFromDate) {
		this.stockInFromDate = stockInFromDate;
	}


	public Date getStockInToDate() {
		return stockInToDate;
	}


	public void setStockInToDate(Date stockInToDate) {
		this.stockInToDate = stockInToDate;
	}


	public static StockSearchVo fetchFromRequest(HttpServletRequest request){
    	StockSearchVo searchVo = new StockSearchVo();

    	if(request.getParameter("productId") != null && !Utils.isNullOrEmpty((String)request.getParameter("productId")))
    		searchVo.setProductId(Long.parseLong((String)request.getParameter("productId")));
    	if(request.getParameter("createdBy") != null && !Utils.isNullOrEmpty((String)request.getParameter("createdBy")))
    		searchVo.setCreatedBy((String)request.getParameter("createdBy"));
    	if(request.getParameter("quantity") != null && !Utils.isNullOrEmpty((String)request.getParameter("quantity")))
    		searchVo.setQuantity(Long.parseLong((String)request.getParameter("quantity")));
    	if(request.getParameter("stockInFromDate") != null && !Utils.isNullOrEmpty((String)request.getParameter("stockInFromDate")))
    		searchVo.setStockInFromDate(Utils.getDateFromString(Constants.DATE_FORMAT,(String)request.getParameter("stockInFromDate")));
    	if(request.getParameter("stockInToDate") != null && !Utils.isNullOrEmpty((String)request.getParameter("stockInToDate")))
    		searchVo.setStockInToDate(Utils.getDateFromString(Constants.DATE_FORMAT,(String)request.getParameter("stockInToDate")));

    	return searchVo;
    }
	
	public void buildFilterQueryClauses(String sql, List<Object> paramList, boolean hasClause){	
		
		if(this.productId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "product.id = ? ";
    		paramList.add(this.productId);
    		hasClause = true;
    	}   
		if(!Utils.isNullOrEmpty(this.createdBy)){
    		sql += (hasClause ? "AND ":"WHERE ") + "createdBy = ? ";
    		paramList.add(this.createdBy);
    		hasClause = true;
    	}
		if(this.quantity != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "quantity = ? ";
    		paramList.add(this.quantity);
    		hasClause = true;
    	}   	
		if(this.stockInFromDate != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "stockInDate > ? ";
    		paramList.add(Utils.prevDay(this.stockInFromDate));
    		hasClause = true;
    	}
		if(this.stockInToDate != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "stockInDate < ? ";
    		paramList.add(Utils.nextDay(this.stockInToDate));
    		hasClause = true;
    	}
		
		this.sql = sql;
		this.params = paramList.toArray();		
	}
	
}
