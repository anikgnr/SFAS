package com.codeyard.sfas.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
public abstract class AbstractBaseEntity {

	@Id
	@Column(name = "id")
    private Long id;    

	@Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date created;

	@Column(name = "created_by")
    private String createdBy;    

	@Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date lastModified;
	
	@Column(name = "last_modified_by")
    private String lastModifiedBy;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}    

	
}