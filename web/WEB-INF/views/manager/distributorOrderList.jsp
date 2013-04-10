<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/manager/distributor.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle" ><spring:message code="distributor.pending.order.list.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="outlet.form.distributor"/> : 
								<select id="distributorId" name="distributorId">
									<option value=""></option>
									<c:forEach items="${distributors}" var="distributor">
										<option value="${distributor.id}">${distributor.pointName}</option>
									</c:forEach>
								</select>	
							</td>	
							<td>
								<spring:message code="asm.form.rsm"/> : 
								<select id="distributorRsmId" name="distributorRsmId">
									<option value=""></option>
									<c:forEach items="${rsms}" var="rsm">
										<option value="${rsm.id}">${rsm.nameWithRegion}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="depo.order.form.amount"/> : <input type="text" id="orderAmount" name="orderAmount" style="width:130px;"/> Tk	
							</td>																				
						</tr>					
						<tr><td>&nbsp;</td></tr>	
						<tr>
							<td>
								<spring:message code="depo.order.form.dateFrom"/> : <input type="text" id="orderFromDate" name="orderFromDate" />	
							</td>
							<td>
								<spring:message code="depo.order.form.dateTo"/> : <input type="text" id="orderToDate" name="orderToDate" />	
							</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="orderSearchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>																			
						</tr>												
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-order-grid" style="padding-left: 25px;"></div><br/>									
			</div>
		</div>
	</div>	  
</body>
</html>