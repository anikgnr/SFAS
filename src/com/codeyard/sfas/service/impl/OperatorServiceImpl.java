package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.OperatorDao;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Service("operatorService")
@Transactional(readOnly = true)
public class OperatorServiceImpl implements OperatorService {
	private static Logger logger = Logger.getLogger(OperatorServiceImpl.class);

	@Autowired(required=true)
	private OperatorDao operatorDao;
	
	public List<DepoStockSummary> getDepoCurrentStockList(StockSearchVo searchVo){
		return operatorDao.getDepoCurrentStockList(searchVo);
	}
	
	public List<DepoDamageSummary> getDepoDamageStockList(StockSearchVo searchVo){
		return operatorDao.getDepoDamageStockList(searchVo);
	}
	
	public List<DepoDeposit> getDepoDepositList(OprSearchVo searchVo){
		return operatorDao.getDepoDepositList(searchVo);
	}
}