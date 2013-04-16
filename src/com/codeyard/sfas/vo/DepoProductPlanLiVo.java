package com.codeyard.sfas.vo;


public class DepoProductPlanLiVo {
    
    
    private String productName;    
    private long quantity;
    private long used;
    
        
    public DepoProductPlanLiVo(){
    	quantity = 0;
    	used = 0;
    }
    
    public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public long getUsed() {
		return used;
	}


	public void setUsed(long used) {
		this.used = used;
	}

	
	public long getBalance(){
		if((this.quantity - this.used) > 0)
			return this.quantity - this.used; 
		else
			return 0;
	}
}