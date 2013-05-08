package com.codeyard.sfas.entity;


public enum Role {
	 ADMIN("System Admin", "ROLE_ADMIN"), MANAGER("System Manager", "ROLE_MANAGER"), OPERATOR("System Operator", "ROLE_OPERATOR"),
	 INVENTORY_ADMIN("Inventory Admin", "ROLE_INVENTORY_ADMIN"), INVENTORY_OPERATOR("Inventory Operator", "ROLE_INVENTORY_OPERATOR"),
	 FACTORY_MANAGER("Factory Manager", "ROLE_FACTORY_ADMIN"), FACTORY_OPERATOR("Factory Operator", "ROLE_FACTORY_OPERATOR");
	 
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
