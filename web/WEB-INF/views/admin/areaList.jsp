<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/area.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="area.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock" style="padding-left: 35px;">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="area.form.name"/> : <input type="text" id="nm" name="nm" />
							</td>
							<td>
								<spring:message code="rsm.form.region"/> : 
								<select id="rg" name="rg">
									<option value=""></option>
									<c:forEach items="${regions}" var="region">
										<option value="${region.id}">${region.name}</option>
									</c:forEach>
								</select>	
							</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="searchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-area-grid" style="padding-left: 30px;"></div><br/>
				<center>
					<input class="button orange" id="createNew" type="button" value='<spring:message code="area.form.new"/>'/>
				</center>		
			</div>
		</div>
	</div>	  
</body>
</html>