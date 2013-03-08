<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/outlet.js"></script>	
	<div class="category_block block_wrap" style="width: 1000px;padding-top:20px;padding-left: 80px;">
		<h3 class="maintitle"><spring:message code="outlet.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock">
					<form:form method="post" action="./saveOutlet.html">
						<form:hidden path="id"/>
						<table style="border-collapse: separate;border-spacing: 5px;">
							<tr>
								<td width="150px;"><spring:message code="outlet.form.pointName"/> : <span class="required">*</span></td>
								<td width="180px;"><form:input path="pointName" /></td>
								<td class="inputerrormsg">&nbsp;</td>								
							</tr>
							<tr>
								<td><spring:message code="rsm.form.address"/> : <span class="required">*</span></td>
								<td><form:input path="address" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="outlet.form.ownerName"/> : </td>
								<td><form:input path="ownerName" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="outlet.form.ownerNumber"/> : </td>
								<td><form:input path="mobileNumber" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="outlet.form.sellerName"/> : </td>
								<td><form:input path="sellerName" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="outlet.form.sellerNumber"/> : </td>
								<td><form:input path="sellerNumber" /></td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="rsm.form.region"/> : <span class="required">*</span></td>
								<td>
									<form:select id="regionId" path="distributor.tso.asm.rsm.region.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${regions}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="asm.form.area"/> : <span class="required">*</span></td>
								<td>
									<form:select id="areaId" path="distributor.tso.asm.area.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${areas}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="tso.form.territory"/> : <span class="required">*</span></td>
								<td>
									<form:select id="territoryId" path="distributor.tso.territory.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${territories}" />
									</form:select>
								</td>
								<td class="inputerrormsg">&nbsp;</td>
							</tr>
							<tr>
								<td><spring:message code="outlet.form.distributor"/> : <span class="required">*</span></td>
								<td>
									<form:select id="distributorId" path="distributor.id">
									 	<form:option value="" label=""/>
	    								<form:options items="${distributors}" />
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