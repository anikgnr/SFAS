package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_be_bank_accounts")
public class BankAccount extends AbstractBaseEntity{
    
    
    @Column(name = "bank_name")
    private String bankName;
        
    @Column(name = "bank_account")
    private String bankAccount;
        
    @Column(name = "is_active")
    private boolean active;
    
        
    public BankAccount(){
    	active = true;
    }

	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getCompleteName(){
		return this.bankName+" ( "+this.bankAccount+" )";
	}
    
}