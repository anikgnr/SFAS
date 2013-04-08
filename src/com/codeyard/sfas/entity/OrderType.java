package com.codeyard.sfas.entity;


public enum OrderType {
	 DEPO("Depo", "DEPO"), DISTRIBUTOR("Distributor", "DISTRIBUTOR");
	 
	private String label;
	private String value;

	private OrderType(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getLabelForValue(String value) {
		for (OrderType type : values()) {
			if (type.getValue().equals(value)) {
				return type.getLabel();				
			}
		}
		return value;
	}
	
	public static OrderType[] getAllTypes(){
		return values();
	}
	
}
