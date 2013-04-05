<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/manager/deposit.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle" ><spring:message code="depo.pending.order.list.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="distributor.form.depo"/> : 
								<select id="depoId" name="depoId">
									<option value=""></option>
									<c:forEach items="${depos}" var="depo">
										<option value="${depo.id}">${depo.name}</option>
									</c:forEach>
								</select>	
							</td>	
							<td>
								<spring:message code="rsm.form.region"/> : 
								<select id="depoRegionId" name="depoRegionId">
									<option value=""></option>
									<c:forEach items="${regions}" var="region">
										<option value="${region.id}">${region.name}</option>
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