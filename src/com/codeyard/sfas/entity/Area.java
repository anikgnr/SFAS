package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cy_be_areas")
public class Area extends AbstractLookUpEntity{
 
	@ManyToOne(optional=true)
    @JoinColumn(name="region_id")
    private Region region;

	public Area(){
		this.region = new Region();
	}
	
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
    
	public String getEditLink(){
		return "<a href='./area.html?id="+super.getId()+"'>edit</a>";
	}
	
	public String getDeleteLink(){
		return "<a href='javascript:deleteLinkClicked(\"./areaDelete.html?id="+super.getId()+"\")'>delete</a>";
	}
	
}