package com.wk.aml.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wk.aml.dao.CTRDao;
import com.wk.aml.dao.CtrReportDao;
import com.wk.aml.entity.BaseCustomerEntity;
import com.wk.aml.entity.BeneficiaryEntity;
import com.wk.aml.entity.CTREntity;
import com.wk.aml.entity.ConductorEntity;
import com.wk.aml.entity.DBAMaintainanceEntity;
import com.wk.aml.entity.TinTypeEnum;
import com.wk.aml.entity.TransactionEntity;
import com.wk.aml.vo.AuditTrailVO;
import com.wk.aml.vo.PersonInvolvedVO;
import com.wk.aml.util.Constants;

public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);

	public static final String getCTRKey(HttpServletRequest request){
		return Constants.CTR_SESSION_KEY + request.getSession().getAttribute(Constants.CTR_ID_SESSION_KEY);
	}
	
	public static final String getTransactionKey(HttpServletRequest request){
		return Constants.TRANSACTION_SESSION_KEY + request.getSession().getAttribute(Constants.CTR_ID_SESSION_KEY);
	}
	
	public static final String getBeneficiaryKey(HttpServletRequest request){
		return Constants.BENEFICIARY_SESSION_KEY + request.getSession().getAttribute(Constants.CTR_ID_SESSION_KEY);
	}
	
	public static final String getConductorKey(HttpServletRequest request){
		return Constants.CONDUCTOR_SESSION_KEY + request.getSession().getAttribute(Constants.CTR_ID_SESSION_KEY);
	}
	
	public static final String getBranchKey(HttpServletRequest request){
		return Constants.BRANCH_SESSION_KEY + request.getSession().getAttribute(Constants.CTR_ID_SESSION_KEY);
	}
	
	public static final String getQualifiedCustomerName(Object obj) {
		String qcn = "";
		if (obj instanceof BeneficiaryEntity) {
			BeneficiaryEntity ben = (BeneficiaryEntity) obj;
			ben.setTinTypeName(TinTypeEnum.getLabelForValue(ben.getTinType()));
			if (TinTypeEnum.BUSINESS.equals(TinTypeEnum.getTinTypeByValue(ben.getTinType()))) {
				if(ben.getBusinessNameUnknown() != null && ben.getBusinessNameUnknown() == true)
					qcn = Constants.UNKNOWN_TEXT;
				else
					qcn = (ben.getBusinessName() != null) ? ben.getBusinessName() : "";
			} else {
				if(ben.getFirstNameUnknown() != null && ben.getFirstNameUnknown() == true
						&& ben.getLastNameUnknown() != null && ben.getLastNameUnknown() == true){
					qcn = Constants.UNKNOWN_TEXT;
				}else if (ben.getFirstName() != null && ben.getLastName() != null)
					qcn = ben.getFirstName()+" "+ben.getLastName();
				else if (ben.getFirstName() != null)
					qcn = ben.getFirstName();
				else if (ben.getLastName() != null)
					qcn = ben.getLastName();
			}
		} else if (obj instanceof ConductorEntity) {
			ConductorEntity con = (ConductorEntity) obj;
			con.setTinTypeName(TinTypeEnum.getLabelForValue(con.getTinType()));			
			if (TinTypeEnum.BUSINESS.equals(TinTypeEnum.getTinTypeByValue(con.getTinType()))) {
				qcn = (con.getBusinessName() != null) ? con.getBusinessName() : "";
			} else {
				if(con.getFirstNameUnknown() != null && con.getFirstNameUnknown() == true
					&& con.getLastNameUnknown() != null && con.getLastNameUnknown() == true){
					qcn = Constants.UNKNOWN_TEXT;
				}else if (con.getFirstName() != null && con.getLastName() != null)
					qcn = con.getFirstName()+" "+con.getLastName();
				else if (con.getFirstName() != null)
					qcn = con.getFirstName();
				else if (con.getLastName() != null)
					qcn = con.getLastName();
			}
		}  else {
			qcn = "";
		}
		return qcn;
	}
	
	public static String getTin(Object obj){
		StringBuffer buf = new StringBuffer();
		if (obj instanceof BeneficiaryEntity) {
			BeneficiaryEntity ben = (BeneficiaryEntity) obj;
			if(ben.getTaxIdUnknown() != null && ben.getTaxIdUnknown()){
				buf.append(Constants.UNKNOWN_TEXT);
			}else if(ben.getTin() != null){
				buf.append(ben.getTin());
			}
		}else if (obj instanceof ConductorEntity) {
			ConductorEntity con = (ConductorEntity) obj;
			if(con.getTaxIdUnknown() != null && con.getTaxIdUnknown()){
				buf.append(Constants.UNKNOWN_TEXT);
			}else if(con.getTin() != null ){
				buf.append(con.getTin());
			}
		}		
		return buf.toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getUniqueList(List list){
		Set uniqueSet = new LinkedHashSet(); //in-order to maintain the sequence and uniqueness of the list
		
		for(Object obj : list){
			uniqueSet.add(obj);			
		}
		List uniqueList = new ArrayList(uniqueSet);
		
		int serial = 0;
		for(Object obj : uniqueList){
			updateSeqEditDeleteLink(obj,serial);
			serial++;
		}
		
		return  uniqueList;
	} 
	
	public static final void updateSeqEditDeleteLink(Object obj, int serial){
		if(obj instanceof TransactionEntity){
			TransactionEntity te = (TransactionEntity)obj;
			//te.setEdit("<a href='./transaction.html?sq="+serial+"'>edit</a>");
			te.setEdit("<a href='javascript:void();' onclick='doTranEdit("+serial+");'>edit</a>");			
			te.setDelete("<span style='text-decoration: underline;cursor: pointer;' onclick='doTranDelete("+serial+");'>delete</span>");
			te.setView("<a href='./transaction.html?sq="+serial+"'>details</a>");
			te.setSequence(serial);
		}else if(obj instanceof BeneficiaryEntity){
			BeneficiaryEntity be = (BeneficiaryEntity)obj;
			be.setEdit("<a href='javascript:void();' onclick='doBenEdit("+serial+");'>edit</a>");			
			be.setDelete("<span style='text-decoration: underline;cursor: pointer;' onclick='doBenDelete("+serial+");'>delete</span>");
			be.setView("<a href='./beneficiary.html?sq="+serial+"'>details</a>");
			be.setSequence(serial);
		}else if(obj instanceof ConductorEntity){
			ConductorEntity ce = (ConductorEntity)obj;
			ce.setEdit("<a href='javascript:void();' onclick='doCondEdit("+serial+");'>edit</a>");
			ce.setDelete("<span style='text-decoration: underline;cursor: pointer;' onclick='doCondDelete("+serial+");'>delete</span>");
			ce.setView("<a href='./conductor.html?sq="+serial+"'>details</a>");
			ce.setSequence(serial);
		}		
	}
	
	public static final String getBeneficiaryName(List<BeneficiaryEntity> list){
		String benName = "";
		StringBuffer buffer=new StringBuffer("");
		for(BeneficiaryEntity be : list){
			be.setTransaction(null);
			buffer.append(be.getName()+", ");
		}
		if(buffer.length() > 0)
			benName = buffer.substring(0, buffer.length()-2);
		return benName;

	}
	
	public static final void addOrUpdateBeneficiarySet(Set<BeneficiaryEntity> beSet, BeneficiaryEntity be) {
		if(beSet.contains(be)){
			if(be.getAccNumber() != null && !be.getAccNumber().isEmpty()){				
				for(BeneficiaryEntity ben : beSet){					
					if(be.equals(ben)){
						if(ben.getAccNumber() != null && !ben.getAccNumber().isEmpty()){
							String allAccNum = ben.getAccNumber().replaceAll("<b>","").replaceAll("</b>","");							
							logger.debug("acc Num :: "+allAccNum);
							if(allAccNum.indexOf(be.getAccNumber()) == -1){
								if(be.getPrimaryAccHolder().equals("Y"))
									be.setAccNumber(be.getAccNumber());
								ben.setAccNumber(ben.getAccNumber()+","+be.getAccNumber());
							}
						}else{					
							ben.setAccNumber(be.getAccNumber());
						}
						if(be.getPrimaryAccHolder().equals("Y")){					
							ben.setAllPrAccHld(be.getPrimaryAccHolder());					
						}
						break;
					}
				}
			}
		}else{
			if(be.getPrimaryAccHolder().equals("Y")){
				be.setAccNumber(be.getAccNumber());
			}			
			be.setAllPrAccHld(be.getPrimaryAccHolder());
			beSet.add(be);
		}
	}
	
	public static final void setAccountNumberOnBenList(List<BeneficiaryEntity> benList, String accountNumber){
		if(accountNumber == null)
			accountNumber = "";
		
		for(BeneficiaryEntity be : benList){
			be.setAccNumber(accountNumber);
		}
	}
	
	public static final void setAccNumberOnSingleBen(BeneficiaryEntity be, String accNumber){
		if(accNumber == null)
			accNumber = "";
		be.setAccNumber(accNumber);		
	}

	public static final List<AuditTrailVO> getProcessedAuditTrialList(List<Object[]> list){
		
		if(list != null){
			List<AuditTrailVO> alist = new ArrayList<AuditTrailVO>();
			for(Object[] objArr: list){
				AuditTrailVO vo = new AuditTrailVO((Date)objArr[0], (String)objArr[1], 
						getAuditAction((String)objArr[2], (String)objArr[3], (String)objArr[7]), 
						getProcessedValue((String)objArr[8], (String)objArr[7], (String)objArr[10]),
						getProcessedValue((String)objArr[9], (String)objArr[7], (String)objArr[10]),
						(String)objArr[5]);
				
				addAuditTrialList(alist, vo);
			}
			return alist;
		}else
			return null;
	}
	
	public static String getFormattedCurrencyString(String amount){
		try{
			return "$"+NumberFormat.getInstance().format(Double.parseDouble(amount));
		}catch(Exception ex){
			logger.debug("error on Currency conversion : "+ex);
		}
		return "$0";
	}

	private static void addAuditTrialList(List<AuditTrailVO> list, AuditTrailVO vo){
		
		boolean included = false;
		for(AuditTrailVO voObj : list){
			if(vo.getAuditId().equals(voObj.getAuditId())){
				included = true;
				if(!isNullOrEmpty(vo.getPreviousValue())){
					if(!isNullOrEmpty(voObj.getPreviousValue()))
						voObj.setPreviousValue(voObj.getPreviousValue()+", "+vo.getPreviousValue());					
					else
						voObj.setPreviousValue(vo.getPreviousValue());
				}
				
				if(!isNullOrEmpty(vo.getNewValue())){
					if(!isNullOrEmpty(voObj.getNewValue()))
						voObj.setNewValue(voObj.getNewValue()+", "+vo.getNewValue());
					else
						voObj.setNewValue(vo.getNewValue());
				}
				break;
			}
		}
		if(!included)
			list.add(vo);
	}
	
	private static String getAuditAction(String action, String objectType, String fieldLabel){
		if(action != null){
			if(objectType != null){
				if(Constants.AUDIT_CTR_FIELD_LABEL.equals(objectType) 
						&& Constants.AUDIT_STATUS_FIELD_LABEL.equals(fieldLabel)){					
					action += " "+fieldLabel;
				}else{
					action += " "+objectType;
				}
			}		
			return action;
		}else
			return "";
	}
	
	private static String getProcessedValue(String value, String fieldLabel, String fieldType){
		String newValue = "";
		if(!isNullOrEmpty(value) && !isNullOrEmpty(fieldLabel) && !isNullOrEmpty(fieldType)){
			newValue +=  "<b>"+fieldLabel + "</b>: ";
			if(Constants.AUDIT_FIELD_TYPE_CASH.equals(fieldType) || 
					Constants.AUDIT_FIELD_TYPE_FOREIGN_CASH.equals(fieldType)){
				
				newValue += getFormattedCurrencyString(value);
				
			}else if(Constants.AUDIT_FIELD_TYPE_TEXT.equals(fieldType)){
				
				if(Constants.AUDIT_STATUS_FIELD_LABEL.equals(fieldLabel))
					newValue = value;
				else
					newValue += value;
				
			}else if(Constants.AUDIT_FIELD_TYPE_FLAG.equals(fieldType)){				
				
				if("1".equals(value))
					newValue += Constants.AUDIT_FLAG_ON;
				else 
					newValue += Constants.AUDIT_FLAG_OFF;
				
			}else{
				// need to put check for DATE, FLAG & PHONE
				newValue += value;
			}
		}
		return newValue;
	}

	public static boolean isNullOrEmpty(String content){
		if(content != null && !content.isEmpty())
			return false;
		else
			return true;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getLoggedUserRoles(){
		List<String> roles = new ArrayList<String>();
		
		List<GrantedAuthority> grantedRoles = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	    
		for(GrantedAuthority role : grantedRoles){
			logger.debug("role name : "+role.getAuthority());
			roles.add(role.getAuthority());
		}
		
		return roles;
	}

	public static String getAmlConfigPropertyValue(String key){
		try{
			Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("../configurations/properties/aml_config.properties"));			
			return props.getProperty(key);
		}catch(Exception ex){
			logger.debug("error : "+ex);
			return "";
		}
	}
	
	public static String getMessageBundlePropertyValue(String key){
		try{
			Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("../configurations/messages/messages_en.properties"));			
			return props.getProperty(key);
		}catch(Exception ex){
			logger.debug("error : "+ex);
			return "";
		}
	}

	public static Date getDateFromString(String format, String value){
		try {
			if( value != null && value.length() > 0 ){
				return new SimpleDateFormat(format).parse(value);
			}
		} catch (Exception e) {
			logger.debug("error : "+e);
		}
		return null;
	}
	
	public static String getStringFromDate(String format, Date value){
		try {
			return new SimpleDateFormat(format).format(value);
		} catch (Exception e) {
			logger.debug("error : "+e);
		}
		return "";
	}
	
	public static Date today(){
		return new Date();
	}
	
	public static Boolean isECTR(HttpServletRequest request){
		if(request.getSession().getAttribute(Utils.getCTRKey(request)) != null){
			CTREntity ctr = (CTREntity)request.getSession().getAttribute(Utils.getCTRKey(request));
			if(ctr != null && ctr.getId() != null && ctr.getId() > 0)
				return true;
			else
				return false;
		}else{
			return false;
		}
	}
	
	public static void loadDBAInfoOnBeneficiary(BeneficiaryEntity be, DBAMaintainanceEntity dba){
		
		if(dba != null){
			be.setDbaEin(dba.getEin());
			be.setDbaTaName(dba.getTaName());
			be.setDbaAdd1(dba.getAddress1());
			be.setDbaAdd2(dba.getAddress2());
			be.setDbaAdd3(dba.getAddress3());
			be.setDbaAdd4(dba.getAddress4());
			be.setDbaCity(dba.getCity());
			be.setDbaState(dba.getState());
			be.setDbaCountyId(dba.getCountryId());
			be.setDbaZip(dba.getZipCode());
			be.setDbaOccupation(dba.getOccupation());
			be.setDbaCustId(dba.getCustomerId());
		}else{
			be.setDbaEin(null);
			be.setDbaTaName(null);
			be.setDbaAdd1(null);
			be.setDbaAdd2(null);
			be.setDbaAdd3(null);
			be.setDbaAdd4(null);
			be.setDbaCity(null);
			be.setDbaState(null);
			be.setDbaCountyId(null);
			be.setDbaZip(null);
			be.setDbaOccupation(null);
			be.setDbaCustId(null);
		}
	}
	
	public static void setMessage(HttpServletRequest request, String message){
		
		request.getSession().setAttribute(Constants.GLOBAL_MSG_KEY, message);
	}
	
	public static String getMessage(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.GLOBAL_MSG_KEY) != null){
			String message = (String)request.getSession().getAttribute(Constants.GLOBAL_MSG_KEY);
			request.getSession().removeAttribute(Constants.GLOBAL_MSG_KEY);
			return message;
		}
		return "";
	}
	
	public static void setInlineMessage(HttpServletRequest request, String message){
		
		request.getSession().setAttribute(Constants.GLOBAL_INLINE_MSG_KEY, message);
	}
	
	public static String getInlineMessage(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.GLOBAL_INLINE_MSG_KEY) != null){
			String message = (String)request.getSession().getAttribute(Constants.GLOBAL_INLINE_MSG_KEY);
			request.getSession().removeAttribute(Constants.GLOBAL_INLINE_MSG_KEY);
			return message;
		}
		return "";
	}
	
	public static void setConfirmationMessage(HttpServletRequest request, String message, String okUrl, String cancelUrl){
		
		request.getSession().setAttribute(Constants.GLOBAL_CONFIRM_MSG_KEY, message);
		request.getSession().setAttribute(Constants.GLOBAL_CONFIRM_OK_URL_KEY, okUrl);
		request.getSession().setAttribute(Constants.GLOBAL_CONFIRM_CANCEL_URL_KEY, cancelUrl);
	}
	
	public static String getConfirmationMessage(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.GLOBAL_CONFIRM_MSG_KEY) != null){
			String message = (String)request.getSession().getAttribute(Constants.GLOBAL_CONFIRM_MSG_KEY);
			request.getSession().removeAttribute(Constants.GLOBAL_CONFIRM_MSG_KEY);
			return message;
		}
		return "";
	}
	
	public static String getConfirmationForwardUrl(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.GLOBAL_CONFIRM_OK_URL_KEY) != null){
			String message = (String)request.getSession().getAttribute(Constants.GLOBAL_CONFIRM_OK_URL_KEY);
			request.getSession().removeAttribute(Constants.GLOBAL_CONFIRM_OK_URL_KEY);
			return message;
		}
		return "";
	}
	
	public static String getConfirmationCancelUrl(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.GLOBAL_CONFIRM_CANCEL_URL_KEY) != null){
			String message = (String)request.getSession().getAttribute(Constants.GLOBAL_CONFIRM_CANCEL_URL_KEY);
			request.getSession().removeAttribute(Constants.GLOBAL_CONFIRM_CANCEL_URL_KEY);
			return message;
		}
		return "";
	}
	
	public static String getExemptionMessage(List<BeneficiaryEntity> beList){
		StringBuffer message = new StringBuffer("This CTR includes one or more beneficiaries exempted:\n");
		for(BeneficiaryEntity be : beList){
			message.append("\n"+be.getTin()+", ");
			if(TinTypeEnum.BUSINESS.getValue().equals(be.getTinType())){
				message.append((be.getBusinessName()));
			}else{
				message.append(be.getLastName()+", "+be.getFirstName());
			}
			if(Constants.EXEMPTION_PHASE_I.equals(be.getPhase())){
				message.append(" has a Phase I exemption status") ;
			}else if(Constants.EXEMPTION_PHASE_II.equals(be.getPhase())){
				message.append(" has a Phase II exemption status");
			}
		}
		message.append("\n\nDo you wish to continue? Click 'OK' to continue with the CTR Entry,  'Cancel' to quit this CTR entry.");
		message.append("\n\nIf you continue with the CTR entry, please review and edit the CTR as appropriate.");
		return message.toString();
	}

	public static boolean isAuthenticated(HttpServletRequest request){
		if(request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") == null){
			saveLastHTTPRequest(request);
			return false;
		}
		else{
			request.getSession().setAttribute(Constants.LAST_HTTP_REQUEST_SESSION_KEY, null);
			return true;
		}
	}
	
	public static void saveLastHTTPRequest(HttpServletRequest request){
		if(request.getQueryString() != null){
			request.getSession().setAttribute(Constants.LAST_HTTP_REQUEST_SESSION_KEY, request.getServletPath()+"?"+request.getQueryString());
		}
		else{
			request.getSession().setAttribute(Constants.LAST_HTTP_REQUEST_SESSION_KEY, request.getServletPath());
		}
	}
	
	public static java.util.Date startOfDate(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
		}

	public static java.util.Date endOfDate(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}	
	
	public static java.util.Date prevDay(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static java.util.Date nextDay(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		return cal.getTime();
	}
	
	@SuppressWarnings("rawtypes")
	public static String getIds(List list){
    	String ids = "";
    	StringBuffer buffer =new StringBuffer("");
    	for (Object obj : list){
    		if(obj instanceof TransactionEntity){
    			TransactionEntity te = (TransactionEntity)obj;
    			buffer.append(te.getId()+",");
    		}else if(obj instanceof BeneficiaryEntity){
    			BeneficiaryEntity be = (BeneficiaryEntity)obj;
    			buffer.append(be.getId()+",") ;
    		}else if(obj instanceof ConductorEntity){
    			ConductorEntity ce = (ConductorEntity)obj;
    			buffer.append(ce.getId()+",");
    		} 
    			
    	}
    	if(buffer.length()>0){ 
    		ids = buffer.substring(0, buffer.length()-1);
    	}
    	return ids;
    }    
 
	public static String roundTwoDecimals(Double d) {
		BigDecimal bd = new BigDecimal(d);
		return NumberFormat.getCurrencyInstance().format(bd);
	}

	public static String roundTwoDecimals(String d) {
		try{
			BigDecimal bd = new BigDecimal(Double.parseDouble(d));
			return NumberFormat.getCurrencyInstance().format(bd);
		}catch(Exception ex){
			logger.debug("error :: "+ex);
			return "$0.00";
		}
	}
	
	
	public static List<BaseCustomerEntity> getUniqueCustomers(CtrReportDao ctrReportDao, CTRDao ctrDao, Long ctrId, Boolean isBeneficiary){
		List<BaseCustomerEntity> customers = new ArrayList<BaseCustomerEntity>();
		
		List<PersonInvolvedVO> involvedPersons = ctrReportDao.findInvolvedPersonListByCtrId(ctrId);
		if( !involvedPersons.isEmpty() ){
			for(PersonInvolvedVO person: involvedPersons){
				BaseCustomerEntity customer = null;
				if( (isBeneficiary == null || isBeneficiary) && person.getIsBeneficiary() ){
					customer = ctrDao.findBeneficiaryById(person.getPersonId());
				}else if( (isBeneficiary == null || !isBeneficiary) && !person.getIsBeneficiary() ){
					 customer = ctrDao.findConductorById(person.getPersonId());
				}
				if( customer != null ){
					customer.setPersonInvolvedType(person.getPersonInvolvedType());					
					Boolean multiTransaction = person.getNumberOfTransactions() > 1;					
					customer.setInMultiTransaction(multiTransaction);
					customer.setCashInAmount(person.getCashInTotal());
					customer.setCashOutAmount(person.getCashOutTotal());
					customer.setCashInAccounts(person.getAccountInvolvedCashIn());
					customer.setCashOutAccounts(person.getAccountInvolvedCashOut());
					customer.setAlternateName(person.getAlternateNames());
										
					//for ui grids only
					customer.setName(getQualifiedCustomerName(customer));
					customer.setTinTypeName(TinTypeEnum.getLabelForValue(customer.getTinType()));
					customer.setTinText(getTin(customer));
					customer.setAccountInvolvedAll(person.getAccountInvolvedAll());
					customer.setPrimaryAccountHolder(person.getPrimaryAccountHolder());					
					
					customers.add(customer);
				}
			}
		}
		
		return customers;
	}
	
	public static void initializeBensCondsFromTran(TransactionEntity te){
		
		if(te != null){
			
			if(te.getBeneficiaries() != null && te.getBeneficiaries().size() > 0){
				for(BeneficiaryEntity be : te.getBeneficiaries()){
					be.setTransaction(null);
				}
			}
			if(te.getConductors() != null && te.getConductors().size() > 0){
				for(ConductorEntity ce : te.getConductors()){
					ce.setTransaction(null);
				}
			}
		}
	}
}
