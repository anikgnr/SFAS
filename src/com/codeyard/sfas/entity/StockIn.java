package com.codeyard.sfas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.codeyard.sfas.util.Utils;

@Entity
@Table(name="cy_re_stocks_in")
public class StockIn extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
    
    @Column(name = "stock_in_date")
    @Temporal(TemporalType.DATE)
    private  Date stockInDate;
    
    @Column(name = "manufacture_date")
    @Temporal(TemporalType.DATE)
    private  Date manufactureDate;
    
    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private  Date expireDate;
    
    @Column(name = "quantity")
    private  Long quantity;
    
    @Column(name = "is_approved")
    private  Boolean approved;
    
    @Column(name = "approved_by")
    private  String approvedBy;
    
    @Column(name = "approved_date")
    @Temporal(TemporalType.DATE)
    private  Date approvedDate;
    
        
    public StockIn(){
    	quantity = 0L;
    	product = new Product();
    	approved = false;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getStockInDate() {
		return stockInDate;
	}

	public void setStockInDate(Date stockInDate) {
		this.stockInDate = stockInDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
	@Override
	public String getEditLink(){
		if(!this.approved && Utils.isInRole(Role.INVENTORY_ADMIN.getValue())){
			return "<a href='javascript:deleteLinkClicked(\"./stockinApprove.html?id="+super.getId()+"\")'>approve</a>";
		}else
			return "";
	}

	@Override
	public String getDeleteLink(){
		if(!this.approved && Utils.isInRole(Role.INVENTORY_ADMIN.getValue())){
			return "<a href='javascript:deleteLinkClicked(\"./stockinDelete.html?id="+super.getId()+"\")'>delete</a>";
		}else
			return "";
	}
}