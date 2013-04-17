package com.codeyard.sfas.entity;


public enum NotificationType {
	 INVENTORY_STOCK_IN("Inventory Stock In"), 
	 INVENTORY_STOCK_APPROVED("Inventory Stock Approved"), 
	 INVENTORY_STOCK_DELETED("Inventory Stock Deleted"),
	 DEPO_DEPOSIT_IN("Depo Deposit In"), 
	 DEPO_DEPOSIT_APPROVED("Depo Deposit Approved"), 
	 DEPO_DEPOSIT_DELETED("Depo Deposit Deleted"),
	 DISTRIBUTOR_DEPOSIT_IN("Distributor Deposit In"), 
	 DISTRIBUTOR_DEPOSIT_APPROVED("Distributor Deposit Approved"), 
	 DISTRIBUTOR_DEPOSIT_DELETED("Distributor Deposit Deleted"),
	 DEPO_ORDER_IN("Depo Order In"),
	 DEPO_ORDER_DELETED("Depo Order Deleted"),
	 DEPO_ORDER_APPROVED("Depo Order Approved"),
	 DEPO_ORDER_UNAPPROVED("Depo Order UnApproved"),
	 DEPO_ORDER_DELIVERED("Depo Order Delivered"),
	 DISTRIBUTOR_ORDER_IN("Distributor Order In"),
	 DISTRIBUTOR_ORDER_DELETED("Distributor Order Deleted"),
	 DISTRIBUTOR_ORDER_APPROVED("Distributor Order Approved"),
	 DISTRIBUTOR_ORDER_UNAPPROVED("Distributor Order UnApproved"),
	 DISTRIBUTOR_ORDER_DELIVERED("Distributor Order Delivered");
	 
	 
	private String value;

	private NotificationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static NotificationType[] getAllTypes(){
		return values();
	}
	
}
