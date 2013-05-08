<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/route.js"></script>	
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="route.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock" style="padding-left: 250px !important;">
					<form:form method="post" action="./saveRoute.html">
						<form:hidden path="id"/>
						<table style="border-collapse: separate;border-spacing: 5px;">
							<tr>
								<td width="110px;"><spring:message code="rsm.form.region"/> : <span class="required">*</span></td>
								<td>
									<form:select id="regionId" path="territory.area.region.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${regions}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td width="110px;"><spring:message code="asm.form.area"/> : <span class="required">*</span></td>
								<td>
									<form:select id="areaId" path="territory.area.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${areas}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td width="110px;"><spring:message code="tso.form.territory"/> : <span class="required">*</span></td>
								<td>
									<form:select id="territoryId" path="territory.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${territories}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td width="110px;"><spring:message code="route.form.name"/> : <span class="required">*</span></td>
								<td><form:input path="name" cssStyle="width: 200px;"/></td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr><td>&nbsp;</td></tr>													 
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td colspan="3" style="padding-left: 80px;">
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