package com.codeyard.sfas.entity;

import java.util.List;


public enum Role {
	 ADMIN("Site Admin", "ROLE_ADMIN"), OPERATOR("Site Operator", "ROLE_OPERATOR");
	 
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
