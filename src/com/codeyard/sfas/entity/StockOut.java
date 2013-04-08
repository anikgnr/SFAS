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
@Table(name="cy_re_stocks_out")
public class StockOut extends AbstractBaseEntity{
    
	@ManyToOne(optional=true)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "quantity")
    private  Long quantity;
    
    @Column(name = "order_from")
    private  String orderFrom;
    
    @Column(name = "order_id")
    private  Long orderId;

    @Column(name = "stock_out_date")
    @Temporal(TemporalType.DATE)
    private  Date stockOutDate;
        
    public StockOut(){
    	orderId = 0L;
    }

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getStockOutDate() {
		return stockOutDate;
	}

	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}    

}