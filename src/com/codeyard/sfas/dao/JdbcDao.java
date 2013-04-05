package com.codeyard.sfas.dao;

import java.util.List;

import com.codeyard.sfas.entity.DepoOrderLi;


public interface JdbcDao { 

	boolean isStockInAlreadyUsedById(Long stockInId);
	boolean hasUnDeliveredOrderForDepo(Long depoId);
	List<DepoOrderLi> getLiteDepoOrderLiList(Long depoOrderId);
}

