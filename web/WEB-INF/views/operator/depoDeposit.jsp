<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.codeyard.sfas.entity.Role"%>
<%	
	final String contextPath = request.getContextPath();
	pageContext.setAttribute("managerRole", Role.MANAGER.getValue());
%>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/depo.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="dep.deposit.form.title"/> of ${command.depo.fullName}</h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock" style="padding-left:160px" >
					<form:form method="post" action="./saveDepoDeposit.html">
						<form:hidden path="id"/>						
						<form:hidden id="depoId" path="depo.id"/>
						<table style="border-collapse: separate;border-spacing: 5px;">
							<tr>
								<td><spring:message code="depo.deposit.form.bankAccount"/> : <span class="required">*</span></td>
								<td>
									<form:select id="accountId" path="account.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${accounts}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td width="150px;"><spring:message code="depo.deposit.form.amount"/> : <span class="required">*</span></td>
								<td width="180px;"><form:input path="depositAmount" /></td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr>
								<td style="text-align: top"><spring:message code="depo.deposit.form.date"/> : <span class="required">*</span></td>
								<td>
									<input size="20" type="text" id="depositDate" name="depositDate" value='<fmt:formatDate value="${command.depositDate}" pattern="MM/dd/yyyy" />'/><br/>									
									<span style="color: gray;padding-left: 35px;">( MM/DD/YYYY )</span>									
								</td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr><td>&nbsp;</td></tr>													 
							<tr>								
								<td colspan="2" style="text-align: right;">
									<input class="button orange" id="depositBackBtn" name="depositBackBtn" type="button" value='<spring:message code="back.button.title"/>'/>&nbsp;<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>' onclick="clearErrors();"/>&nbsp;<input class="button orange" id="saveDepositBtn" name="saveDepositBtn" type="submit" value='<spring:message code="save.button.title"/>'/>
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