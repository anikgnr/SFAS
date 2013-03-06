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
	
	public SearchVo(){
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.mobileNumber = null;
		this.role = null;
		this.isActive = null;
		this.address = null;
		this.regionId = 0;
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
    	
    	return searchVo;
    }

}
