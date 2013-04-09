package com.codeyard.sfas.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.Utils;

public class StockSearchVo extends SearchVo{
	
	private Long depoId;
	private Long distributorId;
	private Long productId;
	private String createdBy;
	private Long quantity;
	private Date stockInFromDate;
	private Date stockInToDate;
	private String productName;
	private String bagSize;
	private String damageType;
	
	public StockSearchVo(){
		this.depoId = 0L;
		this.distributorId = 0L;
		this.productId = 0L;
		this.createdBy = null;
		this.quantity = 0L;
		this.stockInFromDate = null;
		this.stockInToDate = null;
		this.productName = null;
		this.bagSize = null;
		this.damageType = null;
	}
	
	
	public Long getDepoId() {
		return depoId;
	}


	public void setDepoId(Long id) {
		this.depoId = id;
	}


	public Long getDistributorId() {
		return distributorId;
	}


	public void setDistributorId(Long distributorId) {
		this.distributorId = distributorId;
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

	

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getBagSize() {
		return bagSize;
	}


	public void setBagSize(String bagSize) {
		this.bagSize = bagSize;
	}


	public String getDamageType() {
		return damageType;
	}


	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}


	public static StockSearchVo fetchFromRequest(HttpServletRequest request){
    	StockSearchVo searchVo = new StockSearchVo();

    	if(request.getParameter("depoId") != null && !Utils.isNullOrEmpty((String)request.getParameter("depoId")))
    		searchVo.setDepoId(Long.parseLong((String)request.getParameter("depoId")));
    	if(request.getParameter("distributorId") != null && !Utils.isNullOrEmpty((String)request.getParameter("distributorId")))
    		searchVo.setDistributorId(Long.parseLong((String)request.getParameter("distributorId")));
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
    	if(request.getParameter("productName") != null && !Utils.isNullOrEmpty((String)request.getParameter("productName")))
    		searchVo.setProductName((String)request.getParameter("productName"));
    	if(request.getParameter("bagSize") != null && !Utils.isNullOrEmpty((String)request.getParameter("bagSize")))
    		searchVo.setBagSize((String)request.getParameter("bagSize"));
    	if(request.getParameter("damageType") != null && !Utils.isNullOrEmpty((String)request.getParameter("damageType")))
    		searchVo.setDamageType((String)request.getParameter("damageType"));
    	
    	return searchVo;
    }
	
	public void buildFilterQueryClauses(String sql, List<Object> paramList, boolean hasClause){	
		
		if(this.depoId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "depo.id = ? ";
    		paramList.add(this.depoId);
    		hasClause = true;
    	}   
		if(this.distributorId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "distributor.id = ? ";
    		paramList.add(this.distributorId);
    		hasClause = true;
    	}   
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
		if(!Utils.isNullOrEmpty(this.productName)){
    		sql += (hasClause ? "AND ":"WHERE ") + "product.productName LIKE '%"+this.productName+"%' ";    		
    		hasClause = true;
    	}
		if(!Utils.isNullOrEmpty(this.bagSize)){
    		sql += (hasClause ? "AND ":"WHERE ") + "product.bagSize = ? ";
    		paramList.add(this.bagSize);
    		hasClause = true;
    	}
		if(!Utils.isNullOrEmpty(this.damageType)){
    		sql += (hasClause ? "AND ":"WHERE ") + "damageType = ? ";
    		paramList.add(this.damageType);
    		hasClause = true;
    	}
		
		this.sql = sql;
		this.params = paramList.toArray();		
	}
	
}
