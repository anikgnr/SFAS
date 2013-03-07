package com.codeyard.sfas.vo;

import javax.servlet.http.HttpServletRequest;

import com.codeyard.sfas.util.Utils;

public class SearchVo {

	private String firstName;
	private String lastName;
	private String userName;
	private String mobileNumber;
	private String role;
	private String isActive;
	private String address;
	private long regionId;
	private long areaId;
	private long rsmId;
	private long asmRsmId;
	private long asmId;
	private long territoryId;
	
	public SearchVo(){
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.mobileNumber = null;
		this.role = null;
		this.isActive = null;
		this.address = null;
		this.regionId = 0;
		this.areaId = 0;
		this.rsmId = 0;
		this.asmRsmId = 0;
		this.asmId = 0;
		this.territoryId = 0;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}		
	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}
	public long getRsmId() {
		return rsmId;
	}

	public void setRsmId(long rsmId) {
		this.rsmId = rsmId;
	}

	public long getAsmRsmId() {
		return asmRsmId;
	}

	public void setAsmRsmId(long asmRsmId) {
		this.asmRsmId = asmRsmId;
	}

	public long getAsmId() {
		return asmId;
	}

	public void setAsmId(long asmId) {
		this.asmId = asmId;
	}

	public long getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(long territoryId) {
		this.territoryId = territoryId;
	}

	public static SearchVo fetchFromRequest(HttpServletRequest request){
    	SearchVo searchVo = new SearchVo();
    	
    	if(request.getParameter("fn") != null && !Utils.isNullOrEmpty((String)request.getParameter("fn")))
    		searchVo.setFirstName((String)request.getParameter("fn"));
    	if(request.getParameter("ln") != null && !Utils.isNullOrEmpty((String)request.getParameter("ln")))
    		searchVo.setLastName((String)request.getParameter("ln"));
    	if(request.getParameter("un") != null && !Utils.isNullOrEmpty((String)request.getParameter("un")))
    		searchVo.setUserName((String)request.getParameter("un"));    	
    	if(request.getParameter("mn") != null && !Utils.isNullOrEmpty((String)request.getParameter("mn")))
    		searchVo.setMobileNumber((String)request.getParameter("mn"));
    	if(request.getParameter("rl") != null && !Utils.isNullOrEmpty((String)request.getParameter("rl")))
    		searchVo.setRole((String)request.getParameter("rl"));    	
    	if(request.getParameter("ad") != null && !Utils.isNullOrEmpty((String)request.getParameter("ad")))
    		searchVo.setAddress((String)request.getParameter("ad"));
    	if(request.getParameter("rg") != null && !Utils.isNullOrEmpty((String)request.getParameter("rg")))
    		searchVo.setRegionId(Long.parseLong((String)request.getParameter("rg")));
    	if(request.getParameter("ia") != null && !Utils.isNullOrEmpty((String)request.getParameter("ia")))
    		searchVo.setIsActive((String)request.getParameter("ia"));
    	if(request.getParameter("ar") != null && !Utils.isNullOrEmpty((String)request.getParameter("ar")))
    		searchVo.setAreaId(Long.parseLong((String)request.getParameter("ar")));
      	if(request.getParameter("rs") != null && !Utils.isNullOrEmpty((String)request.getParameter("rs")))
    		searchVo.setRsmId(Long.parseLong((String)request.getParameter("rs")));
      	if(request.getParameter("ars") != null && !Utils.isNullOrEmpty((String)request.getParameter("ars")))
    		searchVo.setAsmRsmId(Long.parseLong((String)request.getParameter("ars")));
      	if(request.getParameter("as") != null && !Utils.isNullOrEmpty((String)request.getParameter("as")))
    		searchVo.setAsmId(Long.parseLong((String)request.getParameter("as")));
      	if(request.getParameter("tr") != null && !Utils.isNullOrEmpty((String)request.getParameter("tr")))
    		searchVo.setTerritoryId(Long.parseLong((String)request.getParameter("tr")));
    	
    	return searchVo;
    }
	
	public String buildFilterQueryClauses(String sql){
		boolean hasClause = false;
    	
    	if(!Utils.isNullOrEmpty(this.firstName)){
    		sql += (hasClause ? "AND ":"WHERE ") + "firstName LIKE '%"+this.firstName+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.lastName)){
    		sql += (hasClause ? "AND ":"WHERE ") + "lastName LIKE '%"+this.lastName+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.userName)){
    		sql += (hasClause ? "AND ":"WHERE ") + "userName LIKE '%"+this.userName+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.userName)){
    		sql += (hasClause ? "AND ":"WHERE ") + "mobileNumber LIKE '%"+this.userName+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.role)){
    		sql += (hasClause ? "AND ":"WHERE ") + "role = '"+this.role+"'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.isActive)){
    		sql += (hasClause ? "AND ":"WHERE ") + "active = '"+this.isActive+"'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.address)){
    		sql += (hasClause ? "AND ":"WHERE ") + "address LIKE '%"+this.address+"%'";
    		hasClause = true;
    	}
    	if(this.regionId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "region.id = '"+this.regionId+"'";
    		hasClause = true;
    	}
    	if(this.areaId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "area.id = '"+this.areaId+"'";
    		hasClause = true;
    	}
    	if(this.rsmId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "rsm.id = '"+this.rsmId+"'";
    		hasClause = true;
    	}
    	if(this.asmRsmId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "asm.rsm.id = '"+this.asmRsmId+"'";
    		hasClause = true;
    	}
    	if(this.asmId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "asm.id = '"+this.asmId+"'";
    		hasClause = true;
    	}
    	if(this.territoryId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "territory.id = '"+this.territoryId+"'";
    		hasClause = true;
    	}   	
		
    	return sql;
	}

}
