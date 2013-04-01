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
@Table(name="cy_re_depo_deposit")
public class DepoDeposit extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="account_id")
    private BankAccount account;
  
    @Column(name = "deposit_amount")
    private  Double depositAmount;       
    
    @Column(name = "deposit_date")
    @Temporal(TemporalType.DATE)
    private  Date depositDate;
      
    @ManyToOne(optional=true)
    @JoinColumn(name="depo_id")
    private Depo depo;
    
    @Column(name = "is_account_approved")
    private boolean accountApproved;
    
    @Column(name = "account_approved_by")
    private String accountApprovedBy;
    
    @Column(name = "account_approved_date")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date accountApprovedDate;
    
    public DepoDeposit(){
    	depositAmount = 0.0;
    	account = new BankAccount();
    	depo = new Depo();
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

	public Depo getDepo() {
		return depo;
	}

	public void setDepo(Depo depo) {
		this.depo = depo;
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
			return "<a href='./depoDeposit.html?id="+super.getId()+"'>edit</a>";
		else
			return "";
	}
	
	public String getDeleteLink(){
		if(!this.accountApproved)
			return "<a href='javascript:deleteLinkClicked(\"./depoDepositDelete.html?id="+super.getId()+"&did="+this.depo.getId()+"\")'>delete</a>";
		else
			return "";
	}
	
	public String getApproveLink(){
		return "<a href='javascript:doApprove(\"./approveDepoDeposit.html?id="+super.getId()+"\")'>approve</a>";
	}

}