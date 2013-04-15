 <%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/product.js"></script>	
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="product.form.rate.title"/> "${command.fullName}"</h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
					<form:form method="post" action="./saveRegionalProductRate.html">
						<form:hidden path="id"/>						
						<table class="orderTable" border="1">
							<tr style="height: 45px;">
								<th>Region</th>
								<th style="width:200px;">Sell Rate</th>
								<th style="width:200px;">Profit Margin</th>
							</tr>
							<c:forEach items="${command.regionalRateList}" var="rate" varStatus="idx">
								<form:hidden path="regionalRateList[${idx.index}].id"/>
								<form:hidden path="regionalRateList[${idx.index}].product.id"/>								
								<form:hidden path="regionalRateList[${idx.index}].region.id"/>
								<tr style="height: 45px;">
									<td>${rate.region.name}</td>
									<td><form:input id="rate-${idx.index}" path="regionalRateList[${idx.index}].rate"  cssStyle="width: 80px;text-align: right;"/> &nbsp;&nbsp;Tk</td>
									<td><form:input id="profit-${idx.index}" path="regionalRateList[${idx.index}].profitMargin"  cssStyle="width: 80px;text-align: right;"/> &nbsp;&nbsp;%</td>
								</tr>
							</c:forEach>
						</table>
						<br/><br/><br/>
						<center>
							<input class="button orange" id="backToProduct" name="backToProduct" type="button" value='<spring:message code="product.back.to.list"/>'/>&nbsp;
							<input class="button orange" id="saveRateBtn" name="saveRateBtn" type="submit" value='<spring:message code="save.button.title"/>'/>
						</center>
					</form:form>				
			</div>
		</div>
	</div>	  
</body>
</html>