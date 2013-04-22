<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/outlet.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 50px;">
		<h3 class="maintitle" style="width: 1000px;"><spring:message code="outlet.form.title"/></h3>
		<div class="ipsBox table_wrap" style="width: 1000px;">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td><spring:message code="outlet.form.pointName"/> : </td>
							<td><input type="text" id="pn" name="pn" /></td>
							<td>
								<spring:message code="rsm.form.address"/> : 
							</td>
							<td>
								<input type="text" id="ad" name="ad" />	
							</td>
							<td>
								<spring:message code="asm.form.rsm"/> : 
							</td>
							<td>								 
								<select id="dtars" name="dtars">
									<option value=""></option>
									<c:forEach items="${rsms}" var="rsm">
										<option value="${rsm.id}">${rsm.firstName} ${rsm.lastName}-(${rsm.region.name})</option>
									</c:forEach>
								</select>	
							</td>							
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							
							<td>
								<spring:message code="tso.form.asm"/> : 
							</td>
							<td> 
								<select id="dtas" name="dtas">
									<option value=""></option>
									<c:forEach items="${asms}" var="asm">
										<option value="${asm.id}">${asm.firstName} ${asm.lastName}-(${asm.area.name})</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="distributor.form.tso"/> : 
							</td>
							<td> 
								<select id="dts" name="dts">
									<option value=""></option>
									<c:forEach items="${tsos}" var="tso">
										<option value="${tso.id}">${tso.firstName} ${tso.lastName}-(${tso.territory.name})</option>
									</c:forEach>
								</select>	
							</td>														
							<td>
								<spring:message code="outlet.order.list.sr"/> : 
							</td>
							<td> 
								<select id="dsr" name="dsr">
									<option value=""></option>
									<c:forEach items="${srs}" var="sr">
										<option value="${sr.id}">${sr.firstName} ${sr.lastName}-(${sr.tso.territory.name})</option>
									</c:forEach>
								</select>	
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td>
								<spring:message code="distributor.form.depo"/> : 
							</td>
							<td> 
								<select id="drdp" name="drdp">
									<option value=""></option>
									<c:forEach items="${depos}" var="depo">
										<option value="${depo.id}">${depo.name}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="outlet.form.distributor"/> : 
							</td>
							<td> 
								<select id="dr" name="dr">
									<option value=""></option>
									<c:forEach items="${distributors}" var="distributor">
										<option value="${distributor.id}">${distributor.pointName}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="outlet.form.route"/> : 
							</td>
							<td>								 
								<select id="rut" name="rut">
									<option value=""></option>
									<c:forEach items="${routes}" var="route">
										<option value="${route.id}">${route.name}</option>
									</c:forEach>
								</select>	
							</td>							
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="5">&nbsp;</td>
							<td><input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="searchBtn" type="button" value='<spring:message code="search.button.title"/>'/></td>
						</tr>						
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-outlet-grid"></div><br/>						
			</div>
		</div>
	</div>	  
</body>
</html>