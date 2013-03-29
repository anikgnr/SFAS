package com.codeyard.sfas.entity;


public enum ManagerType {
	 MIS("MIS", "mis"), ACCOUNT("Accounts", "accounts"), Manager("Manager", "manager"),
	 MM("MM", "mm"), MD("MD", "md");
	 
	private String label;
	private String value;

	private ManagerType(String label, String value) {
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
		for (ManagerType type : values()) {
			if (type.getValue().equals(value)) {
				return type.getLabel();				
			}
		}
		return value;
	}
	
	public static ManagerType[] getAllTypes(){
		return values();
	}
	
}
