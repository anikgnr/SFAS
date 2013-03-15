<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/inventory/stockIn.js"></script>
	<div class="category_block block_wrap" style="width: 1000px;padding-top:20px;padding-left: 80px;">
		<h3 class="maintitle"><spring:message code="stockinlist.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
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
								<spring:message code="stockinlist.form.entryBy"/> : 
								<select id="createdBy" name="createdBy">
									<option value=""></option>
									<c:forEach items="${users}" var="user">
										<option value="${user.userName}">${user.userName}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="stockin.form.quantity"/> : <input type="text" id="quantity" name="quantity" />	
							</td>
						</tr>						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<spring:message code="stockin.form.stockInFrom"/> : <input type="text" id="stockInFromDate" name="stockInFromDate" />	
							</td>
							<td>
								<spring:message code="stockin.form.stockInTo"/> : <input type="text" id="stockInToDate" name="stockInToDate" />	
							</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="searchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-stockin-grid"></div><br/>						
			</div>
		</div>
	</div>	  
</body>
</html>