package com.codeyard.sfas.dao;

import java.util.List;

import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DistributorOrderLi;


public interface JdbcDao { 

	boolean isStockInAlreadyUsedById(Long stockInId);
	boolean hasUnDeliveredOrderForDepo(Long depoId);
	List<DepoOrderLi> getLiteDepoOrderLiList(Long depoOrderId);
	boolean hasUnDeliveredOrderForDistributor(Long distributorId);
	List<DistributorOrderLi> getLiteDistributorOrderLiList(Long distributorOrderId);
}

