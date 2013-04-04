<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/depoOrder.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle" ><spring:message code="depo.order.list.title"/> of ${depoName}</h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<input type="hidden" id="depoId" name="depoId" value="${depoId}"/>
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="depo.order.form.dateFrom"/> : <input type="text" id="orderFromDate" name="orderFromDate" />	
							</td>
							<td>
								<spring:message code="depo.order.form.dateTo"/> : <input type="text" id="orderToDate" name="orderToDate" />	
							</td>
							<td>
								<spring:message code="depo.order.form.amount"/> : <input type="text" id="orderAmount" name="orderAmount" style="width:130px;"/> Tk	
							</td>							
						</tr>					
						<tr><td>&nbsp;</td></tr>	
						<tr>
							<td>
								<spring:message code="depo.deposit.form.isApproved"/> : 
								<select id="mdApproved" name="mdApproved">
									<option value=""></option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>	
							</td>
							<td>
								<spring:message code="depo.order.form.isDelivered"/> : 
								<select id="delivered" name="delivered">
									<option value=""></option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>	
							</td>		
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="orderSearchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>				
						</tr>						
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-order-grid" style="padding-left: 35px;"></div><br/>						
				<center>
					<input class="button orange" id="backToDepo" type="button" value='<spring:message code="operator.back.depo"/>'/>&nbsp;
					<input class="button orange" id="createDepoOrder" type="button" value='<spring:message code="depo.order.form.new"/>'/>
				</center>			
			</div>
		</div>
	</div>	  
</body>
</html>