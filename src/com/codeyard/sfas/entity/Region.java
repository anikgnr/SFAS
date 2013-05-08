package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cy_be_regions")
public class Region extends AbstractLookUpEntity{
    	
	public String getEditLink(){
		return "<a href='./region.html?id="+super.getId()+"'>edit</a>";
	}
	
	public String getDeleteLink(){
		return "<a href='javascript:deleteLinkClicked(\"./regionDelete.html?id="+super.getId()+"\")'>delete</a>";
	}
}