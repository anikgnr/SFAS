package com.codeyard.sfas.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

@Entity
@Table(name="cy_re_distributor_product_plans")
public class DistributorProductPlan extends AbstractBaseEntity{
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name="distributor_id")
    private Distributor distributor;
    
    @Column(name = "plan_month")
    private int planMonth;
    
    @Column(name = "plan_year")
    private int planYear;
        
    @Transient
    List<DistributorProductPlanLi> planLiList;
    
    public DistributorProductPlan(){
    	distributor = new Distributor();    	
    }

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public int getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(int planMonth) {
		this.planMonth = planMonth;
	}

	public int getPlanYear() {
		return planYear;
	}

	public void setPlanYear(int planYear) {
		this.planYear = planYear;
	}

	public List<DistributorProductPlanLi> getPlanLiList() {
		return planLiList;
	}

	public void setPlanLiList(List<DistributorProductPlanLi> planLiList) {
		this.planLiList = planLiList;
	}

	
}