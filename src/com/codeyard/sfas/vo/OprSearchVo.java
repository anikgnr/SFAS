package com.codeyard.sfas.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.util.Constants;
import com.codeyard.sfas.util.Utils;

public class OprSearchVo extends SearchVo{
	
	private Long depoId;
	private Long accountId;
	private Long depoRegionId;
	private Double depositAmount;
	private Boolean accountApproved;
	private String accountApprovedBy;
	private String approveType;
	private Date depositFromDate;
	private Date depositToDate;
	private Date orderFromDate;
	private Date orderToDate;
	private Double orderAmount;
	private Boolean mdApproved;
	private Boolean delivered;	
	
	
	public OprSearchVo(){
		this.depoId = 0L;
		this.accountId = 0L;
		this.depoRegionId = 0L;
		this.depositAmount = null;
		this.accountApproved = null;
		this.accountApprovedBy = null;
		this.approveType = null;
		this.depositFromDate = null;
		this.depositToDate = null;
		this.orderFromDate = null;
		this.orderToDate = null;
		this.orderAmount = null;
		this.mdApproved = null;
		this.delivered = null;		
	}
	
	
	public Long getDepoId() {
		return depoId;
	}


	public void setDepoId(Long depoId) {
		this.depoId = depoId;
	}


	public Long getAccountId() {
		return accountId;
	}


	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	public Long getDepoRegionId() {
		return depoRegionId;
	}


	public void setDepoRegionId(Long depoRegionId) {
		this.depoRegionId = depoRegionId;
	}


	public Double getDepositAmount() {
		return depositAmount;
	}


	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}


	public Boolean getAccountApproved() {
		return accountApproved;
	}


	public void setAccountApproved(Boolean accountApproved) {
		this.accountApproved = accountApproved;
	}


	public String getAccountApprovedBy() {
		return accountApprovedBy;
	}


	public void setAccountApprovedBy(String accountApprovedBy) {
		this.accountApprovedBy = accountApprovedBy;
	}


	public Date getDepositFromDate() {
		return depositFromDate;
	}


	public void setDepositFromDate(Date depositFromDate) {
		this.depositFromDate = depositFromDate;
	}


	public Date getDepositToDate() {
		return depositToDate;
	}


	public void setDepositToDate(Date depositToDate) {
		this.depositToDate = depositToDate;
	}
	
	
	public Date getOrderFromDate() {
		return orderFromDate;
	}


	public void setOrderFromDate(Date orderFromDate) {
		this.orderFromDate = orderFromDate;
	}


	public Date getOrderToDate() {
		return orderToDate;
	}


	public void setOrderToDate(Date orderToDate) {
		this.orderToDate = orderToDate;
	}


	public Double getOrderAmount() {
		return orderAmount;
	}


	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}


	public Boolean getMdApproved() {
		return mdApproved;
	}


	public void setMdApproved(Boolean mdApproved) {
		this.mdApproved = mdApproved;
	}


	public Boolean getDelivered() {
		return delivered;
	}


	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}


	public String getApproveType() {
		return approveType;
	}


	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}


	public static OprSearchVo fetchFromRequest(HttpServletRequest request){
    	OprSearchVo searchVo = new OprSearchVo();
    	    	
    	if(request.getParameter("depoId") != null && !Utils.isNullOrEmpty((String)request.getParameter("depoId")))
    		searchVo.setDepoId(Long.parseLong((String)request.getParameter("depoId")));
    	if(request.getParameter("accountId") != null && !Utils.isNullOrEmpty((String)request.getParameter("accountId")))
    		searchVo.setAccountId(Long.parseLong((String)request.getParameter("accountId")));    	
    	if(request.getParameter("depoRegionId") != null && !Utils.isNullOrEmpty((String)request.getParameter("depoRegionId")))
    		searchVo.setDepoRegionId(Long.parseLong((String)request.getParameter("depoRegionId")));
    	if(request.getParameter("depositAmount") != null && !Utils.isNullOrEmpty((String)request.getParameter("depositAmount")))
    		searchVo.setDepositAmount(Double.parseDouble((String)request.getParameter("depositAmount")));
    	if(request.getParameter("accountApproved") != null && !Utils.isNullOrEmpty((String)request.getParameter("accountApproved")))
    		searchVo.setAccountApproved(Boolean.parseBoolean((String)request.getParameter("accountApproved")));
    	if(request.getParameter("accountApprovedBy") != null && !Utils.isNullOrEmpty((String)request.getParameter("accountApprovedBy")))
    		searchVo.setAccountApprovedBy((String)request.getParameter("accountApprovedBy"));    	
    	if(request.getParameter("depositFromDate") != null && !Utils.isNullOrEmpty((String)request.getParameter("depositFromDate")))
    		searchVo.setDepositFromDate(Utils.getDateFromString(Constants.DATE_FORMAT,(String)request.getParameter("depositFromDate")));
    	if(request.getParameter("depositToDate") != null && !Utils.isNullOrEmpty((String)request.getParameter("depositToDate")))
    		searchVo.setDepositToDate(Utils.getDateFromString(Constants.DATE_FORMAT,(String)request.getParameter("depositToDate")));
    	if(request.getParameter("orderFromDate") != null && !Utils.isNullOrEmpty((String)request.getParameter("orderFromDate")))
    		searchVo.setOrderFromDate(Utils.getDateFromString(Constants.DATE_FORMAT,(String)request.getParameter("orderFromDate")));
    	if(request.getParameter("orderToDate") != null && !Utils.isNullOrEmpty((String)request.getParameter("orderToDate")))
    		searchVo.setOrderToDate(Utils.getDateFromString(Constants.DATE_FORMAT,(String)request.getParameter("orderToDate")));
    	if(request.getParameter("orderAmount") != null && !Utils.isNullOrEmpty((String)request.getParameter("orderAmount")))
    		searchVo.setOrderAmount(Double.parseDouble((String)request.getParameter("orderAmount")));
    	if(request.getParameter("mdApproved") != null && !Utils.isNullOrEmpty((String)request.getParameter("mdApproved")))
    		searchVo.setMdApproved(Boolean.parseBoolean((String)request.getParameter("mdApproved")));
    	if(request.getParameter("delivered") != null && !Utils.isNullOrEmpty((String)request.getParameter("delivered")))
    		searchVo.setDelivered(Boolean.parseBoolean((String)request.getParameter("delivered")));
    	
    	return searchVo;
    }
	
	public void buildFilterQueryClauses(String sql, List<Object> paramList, boolean hasClause){	
		
		if(this.depoId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "depo.id = ? ";
    		paramList.add(this.depoId);
    		hasClause = true;
    	}   
		if(this.accountId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "account.id = ? ";
    		paramList.add(this.accountId);
    		hasClause = true;
    	}
    	if(this.depoRegionId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "depo.rsm.region.id = ? ";
    		paramList.add(this.depoRegionId);
    		hasClause = true;
    	}  		
		if(this.depositAmount != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "depositAmount = ? ";
    		paramList.add(this.depositAmount);
    		hasClause = true;
    	}
		if(this.accountApproved != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "accountApproved = ? ";
    		paramList.add(this.accountApproved);
    		hasClause = true;
    	}
		if(this.accountApprovedBy != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "accountApprovedBy = ? ";
    		paramList.add(this.accountApprovedBy);
    		hasClause = true;
    	}
		if(this.depositFromDate != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "depositDate > ? ";
    		paramList.add(Utils.prevDay(this.depositFromDate));
    		hasClause = true;
    	}
		if(this.depositToDate != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "depositDate < ? ";
    		paramList.add(Utils.nextDay(this.depositToDate));
    		hasClause = true;
    	}
		if(this.orderFromDate != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "orderDate > ? ";
    		paramList.add(Utils.prevDay(this.orderFromDate));
    		hasClause = true;
    	}
		if(this.orderToDate != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "orderDate < ? ";
    		paramList.add(Utils.nextDay(this.orderToDate));
    		hasClause = true;
    	}
		if(this.orderAmount != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "orderAmount = ? ";
    		paramList.add(this.orderAmount);
    		hasClause = true;
    	}
		if(this.mdApproved != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "mdApproved = ? ";
    		paramList.add(this.mdApproved);
    		hasClause = true;
    	}
		if(this.delivered != null){
    		sql += (hasClause ? "AND ":"WHERE ") + "delivered = ? ";
    		paramList.add(this.delivered);
    		hasClause = true;
    	}
		if(!Utils.isNullOrEmpty(this.approveType)){
			
			if(ManagerType.MIS.getValue().equals(this.approveType)){
    			sql += (hasClause ? "AND ":"WHERE ") + "misApproved = false ";
    		}else if(ManagerType.ACCOUNT.getValue().equals(this.approveType)){
    			sql += (hasClause ? "AND ":"WHERE ") + "misApproved = true and accountApproved = false ";
    		}else if(ManagerType.Manager.getValue().equals(this.approveType)){
    			sql += (hasClause ? "AND ":"WHERE ") + "accountApproved = true and mgrApproved = false ";
    		}else if(ManagerType.MM.getValue().equals(this.approveType)){
    			sql += (hasClause ? "AND ":"WHERE ") + "mgrApproved = true and mmApproved = false ";
    		}else if(ManagerType.MD.getValue().equals(this.approveType)){
    			sql += (hasClause ? "AND ":"WHERE ") + "mmApproved = true and mdApproved = false ";
    		}
    		hasClause = true;
    	}		
		
		this.sql = sql;
		this.params = paramList.toArray();		
	}
	
}
