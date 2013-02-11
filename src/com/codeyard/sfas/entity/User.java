package com.wk.aml.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="wk_be_states")
public class State {

	@Id
	@Column(name = "id")
    private Long id;    
    
    @Column(name = "code")
    private String code;

    
    @ManyToOne(optional=true)
    @JoinColumn(name="country")
    private Country country;

    @Column(name = "zip_code_range")
    private String range;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}


}