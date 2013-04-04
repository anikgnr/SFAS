package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.OperatorDao;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.OperatorService;
import com.codeyard.sfas.util.Utils;
import com.codeyard.sfas.vo.OprSearchVo;
import com.codeyard.sfas.vo.StockSearchVo;


@Service("operatorService")
@Transactional(readOnly = true)
public class OperatorServiceImpl implements OperatorService {
	private static Logger logger = Logger.getLogger(OperatorServiceImpl.class);

	@Autowired(required=true)
	private OperatorDao operatorDao;
	
	@Autowired(required=true)
	private AdminService adminService;
	
	public List<DepoStockSummary> getDepoCurrentStockList(StockSearchVo searchVo){
		return operatorDao.getDepoCurrentStockList(searchVo);
	}
	
	public List<DepoDamageSummary> getDepoDamageStockList(StockSearchVo searchVo){
		return operatorDao.getDepoDamageStockList(searchVo);
	}
	
	public List<DepoDeposit> getDepoDepositList(OprSearchVo searchVo){
		return operatorDao.getDepoDepositList(searchVo);
	}
	
	@Transactional(readOnly = false)
	public boolean approveDepoDeposit(Long depositId){
		
		DepoDeposit deposit = (DepoDeposit)adminService.loadEntityById(depositId,"DepoDeposit");
		if(deposit != null){
			deposit.setAccountApproved(true);
	    	deposit.setAccountApprovedBy(Utils.getLoggedUser());
	    	deposit.setAccountApprovedDate(Utils.today());
	    	adminService.saveOrUpdate(deposit);
	    	
	    	Double currentBalance = deposit.getDepo().getCurrentBalance();
	    	if(currentBalance == null)
	    		currentBalance = 0.0;
	    	deposit.getDepo().setCurrentBalance(currentBalance+deposit.getDepositAmount());
	    	adminService.saveOrUpdate(deposit.getDepo());
	    	
	    	return true;
		}
		return false;		
	}
	
	public List<DepoSellSummary> getDepoSellSummaryList(StockSearchVo searchVo){
		return operatorDao.getDepoSellSummaryList(searchVo);
	}
	
	public DepoDeposit getLatestDepoDeposit(Long depoId){
		return operatorDao.getLatestDepoDeposit(depoId);
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdateDepoOrder(DepoOrder order){		
		operatorDao.saveOrUpdateDepoOrder(order);
	}
	
	public List<DepoOrderLi> getDepoOrderLiList(Long depoOrderId){
		return operatorDao.getDepoOrderLiList(depoOrderId);
	}
	
	public List<DepoOrder> getDepoOrderList(OprSearchVo searchVo){
		return operatorDao.getDepoOrderList(searchVo);
	}
	
	@Transactional(readOnly = false)
	public void deleteDepoOrderById(Long orderId){
		operatorDao.deleteDepoOrderById(orderId);
	}
}