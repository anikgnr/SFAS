package com.codeyard.sfas.vo;


public class SearchVo {
	
	public String sql;
	public Object[] params;
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}	
	
}
