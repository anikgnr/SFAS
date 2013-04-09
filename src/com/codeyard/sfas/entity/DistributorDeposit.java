package com.codeyard.sfas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="cy_re_distributor_deposit")
public class DistributorDeposit extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="account_id")
    private BankAccount account;
  
    @Column(name = "deposit_amount")
    private  Double depositAmount;       
    
    @Column(name = "deposit_date")
    @Temporal(TemporalType.DATE)
    private  Date depositDate;
      
    @ManyToOne(optional=true)
    @JoinColumn(name="distributor_id")
    private Distributor distributor;
    
    @Column(name = "is_account_approved")
    private boolean accountApproved;
    
    @Column(name = "account_approved_by")
    private String accountApprovedBy;
    
    @Column(name = "account_approved_date")
    @Temporal(TemporalType.DATE)
    private  Date accountApprovedDate;
    
    public DistributorDeposit(){
    	depositAmount = 0.0;
    	account = new BankAccount();
    	distributor = new Distributor();
    	accountApproved = false;
    }

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}
	
	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public boolean isAccountApproved() {
		return accountApproved;
	}

	public void setAccountApproved(boolean accountApproved) {
		this.accountApproved = accountApproved;
	}

	public String getAccountApprovedBy() {
		return accountApprovedBy;
	}

	public void setAccountApprovedBy(String accountApprovedBy) {
		this.accountApprovedBy = accountApprovedBy;
	}

	public Date getAccountApprovedDate() {
		return accountApprovedDate;
	}

	public void setAccountApprovedDate(Date accountApprovedDate) {
		this.accountApprovedDate = accountApprovedDate;
	}

	public String getEditLink(){
		if(!this.accountApproved)
			return "<a href='./distributorDeposit.html?id="+super.getId()+"'>edit</a>";
		else
			return "";
	}
	
	public String getDeleteLink(){
		if(!this.accountApproved)
			return "<a href='javascript:deleteLinkClicked(\"./distributorDepositDelete.html?id="+super.getId()+"&did="+this.distributor.getId()+"\")'>delete</a>";
		else
			return "";
	}
	
	public String getApproveLink(){
		return "<a href='javascript:doApprove(\"./approveDistributorDeposit.html?id="+super.getId()+"\")'>approve</a>";
	}

}