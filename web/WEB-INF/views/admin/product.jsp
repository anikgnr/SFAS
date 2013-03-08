<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/product.js"></script>	
	<div class="category_block block_wrap" style="width: 1000px;padding-top:20px;padding-left: 80px;">
		<h3 class="maintitle"><spring:message code="product.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock">
					<form:form method="post" action="./saveProduct.html">
						<form:hidden path="id"/>
						<table style="border-collapse: separate;border-spacing: 5px;">
							<tr>
								<td width="150px;"><spring:message code="product.form.productName"/> : <span class="required">*</span></td>
								<td width="180px;"><form:input path="productName" /></td>
								<td class="inputerrormsg">&nbsp;</td>
								
								<td><spring:message code="product.form.description"/> : </td>
								<td><form:input path="productDescription" /></td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr>
								<td><spring:message code="product.form.bagSize"/> : <span class="required">*</span></td>
								<td><form:input path="bagSize" style="width: 100px"/>&nbsp;Pcs</td>
								<td class="inputerrormsg">&nbsp;</td>
							
								<td><spring:message code="product.form.rate"/> : <span class="required">*</span></td>
								<td><form:input path="rate"  style="width: 100px"/>&nbsp;Tk</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="product.form.profitMargin"/> : <span class="required">*</span></td>
								<td><form:input path="profitMargin" style="width: 100px"/>&nbsp;%</td>
								<td class="inputerrormsg">&nbsp;</td>
							
								<td><spring:message code="user.form.isActive"/> : </td>
								<td>
									<form:select path="active">
									 	<form:option value="true" label="True"/>    								
									 	<form:option value="false" label="False"/>
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td>&nbsp;</td></tr>																		 
							<tr>								
								<td colspan="5" style="text-align: center;">
									<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>' onclick="clearErrors();"/>&nbsp;<input class="button orange" id="saveBtn" name="saveBtn" type="submit" value='<spring:message code="save.button.title"/>'/>
								</td>								
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>	  
</body>
</html>