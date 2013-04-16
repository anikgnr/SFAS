<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.codeyard.sfas.entity.Role"%>
<%	
	final String contextPath = request.getContextPath();
	pageContext.setAttribute("managerRole", Role.MANAGER.getValue());
%>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/user.js"></script>
	<input type="hidden" id="managerRole" value="${managerRole}"/>	
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="user.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock" style="padding-left:160px" >
					<form:form method="post" action="./saveUser.html">
						<form:hidden path="id"/>						
						<form:hidden path="sameUserName"/>
						<table style="border-collapse: separate;border-spacing: 5px;">
							<tr>
								<td width="150px;"><spring:message code="user.form.firstName"/> : <span class="required">*</span></td>
								<td width="180px;"><form:input path="firstName" /></td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr>
								<td><spring:message code="user.form.lastName"/> : <span class="required">*</span></td>
								<td><form:input path="lastName" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="user.form.userName"/> : <span class="required">*</span></td>
								<td><form:input path="userName" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="user.form.password"/> : <span class="required">*</span></td>
								<td><form:password path="password" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="user.form.confirmPassword"/> : </td>
								<td><form:password path="confirmPassword" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="user.form.role"/> : <span class="required">*</span></td>
								<td>
									<form:select path="role">
									 	<form:option value="" label=""/>
	    								<form:options items="${roles}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr id="deptBlock" <c:if test="${managerRole != command.role}">style="display:none;"</c:if>>
									<td><spring:message code="user.form.post"/> : <span class="required">*</span></td>
									<td>
										<form:select path="department">
										 	<form:option value="" label=""/>
		    								<form:options items="${types}" />
										</form:select>
									</td>
									<td class="inputerrormsg">&nbsp;</td>
							</tr>							
							<tr>
								<td><spring:message code="user.form.cellNumber"/> : <span class="required">*</span></td>
								<td><form:input path="mobileNumber" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="user.form.email"/> : <span class="required">*</span></td>
								<td><form:input path="email" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
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