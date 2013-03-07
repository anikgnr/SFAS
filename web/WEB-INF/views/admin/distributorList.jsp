<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/distributor.js"></script>
	<div class="category_block block_wrap" style="width: 1000px;padding-top:20px;padding-left: 80px;">
		<h3 class="maintitle"><spring:message code="distributor.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="distributor.form.pointName"/> : <input type="text" id="pn" name="pn" />
							</td>
							<td>
								<spring:message code="rsm.form.address"/> : <input type="text" id="ad" name="ad" />	
							</td>
							<td>
								<spring:message code="distributor.form.contactNumber"/> : <input type="text" id="mn" name="mn" />	
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<spring:message code="asm.form.rsm"/> : 
								<select id="tars" name="tars">
									<option value=""></option>
									<c:forEach items="${rsms}" var="rsm">
										<option value="${rsm.id}">${rsm.firstName} ${rsm.lastName}-(${rsm.region.name})</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="tso.form.asm"/> : 
								<select id="tas" name="tas">
									<option value=""></option>
									<c:forEach items="${asms}" var="asm">
										<option value="${asm.id}">${asm.firstName} ${asm.lastName}-(${asm.area.name})</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="distributor.form.tso"/> : 
								<select id="ts" name="ts">
									<option value=""></option>
									<c:forEach items="${tsos}" var="tso">
										<option value="${tso.id}">${tso.firstName} ${tso.lastName}-(${tso.territory.name})</option>
									</c:forEach>
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
				<div id="admin-distributor-grid"></div><br/>
				<center>
					<input class="button orange" id="createNew" type="button" value='<spring:message code="distributor.form.new"/>'/>
				</center>		
			</div>
		</div>
	</div>	  
</body>
</html>