<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/distributor.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="damage.stock.form.title"/> of ${distributorName}</h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock" style="padding-left: 62px;">
					<form id="searchForm">
					<input type="hidden" id="distributorId" name="distributorId" value="${distributorId}"/>
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="stockin.form.product"/> : 
								<select id="productId" name="productId">
									<option value=""></option>
									<c:forEach items="${products}" var="product">
										<option value="${product.id}">${product.fullName}</option>
									</c:forEach>
								</select>	
							</td>							
							<td>
								<spring:message code="stock.form.damageType"/> : 
								<select id="damageType" name="damageType">
									<option value=""></option>
									<c:forEach items="${damageTypes}" var="damageType">
										<option value="${damageType.value}">${damageType.label}</option>
									</c:forEach>
								</select>	
							</td>							
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="damageSearchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>					
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-damage-grid" style="padding-left: 65px;"></div><br/>			
				<center>
					<input class="button orange" id="backToDistributor" type="button" value='<spring:message code="operator.back.distributor"/>'/>
				</center>				
			</div>
		</div>
	</div>	  
</body>
</html>