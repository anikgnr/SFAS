<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/manager/deposit.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 50px;">
		<h3 class="maintitle" style="width: 1000px;"><spring:message code="depo.deposit.pending.form.title"/></h3>
		<div class="ipsBox table_wrap" style="width: 1000px;">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="distributor.form.depo"/> : 
								<select id="depoId" name="depoId">
									<option value=""></option>
									<c:forEach items="${depos}" var="depo">
										<option value="${depo.id}">${depo.fullName}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="depo.deposit.form.bankAccount"/> : 
								<select id="accountId" name="accountId">
									<option value=""></option>
									<c:forEach items="${accounts}" var="account">
										<option value="${account.id}">${account.completeName}</option>
									</c:forEach>
								</select>	
							</td>
							<td>
								<spring:message code="depo.deposit.form.amount"/> : <input type="text" id="depositAmount" name="depositAmount" style="width:130px;"/> Tk	
							</td>
						</tr>					
						<tr><td>&nbsp;</td></tr>	
						<tr>
							<td>
								<spring:message code="depo.deposit.form.dateFrom"/> : <input type="text" id="depositFromDate" name="depositFromDate" />	
							</td>
							<td>
								<spring:message code="depo.deposit.form.dateTo"/> : <input type="text" id="depositToDate" name="depositToDate" />	
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td colspan="2">&nbsp;</td>
							<td  style="text-aling: right;">
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="depositSearchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-depo-deposit-grid"></div><br/>						
			</div>
		</div>
	</div>	  
</body>
</html>