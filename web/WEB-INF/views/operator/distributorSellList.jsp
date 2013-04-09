<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/distributor.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="depo.sell.form.title"/> of ${distributorName}</h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock" style="padding-left: 62px;">
					<form id="searchForm">
					<input type="hidden" id="distributorId" name="distributorId" value="${distributorId}"/>
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td style="width: 300px;">
								<spring:message code="product.form.productName"/> : <input type="text" id="productName" name="productName" />
							</td>
							<td style="width: 220px;">
								<spring:message code="product.form.bagSize"/> : <input type="text" id="bagSize" name="bagSize" style="width: 100px"/>&nbsp;pcs
							</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="sellSearchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>						
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-sell-grid" style="padding-left: 100px;"></div><br/>						
				<center>
					<input class="button orange" id="backToDistributor" type="button" value='<spring:message code="operator.back.distributor"/>'/>
				</center>			
			</div>
		</div>
	</div>	  
</body>
</html>