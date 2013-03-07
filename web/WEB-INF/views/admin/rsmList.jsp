<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/rsm.js"></script>
	<div class="category_block block_wrap" style="width: 1000px;padding-top:20px;padding-left: 80px;">
		<h3 class="maintitle"><spring:message code="rsm.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="user.form.firstName"/> : <input type="text" id="fn" name="fn" />
							</td>
							<td>
								<spring:message code="user.form.lastName"/> : <input type="text" id="ln" name="ln" />	
							</td>
							<td>
								<spring:message code="user.form.cellNumber"/> : <input type="text" id="mn" name="mn" />	
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<spring:message code="rsm.form.address"/> : <input type="text" id="ad" name="ad" />
							</td>
							<td>
								<spring:message code="rsm.form.region"/> : 
								<select id="rg" name="rg">
									<option value="">Please Select One</option>
									<c:forEach items="${regions}" var="region">
										<option value="${region.id}">${region.name}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="user.form.isActive"/> : 
								<select id="ia" name="ia">
									<option value="">Please Select One</option>
									<option value="1">True</option>
									<option value="0">False</option>
								</select>	
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="2">&nbsp;</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="searchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-rsm-grid"></div><br/>
				<center>
					<input class="button orange" id="createNew" type="button" value='<spring:message code="rsm.form.new"/>'/>
				</center>		
			</div>
		</div>
	</div>	  
</body>
</html>