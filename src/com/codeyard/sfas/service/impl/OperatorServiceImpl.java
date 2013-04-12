package com.codeyard.sfas.service.impl;

import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeyard.sfas.dao.JdbcDao;
import com.codeyard.sfas.dao.OperatorDao;
import com.codeyard.sfas.entity.Depo;
import com.codeyard.sfas.entity.DepoDamageSummary;
import com.codeyard.sfas.entity.DepoDeposit;
import com.codeyard.sfas.entity.DepoOrder;
import com.codeyard.sfas.entity.DepoOrderLi;
import com.codeyard.sfas.entity.DepoSellSummary;
import com.codeyard.sfas.entity.DepoStockSummary;
import com.codeyard.sfas.entity.ManagerType;
import com.codeyard.sfas.entity.OrderType;
import com.codeyard.sfas.entity.StockOut;
import com.codeyard.sfas.entity.StockSummary;
import com.codeyard.sfas.service.AdminService;
import com.codeyard.sfas.service.InventoryService;
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

	@Autowired(required=true)
	private InventoryService inventoryService;
	
	@Autowired(required=true)
	private JdbcDao jdbcDao;

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
	    	adminService.onlySaveOrUpdate(deposit);
	    	
	    	Double currentBalance = deposit.getDepo().getCurrentBalance();
	    	if(currentBalance == null)
	    		currentBalance = 0.0;
	    	deposit.getDepo().setCurrentBalance(currentBalance+deposit.getDepositAmount());
	    	adminService.onlySaveOrUpdate(deposit.getDepo());
	    	
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
		List<StockSummary> stocks = inventoryService.getCurrentStockList(new StockSearchVo());
		List<DepoOrderLi> orderLiList = jdbcDao.getLiteDepoOrderLiList(order.getId());
		Long oldQty = 0L;
		Long newQty = 0L;
		for(StockSummary stock : stocks){
			oldQty = 0L;
			newQty = 0L;
			
			for(DepoOrderLi oldLi : orderLiList){
				if(stock.getProduct().getId() == oldLi.getProduct().getId()){
					oldQty = oldLi.getQuantity();
					break;
				}
			}
			
			for(DepoOrderLi newLi : order.getOrderLiList()){
				if(stock.getProduct().getId() == newLi.getProduct().getId()){
					newQty = newLi.getQuantity();
					break;
				}
			}
			logger.debug(stock.getProduct().getFullName()+" current stock :: "+stock.getQuantity());
			logger.debug(stock.getProduct().getFullName()+" old Order Qty :: "+oldQty);
			logger.debug(stock.getProduct().getFullName()+" new Order Qty :: "+newQty);
			stock.setQuantity(stock.getQuantity()+oldQty-newQty);
			adminService.saveOrUpdate(stock);
		}
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
		List<StockSummary> stocks = inventoryService.getCurrentStockList(new StockSearchVo());
		List<DepoOrderLi> orderLiList = getDepoOrderLiList(orderId);
		for(StockSummary stock : stocks){
			if(orderLiList != null){
				for(DepoOrderLi oldLi : orderLiList){
					if(stock.getProduct().getId() == oldLi.getProduct().getId()){						
						stock.setQuantity(stock.getQuantity()+oldLi.getQuantity());
						break;
					}
				}
			}
			adminService.saveOrUpdate(stock);			
		}
		operatorDao.deleteDepoOrderById(orderId);
	}
	
	public boolean hasUnDeliveredOrderForDepo(Long depoId){
		return jdbcDao.hasUnDeliveredOrderForDepo(depoId);
	}
	
	@Transactional(readOnly = false)
	public void approveDepoOrder(DepoOrder order, String type){
		if(ManagerType.MIS.getValue().equals(type)){
			order.setMisApproved(true);
			order.setMisApprovedBy(Utils.getLoggedUser());
			order.setMisApprovedDate(Utils.today());
		}else if(ManagerType.ACCOUNT.getValue().equals(type)){
			order.setAccountApproved(true);
			order.setAccountApprovedBy(Utils.getLoggedUser());
			order.setAccountApprovedDate(Utils.today());
		}else if(ManagerType.Manager.getValue().equals(type)){
			order.setMgrApproved(true);
			order.setMgrApprovedBy(Utils.getLoggedUser());
			order.setMgrApprovedDate(Utils.today());
		}else if(ManagerType.MM.getValue().equals(type)){
			order.setMmApproved(true);
			order.setMmApprovedBy(Utils.getLoggedUser());
			order.setMmApprovedDate(Utils.today());
		}else if(ManagerType.MD.getValue().equals(type)){
			order.setMdApproved(true);
			order.setMdApprovedBy(Utils.getLoggedUser());
			order.setMdApprovedDate(Utils.today());
		}
		adminService.onlySaveOrUpdate(order);
	}
	
	@Transactional(readOnly = false)
	public void unApproveDepoOrder(DepoOrder order, String type){
		if(ManagerType.ACCOUNT.getValue().equals(type)){
			order.setMisApproved(false);			
		}else if(ManagerType.Manager.getValue().equals(type)){
			order.setAccountApproved(false);			
		}else if(ManagerType.MM.getValue().equals(type)){
			order.setMgrApproved(false);			
		}else if(ManagerType.MD.getValue().equals(type)){
			order.setMmApproved(false);			
		}
		adminService.onlySaveOrUpdate(order);
	}
	
	@Transactional(readOnly = false)
	public void deliverDepoOrder(DepoOrder order){
		order.setDelivered(true);
		adminService.saveOrUpdate(order);
		
		Depo depo = order.getDepo();
		depo.setCurrentBalance(depo.getCurrentBalance() - order.getOrderAmount());
		adminService.onlySaveOrUpdate(depo);
		
		List <DepoOrderLi> orderLiList = getDepoOrderLiList(order.getId());
		if(orderLiList != null){
			for(DepoOrderLi orderLi : orderLiList){
				if(orderLi.getQuantity() <= 0)
					continue;
				
				DepoStockSummary stockSummary = operatorDao.getDepoStockSummaryByProductId(orderLi.getProduct().getId(), order.getDepo().getId());
				if(stockSummary == null){
					stockSummary = new DepoStockSummary();
					stockSummary.setDepo(depo);
					stockSummary.setProduct(orderLi.getProduct());
				}
				stockSummary.setQuantity(stockSummary.getQuantity() + orderLi.getQuantity());
				stockSummary.setLastStockInDate(Utils.today());
				adminService.saveOrUpdate(stockSummary);
				
				StockOut stockOut = new StockOut();
				stockOut.setProduct(orderLi.getProduct());
				stockOut.setQuantity(orderLi.getQuantity());
				stockOut.setOrderFrom(OrderType.DEPO.getValue());
				stockOut.setOrderId(order.getId());
				stockOut.setStockOutDate(Utils.today());
				adminService.saveOrUpdate(stockOut);
				
				StockSummary inventoryStockSummary = inventoryService.getStockSummaryByProductId(orderLi.getProduct().getId());
				if(inventoryStockSummary != null){
					inventoryStockSummary.setLastStockOutDate(Utils.today());
					adminService.saveOrUpdate(inventoryStockSummary);	
				}
				
			}
		}
		
	}
	
	public DepoStockSummary getDepoCurrentStockSummaryByProductIdAndDepoId(Long productId, Long depoId){
		StockSearchVo searchVo = new StockSearchVo();
		searchVo.setProductId(productId);
		searchVo.setDepoId(depoId);
		List<DepoStockSummary> stockList = getDepoCurrentStockList(searchVo);
		if(stockList != null && stockList.size() > 0)
			return stockList.get(0);		
		return null;
	}
	
	public DepoSellSummary getDepoSellSummaryByProductIdAndDepoId(Long productId, Long depoId){
		StockSearchVo searchVo = new StockSearchVo();
		searchVo.setProductId(productId);
		searchVo.setDepoId(depoId);
		List<DepoSellSummary> stockList = getDepoSellSummaryList(searchVo);
		if(stockList != null && stockList.size() > 0)
			return stockList.get(0);			
		return null;
	}
}