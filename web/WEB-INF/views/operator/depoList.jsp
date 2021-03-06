<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/depo.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 50px;">
		<h3 class="maintitle" style="width: 1000px;"><spring:message code="depo.form.title"/></h3>
		<div class="ipsBox table_wrap" style="width: 1000px;">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="depo.form.name"/> : <input type="text" id="nm" name="nm" />
							</td>
							<td>
								<spring:message code="rsm.form.region"/> : 
								<select id="rrg" name="rrg">
									<option value=""></option>
									<c:forEach items="${regions}" var="region">
										<option value="${region.id}">${region.name}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="asm.form.rsm"/> : 
								<select id="rs" name="rs">
									<option value=""></option>
									<c:forEach items="${rsms}" var="rsm">
										<option value="${rsm.id}">${rsm.name}</option>
									</c:forEach>
								</select>	
							</td>																					
						</tr>	
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="searchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>					
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-depo-grid"></div><br/>						
			</div>
		</div>
	</div>	  
</body>
</html>