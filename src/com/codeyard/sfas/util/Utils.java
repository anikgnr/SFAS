package com.codeyard.sfas.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);

	
	public static boolean isNullOrEmpty(String content){
		if(content != null && !content.isEmpty())
			return false;
		else
			return true;
	}
	
	public static String getMessageBundlePropertyValue(String key){
		try{
			Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(Constants.MESSAGE_FILE_PATH));			
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
	
}
