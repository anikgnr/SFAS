package com.codeyard.sfas.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="cy_re_depo_order")
public class DepoOrder extends AbstractBaseEntity{
    
	@ManyToOne(optional=true)
    @JoinColumn(name="depo_id")
    private Depo depo;
    
	@ManyToOne(optional=true)
    @JoinColumn(name="depo_deposit_id")
    private DepoDeposit lastDeposit;
    
	@Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private  Date orderDate;
    
	@Column(name = "order_amount")
    private  Double orderAmount;       
    
	@Column(name = "is_delivered")
    private boolean delivered;
    
	@Column(name = "is_mis_approved")
    private boolean misApproved;
    
    @Column(name = "mis_approved_by")
    private String misApprovedBy;
    
    @Column(name = "mis_approved_date")
    @Temporal(TemporalType.DATE)
    private  Date misApprovedDate;
    
    @Column(name = "is_account_approved")
    private boolean accountApproved;
    
    @Column(name = "account_approved_by")
    private String accountApprovedBy;
    
    @Column(name = "account_approved_date")
    @Temporal(TemporalType.DATE)
    private  Date accountApprovedDate;
    
    @Column(name = "is_mgr_approved")
    private boolean mgrApproved;
    
    @Column(name = "mgr_approved_by")
    private String mgrApprovedBy;
    
    @Column(name = "mgr_approved_date")
    @Temporal(TemporalType.DATE)
    private  Date mgrApprovedDate;
    
    @Column(name = "is_mm_approved")
    private boolean mmApproved;
    
    @Column(name = "mm_approved_by")
    private String mmApprovedBy;
    
    @Column(name = "mm_approved_date")
    @Temporal(TemporalType.DATE)
    private  Date mmApprovedDate;
    
    @Column(name = "is_md_approved")
    private boolean mdApproved;
    
    @Column(name = "md_approved_by")
    private String mdApprovedBy;
    
    @Column(name = "md_approved_date")
    @Temporal(TemporalType.DATE)
    private  Date mdApprovedDate;
    
    @Transient
    private List<DepoOrderLi> orderLiList;
    
    @Transient
    private boolean hasError;
    
    @Transient
    private String errorMsg;
    
    public DepoOrder(){
    	orderAmount = 0.0;
    	depo = new Depo();
    	lastDeposit = new DepoDeposit();
    	delivered = false;
    	misApproved = false;
    	accountApproved = false;
    	mgrApproved = false;
    	mmApproved = false;
    	mdApproved = false;
    	orderLiList = new ArrayList<DepoOrderLi>();
    	hasError = false;
    }

	public Depo getDepo() {
		return depo;
	}

	public void setDepo(Depo depo) {
		this.depo = depo;
	}

	public DepoDeposit getLastDeposit() {
		return lastDeposit;
	}

	public void setLastDeposit(DepoDeposit lastDeposit) {
		this.lastDeposit = lastDeposit;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public boolean isMisApproved() {
		return misApproved;
	}

	public void setMisApproved(boolean misApproved) {
		this.misApproved = misApproved;
	}

	public String getMisApprovedBy() {
		return misApprovedBy;
	}

	public void setMisApprovedBy(String misApprovedBy) {
		this.misApprovedBy = misApprovedBy;
	}

	public Date getMisApprovedDate() {
		return misApprovedDate;
	}

	public void setMisApprovedDate(Date misApprovedDate) {
		this.misApprovedDate = misApprovedDate;
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

	public boolean isMgrApproved() {
		return mgrApproved;
	}

	public void setMgrApproved(boolean mgrApproved) {
		this.mgrApproved = mgrApproved;
	}

	public String getMgrApprovedBy() {
		return mgrApprovedBy;
	}

	public void setMgrApprovedBy(String mgrApprovedBy) {
		this.mgrApprovedBy = mgrApprovedBy;
	}

	public Date getMgrApprovedDate() {
		return mgrApprovedDate;
	}

	public void setMgrApprovedDate(Date mgrApprovedDate) {
		this.mgrApprovedDate = mgrApprovedDate;
	}

	public boolean isMmApproved() {
		return mmApproved;
	}

	public void setMmApproved(boolean mmApproved) {
		this.mmApproved = mmApproved;
	}

	public String getMmApprovedBy() {
		return mmApprovedBy;
	}

	public void setMmApprovedBy(String mmApprovedBy) {
		this.mmApprovedBy = mmApprovedBy;
	}

	public Date getMmApprovedDate() {
		return mmApprovedDate;
	}

	public void setMmApprovedDate(Date mmApprovedDate) {
		this.mmApprovedDate = mmApprovedDate;
	}

	public boolean isMdApproved() {
		return mdApproved;
	}

	public void setMdApproved(boolean mdApproved) {
		this.mdApproved = mdApproved;
	}

	public String getMdApprovedBy() {
		return mdApprovedBy;
	}

	public void setMdApprovedBy(String mdApprovedBy) {
		this.mdApprovedBy = mdApprovedBy;
	}

	public Date getMdApprovedDate() {
		return mdApprovedDate;
	}

	public void setMdApprovedDate(Date mdApprovedDate) {
		this.mdApprovedDate = mdApprovedDate;
	}

	public List<DepoOrderLi> getOrderLiList() {
		return orderLiList;
	}

	public void setOrderLiList(List<DepoOrderLi> orderLiList) {
		this.orderLiList = orderLiList;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getLastApprovedBy(){
		if(this.mdApproved){
			return ManagerType.MD.getValue();
		}else if(this.mmApproved){
			return ManagerType.MM.getValue();
		}else if(this.mgrApproved){
			return ManagerType.Manager.getValue();
		}else if(this.accountApproved){
			return ManagerType.ACCOUNT.getValue();
		}else if(this.misApproved){
			return ManagerType.MIS.getValue();
		}else{
			return "NONE";
		}
	}
	
	public String getEditLink(){
		if(!this.misApproved){
			return "<a href='./depoOrder.html?id="+super.getId()+"'>edit</a>";
		}else if(this.mdApproved){
			return "<a href='./depoOrder.html?rd=1&id="+super.getId()+"'>view</a>";			
		}else{
			return "<a href='javascript:alert('Order already in Approval process. Cannot alter it anymore.');'>edit</a>";
		}
	}
	
	public String getDeleteLink(){
		if(!this.misApproved){
			return "<a href='javascript:deleteLinkClicked(\"./depoOrderDelete.html?id="+super.getId()+"&did="+this.depo.getId()+"\")'>delete</a>";
		}
		return "";
	}
}