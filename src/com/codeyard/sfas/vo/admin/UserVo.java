package com.codeyard.sfas.vo.admin;

public class UserVo {

	private String firstName;
	private String lastName;
	private String userName;
	private String mobileNumber;
	private String role;
	private String isActive;
	
	public UserVo(){
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.mobileNumber = null;
		this.role = null;
		this.isActive = null;
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
	
	
}
