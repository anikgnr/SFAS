package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cy_be_territories")
public class Territory extends AbstractLookUpEntity{
 
	@ManyToOne(optional=true)
    @JoinColumn(name="area_id")
    private Area area;

	public Territory(){
		this.area = new Area();
	}
	
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}	
	
	public String getEditLink(){
		return "<a href='./territory.html?id="+super.getId()+"'>edit</a>";
	}
	
	public String getDeleteLink(){
		return "<a href='javascript:deleteLinkClicked(\"./territoryDelete.html?id="+super.getId()+"\")'>delete</a>";
	}
}