package com.codeyard.sfas.entity;


public enum Role {
	 ADMIN("Site Admin", "ROLE_ADMIN"), OPERATOR("Site Operator", "ROLE_OPERATOR"),
	 INVENTORY_ADMIN("Inventory Admin", "ROLE_INVENTORY_ADMIN"), INVENTORY_OPERATOR("Inventory Operator", "ROLE_INVENTORY_OPERATOR"), 
	 REPORT_MGR("Report Manager", "ROLE_REPORT_MGR"), REPORT_OPERATOR("Report Operator", "ROLE_REPORT_OPERATOR");
	 
	private String label;
	private String value;

	private Role(String label, String value) {
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
		for (Role type : values()) {
			if (type.getValue().equals(value)) {
				return type.getLabel();				
			}
		}
		return value;
	}
	
	public static Role[] getAllRoles(){
		return values();
	}
	
}
