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

	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}	
}