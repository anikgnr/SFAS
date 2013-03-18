package com.codeyard.sfas.entity;


public enum DamageType {
	 QUALITY("Quality Problem", "Quality Problem"), PACKAGE("Packaging Fault", "Packaging Fault"),
	 EXPIRED("Date Expired", "Date Expired"), INSECT("Insect Damage", "Insect Damage");
	 
	private String label;
	private String value;

	private DamageType(String label, String value) {
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
		for (DamageType type : values()) {
			if (type.getValue().equals(value)) {
				return type.getLabel();				
			}
		}
		return value;
	}
	
	public static DamageType[] getAllDamageTypes(){
		return values();
	}
	
}
