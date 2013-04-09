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
@Table(name="cy_re_distributor_sell_summary")
public class DistributorSellSummary extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;
        
    @Column(name = "sell_quantity")
    private  Long quantity;
    
    @Column(name = "last_sell_date")
    @Temporal(TemporalType.DATE)
    private  Date lastSellDate;
  
    @ManyToOne(optional=true)
    @JoinColumn(name="distributor_id")
    private Distributor distributor;
    
    
    public DistributorSellSummary(){
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

	public Date getLastSellDate() {
		return lastSellDate;
	}

	public void setLastSellDate(Date lastSellDate) {
		this.lastSellDate = lastSellDate;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}
	
}