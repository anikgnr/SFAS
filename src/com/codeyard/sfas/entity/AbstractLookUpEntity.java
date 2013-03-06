package com.codeyard.sfas.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Column;


@MappedSuperclass
public abstract class AbstractLookUpEntity {

	@Id
	@Column(name = "id")
    private Long id;    

    @Column(name = "region_name")
    private String name;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}