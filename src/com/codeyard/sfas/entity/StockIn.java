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
    
    @Column(name = "stock_description")
    private  String comment;
    
    @Column(name = "is_used")
    private  boolean used;
        
    @Transient
    private Long previousQuantity;
    
    public StockIn(){
    	quantity = 0L;
    	previousQuantity = 0L;
    	product = new Product();
    	used = false;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
 
	public Long getPreviousQuantity() {
		return previousQuantity;
	}

	public void setPreviousQuantity(Long previousQuantity) {
		this.previousQuantity = previousQuantity;
	}

	
	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public void merge(StockIn stockIn){
		this.product = stockIn.getProduct();
		this.stockInDate = stockIn.getStockInDate();
		this.manufactureDate = stockIn.getManufactureDate();
		this.expireDate = stockIn.getExpireDate();
		this.quantity = stockIn.getQuantity();
		this.comment = stockIn.getComment();
	}

}