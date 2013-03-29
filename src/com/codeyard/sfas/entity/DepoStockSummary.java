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
@Table(name="cy_re_depo_stock_summary")
public class DepoStockSummary extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
        
    @Column(name = "current_quantity")
    private  Long quantity;
    
    @Column(name = "last_stock_in_date")
    @Temporal(TemporalType.DATE)
    private  Date lastStockInDate;
  
    @Column(name = "last_stock_out_date")
    @Temporal(TemporalType.DATE)
    private  Date lastStockOutDate;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="depo_id")
    private Depo depo;
    
    
    public DepoStockSummary(){
    	quantity = 0L;
    	product = new Product();
    	depo = new Depo();
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Date getLastStockInDate() {
		return lastStockInDate;
	}

	public void setLastStockInDate(Date lastStockInDate) {
		this.lastStockInDate = lastStockInDate;
	}

	public Date getLastStockOutDate() {
		return lastStockOutDate;
	}

	public void setLastStockOutDate(Date lastStockOutDate) {
		this.lastStockOutDate = lastStockOutDate;
	}

	public Depo getDepo() {
		return depo;
	}

	public void setDepo(Depo depo) {
		this.depo = depo;
	}
	
}