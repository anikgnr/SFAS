<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/route.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="route.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="route.form.name"/> : <input type="text" id="nm" name="nm" />
							</td>
							<td>
								<spring:message code="rsm.form.region"/> : 
								<select id="arrg" name="arrg">
									<option value=""></option>
									<c:forEach items="${regions}" var="region">
										<option value="${region.id}">${region.name}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="asm.form.area"/> : 
								<select id="ar" name="ar">
									<option value=""></option>
									<c:forEach items="${areas}" var="area">
										<option value="${area.id}">${area.name}</option>
									</c:forEach>
								</select>	
							</td>							
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<spring:message code="tso.form.territory"/> : 
								<select id="tr" name="tr">
									<option value=""></option>
									<c:forEach items="${territories}" var="territory">
										<option value="${territory.id}">${territory.name}</option>
									</c:forEach>
								</select>	
							</td>
							<td>&nbsp;</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="searchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-route-grid"></div><br/>
				<center>
					<input class="button orange" id="createNew" type="button" value='<spring:message code="route.form.new"/>'/>
				</center>		
			</div>
		</div>
	</div>	  
</body>
</html>