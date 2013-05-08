package com.codeyard.sfas.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cy_be_routes")
public class Route extends AbstractLookUpEntity{
 
	@ManyToOne(optional=true)
    @JoinColumn(name="territory_id")
    private Territory territory;

	public Route(){
		this.territory = new Territory();
	}
	
	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public String getEditLink(){
		return "<a href='./route.html?id="+super.getId()+"'>edit</a>";
	}
	
	public String getDeleteLink(){
		return "<a href='javascript:deleteLinkClicked(\"./routeDelete.html?id="+super.getId()+"\")'>delete</a>";
	}
	
}