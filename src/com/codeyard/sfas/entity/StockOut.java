package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="cy_re_stocks_out")
public class StockOut extends AbstractBaseEntity{
    
	@ManyToOne(optional=true)
    @JoinColumn(name="stock_in_id")
    private StockIn stockIn;

    @Column(name = "quantity")
    private  Long quantity;
    
    @Column(name = "order_from")
    private  String orderFrom;
    
    @Column(name = "order_id")
    private  Long orderId;

    
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

	public StockIn getStockIn() {
		return stockIn;
	}

	public void setStockIn(StockIn stockIn) {
		this.stockIn = stockIn;
	}    

}