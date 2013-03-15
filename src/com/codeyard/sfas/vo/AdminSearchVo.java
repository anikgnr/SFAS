package com.codeyard.sfas.vo;

import javax.servlet.http.HttpServletRequest;

import com.codeyard.sfas.util.Utils;

public class AdminSearchVo {

	private String firstName;
	private String lastName;
	private String userName;
	private String mobileNumber;
	private String role;
	private String isActive;
	private String address;
	private String pointName;
	private String productName;
	private String name;
	private String bagSize;
    private Double rate;
	private Double profitMargin;
	private long regionId;
	private long areaId;
	private long rsmId;
	private long asmRsmId;
	private long asmId;
	private long territoryId;
	private long tsoAsmRsmId;
	private long tsoAsmId;
	private long tsoId;
	private long asmAreaId;
	private long tsoTerritoryId;
	private long distributorTsoAsmRsmId;
	private long distributorTsoAsmId;
	private long distributorTsoId;
	private long distributorId;
	private long depoId;
	
	public AdminSearchVo(){
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.mobileNumber = null;
		this.role = null;
		this.isActive = null;
		this.address = null;
		this.pointName = null;
		this.productName = null;
		this.name = null;
		this.bagSize = null;
		this.rate = 0.0;
		this.profitMargin= 0.0;
		this.regionId = 0;
		this.areaId = 0;
		this.rsmId = 0;
		this.asmRsmId = 0;
		this.asmId = 0;
		this.territoryId = 0;
		this.tsoAsmRsmId = 0;
		this.tsoAsmId = 0;
		this.tsoId = 0;
		this.asmAreaId = 0;
		this.tsoTerritoryId = 0;
		this.distributorTsoAsmRsmId = 0;
		this.distributorTsoAsmId = 0;
		this.distributorTsoId = 0;
		this.distributorId = 0;
		this.depoId = 0;
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

	public long getTsoAsmRsmId() {
		return tsoAsmRsmId;
	}

	public void setTsoAsmRsmId(long tsoAsmRsmId) {
		this.tsoAsmRsmId = tsoAsmRsmId;
	}

	public long getTsoAsmId() {
		return tsoAsmId;
	}

	public void setTsoAsmId(long tsoAsmId) {
		this.tsoAsmId = tsoAsmId;
	}

	public long getTsoId() {
		return tsoId;
	}

	public void setTsoId(long tsoId) {
		this.tsoId = tsoId;
	}

	public long getAsmAreaId() {
		return asmAreaId;
	}

	public void setAsmAreaId(long asmAreaId) {
		this.asmAreaId = asmAreaId;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public long getTsoTerritoryId() {
		return tsoTerritoryId;
	}

	public void setTsoTerritoryId(long tsoTerritoryId) {
		this.tsoTerritoryId = tsoTerritoryId;
	}
	
	public long getDistributorTsoAsmRsmId() {
		return distributorTsoAsmRsmId;
	}

	public void setDistributorTsoAsmRsmId(long distributorTsoAsmRsmId) {
		this.distributorTsoAsmRsmId = distributorTsoAsmRsmId;
	}

	public long getDistributorTsoAsmId() {
		return distributorTsoAsmId;
	}

	public void setDistributorTsoAsmId(long distributorTsoAsmId) {
		this.distributorTsoAsmId = distributorTsoAsmId;
	}

	public long getDistributorTsoId() {
		return distributorTsoId;
	}

	public void setDistributorTsoId(long distributorTsoId) {
		this.distributorTsoId = distributorTsoId;
	}

	public long getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(long distributorId) {
		this.distributorId = distributorId;
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(Double profitMargin) {
		this.profitMargin = profitMargin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDepoId() {
		return depoId;
	}

	public void setDepoId(long depoId) {
		this.depoId = depoId;
	}

	public static AdminSearchVo fetchFromRequest(HttpServletRequest request){
    	AdminSearchVo searchVo = new AdminSearchVo();
    	
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
    	if(request.getParameter("pn") != null && !Utils.isNullOrEmpty((String)request.getParameter("pn")))
    		searchVo.setPointName((String)request.getParameter("pn"));
    	if(request.getParameter("nm") != null && !Utils.isNullOrEmpty((String)request.getParameter("nm")))
    		searchVo.setName((String)request.getParameter("nm"));
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
      	if(request.getParameter("tars") != null && !Utils.isNullOrEmpty((String)request.getParameter("tars")))
    		searchVo.setTsoAsmRsmId(Long.parseLong((String)request.getParameter("tars")));
      	if(request.getParameter("tas") != null && !Utils.isNullOrEmpty((String)request.getParameter("tas")))
    		searchVo.setTsoAsmId(Long.parseLong((String)request.getParameter("tas")));
      	if(request.getParameter("ts") != null && !Utils.isNullOrEmpty((String)request.getParameter("ts")))
    		searchVo.setTsoId(Long.parseLong((String)request.getParameter("ts")));
      	if(request.getParameter("dtars") != null && !Utils.isNullOrEmpty((String)request.getParameter("dtars")))
    		searchVo.setDistributorTsoAsmRsmId(Long.parseLong((String)request.getParameter("dtars")));
      	if(request.getParameter("dtas") != null && !Utils.isNullOrEmpty((String)request.getParameter("dtas")))
    		searchVo.setDistributorTsoAsmId(Long.parseLong((String)request.getParameter("dtas")));
      	if(request.getParameter("dts") != null && !Utils.isNullOrEmpty((String)request.getParameter("dts")))
    		searchVo.setDistributorTsoId(Long.parseLong((String)request.getParameter("dts")));
      	if(request.getParameter("dr") != null && !Utils.isNullOrEmpty((String)request.getParameter("dr")))
    		searchVo.setDistributorId(Long.parseLong((String)request.getParameter("dr")));
      	if(request.getParameter("prn") != null && !Utils.isNullOrEmpty((String)request.getParameter("prn")))
    		searchVo.setProductName((String)request.getParameter("prn"));
      	if(request.getParameter("bs") != null && !Utils.isNullOrEmpty((String)request.getParameter("bs")))
    		searchVo.setBagSize((String)request.getParameter("bs"));
      	if(request.getParameter("rt") != null && !Utils.isNullOrEmpty((String)request.getParameter("rt")))
    		searchVo.setRate(Double.parseDouble((String)request.getParameter("rt")));
      	if(request.getParameter("pfm") != null && !Utils.isNullOrEmpty((String)request.getParameter("pfm")))
    		searchVo.setProfitMargin(Double.parseDouble((String)request.getParameter("pfm")));      	
      	if(request.getParameter("dp") != null && !Utils.isNullOrEmpty((String)request.getParameter("dp")))
    		searchVo.setDepoId(Long.parseLong((String)request.getParameter("dp")));            	
    	
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
    	if(!Utils.isNullOrEmpty(this.pointName)){
    		sql += (hasClause ? "AND ":"WHERE ") + "pointName LIKE '%"+this.pointName+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.productName)){
    		sql += (hasClause ? "AND ":"WHERE ") + "productName LIKE '%"+this.productName+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.name)){
    		sql += (hasClause ? "AND ":"WHERE ") + "name LIKE '%"+this.name+"%'";
    		hasClause = true;
    	}
    	if(!Utils.isNullOrEmpty(this.bagSize)){
    		sql += (hasClause ? "AND ":"WHERE ") + "bagSize LIKE '%"+this.bagSize+"%'";
    		hasClause = true;
    	}
    	if(this.rate != 0.0){
    		sql += (hasClause ? "AND ":"WHERE ") + "rate = '"+this.rate+"'";
    		hasClause = true;
    	}
    	if(this.profitMargin != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "profitMargin = '"+this.profitMargin+"'";
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
    	if(this.tsoAsmRsmId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "tso.asm.rsm.id = '"+this.tsoAsmRsmId+"'";
    		hasClause = true;
    	}
    	if(this.tsoAsmId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "tso.asm.id = '"+this.tsoAsmId+"'";
    		hasClause = true;
    	}
    	if(this.tsoId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "tso.id = '"+this.tsoId+"'";
    		hasClause = true;
    	}
    	if(this.asmAreaId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "asm.area.id = '"+this.asmAreaId+"'";
    		hasClause = true;
    	}
    	if(this.tsoTerritoryId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "tso.territory.id = '"+this.tsoTerritoryId+"'";
    		hasClause = true;
    	}
    	if(this.distributorTsoAsmRsmId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "distributor.tso.asm.rsm.id = '"+this.distributorTsoAsmRsmId+"'";
    		hasClause = true;
    	}
    	if(this.distributorTsoAsmId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "distributor.tso.asm.id = '"+this.distributorTsoAsmId+"'";
    		hasClause = true;
    	}
    	if(this.distributorTsoId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "distributor.tso.id = '"+this.distributorTsoId+"'";
    		hasClause = true;
    	}
    	if(this.distributorId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "distributor.id = '"+this.distributorId+"'";
    		hasClause = true;
    	}
    	if(this.depoId != 0){
    		sql += (hasClause ? "AND ":"WHERE ") + "depo.id = '"+this.depoId+"'";
    		hasClause = true;
    	}
    	return sql;
	}
	
}
