<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/inventory/stockIn.js"></script>	
	<div class="category_block block_wrap" style="width: 1000px;padding-top:20px;padding-left: 80px;">
		<h3 class="maintitle"><spring:message code="stockin.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock" style="padding-left:160px" >
					<form:form method="post" action="./saveStockIn.html">
						<form:hidden path="id"/>
						<table style="border-collapse: separate;border-spacing: 5px;">
							<tr>
								<td width="150px;"><spring:message code="stockin.form.product"/> : <span class="required">*</span></td>
								<td width="180px;">
									<form:select id="productId" path="product.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${products}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align: top"><spring:message code="stockin.form.manufactureDate"/> : <span class="required">*</span></td>
								<td>
									<input size="20" type="text" id="manufactureDate" name="manufactureDate" value='<fmt:formatDate value="${command.manufactureDate}" pattern="dd/MM/yyyy" />'/><br/>									
									<span style="color: gray;padding-left: 35px;">( DD/MM/YYYY )</span>									
								</td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr>
								<td style="text-align: top"><spring:message code="stockin.form.expireDate"/> : <span class="required">*</span></td>
								<td>
									<input size="20" type="text" id="expireDate" name="expireDate" value='<fmt:formatDate value="${command.expireDate}" pattern="dd/MM/yyyy" />'/><br/>									
									<span style="color: gray;padding-left: 35px;">( DD/MM/YYYY )</span>									
								</td>																
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="stockin.form.quantity"/> : <span class="required">*</span></td>
								<td><form:input path="quantity" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="product.form.description"/> : </td>
								<td><form:textarea path="comment" rows="5" cols="20" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr><td>&nbsp;</td></tr>													 
							<tr>
								<td>&nbsp;</td>
								<td style="text-align: right;">
									<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>' onclick="clearErrors();"/>&nbsp;<input class="button orange" id="saveBtn" name="saveBtn" type="submit" value='<spring:message code="save.button.title"/>'/>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>	  
</body>
</html>