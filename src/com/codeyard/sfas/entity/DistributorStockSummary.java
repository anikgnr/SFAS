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
@Table(name="cy_re_distributor_stock_summary")
public class DistributorStockSummary extends AbstractBaseEntity{
    
    
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
    @JoinColumn(name="distributor_id")
    private Distributor distributor;
    
    
    public DistributorStockSummary(){
    	quantity = 0L;
    	product = new Product();
    	distributor = new Distributor();
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

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}
	
}