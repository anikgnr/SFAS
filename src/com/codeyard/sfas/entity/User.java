package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;


@Entity
@Table(name="cy_be_users")
public class User extends AbstractBaseEntity{
    
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "is_active")
    private boolean active;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "mobile_number")
    private String mobileNumber;

    @Transient
    private String confirmPassword;

    public User(){
    	active = true;
    }
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRoleName() {
		return Role.getLabelForValue(role);
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", role=" + role + ", active=" + active + ", firstName="
				+ firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", confirmPassword=" + confirmPassword + "]";
	}
    
    
    
}