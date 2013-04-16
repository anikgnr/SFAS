 <%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/distributor.js"></script>	
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="distributor.plan.sheet.title"/> (${command.planMonth} / ${command.planYear}) of "${command.distributor.fullName}"</h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
					<form:form method="post" action="./saveDistributorPlan.html">
						<form:hidden path="id"/>				
						<form:hidden path="distributor.id"/>
						<form:hidden path="planMonth"/>				
						<form:hidden path="planYear"/>												
						<table class="orderTable" border="1">
							<tr style="height: 45px;">
								<th style="width:100px;">Serial</th>
								<th>Product</th>
								<th style="width:300px;">Quantity</th>								
							</tr>
							<c:forEach items="${command.planLiList}" var="planLi" varStatus="idx">
								<form:hidden path="planLiList[${idx.index}].id"/>
								<form:hidden path="planLiList[${idx.index}].product.id"/>								
								<form:hidden path="planLiList[${idx.index}].plan.id"/>
								<form:hidden path="planLiList[${idx.index}].used"/>
								<tr style="height: 45px;">
									<td>${idx.index+1}</td>
									<td>${planLi.product.fullName}</td>
									<td><form:input id="qty-${idx.index}" path="planLiList[${idx.index}].quantity"  cssStyle="width: 140px;text-align: center;"/></td>								
								</tr>
							</c:forEach>
						</table>
						<br/><br/><br/>
						<center>
							<input class="button orange" id="backToDistributor" type="button" value='<spring:message code="operator.back.distributor"/>'/>
							&nbsp;
							<input class="button orange" id="saveRateBtn" name="saveRateBtn" type="submit" value='<spring:message code="save.button.title"/>'/>
						</center>
					</form:form>				
			</div>
		</div>
	</div>	  
</body>
</html>