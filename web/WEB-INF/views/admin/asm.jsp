<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/asm.js"></script>	
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="rsm.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock">
					<form:form method="post" action="./saveASM.html">
						<form:hidden path="id"/>
						<table style="border-collapse: separate;border-spacing: 5px;">
							<tr>
								<td width="150px;"><spring:message code="user.form.firstName"/> : <span class="required">*</span></td>
								<td width="180px;"><form:input path="firstName" /></td>
								<td class="inputerrormsg">&nbsp;</td>								
							
								<td><spring:message code="user.form.lastName"/> : </td>
								<td><form:input path="lastName" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="user.form.cellNumber"/> : <span class="required">*</span></td>
								<td><form:input path="mobileNumber" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							
								<td><spring:message code="rsm.form.address"/> : </td>
								<td><form:input path="address" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="asm.form.rsm"/> : <span class="required">*</span></td>
								<td>
									<form:select id="rsmId" path="rsm.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${rsms}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							
								<td><spring:message code="asm.form.area"/> : <span class="required">*</span></td>
								<td>
									<form:select id="areaId" path="area.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${areas}" />
									</form:select>
								</td>
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

								<td><spring:message code="rsm.form.gross"/> : <span class="required">*</span></td>
								<td><form:input path="monthlyGross" cssStyle="width: 140px;"/>&nbsp;Tk</td>
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