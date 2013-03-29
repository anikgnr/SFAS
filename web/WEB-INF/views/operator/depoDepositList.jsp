<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/depo.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 50px;">
		<h3 class="maintitle" style="width: 1000px;"><spring:message code="dep.deposit.form.title"/> of ${depoName}</h3>
		<div class="ipsBox table_wrap" style="width: 1000px;">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<input type="hidden" id="depoId" name="depoId" value="${depoId}"/>
					<table cellspacing="10" cellpadding="0">
						<tr>
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
							<td>
								<spring:message code="depo.deposit.form.isApproved"/> : 
								<select id="accountApproved" name="accountApproved">
									<option value=""></option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>	
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
							<td>
								<spring:message code="depo.deposit.form.approvedBy"/> : 
								<select id="accountApprovedBy" name="accountApprovedBy">
									<option value=""></option>
									<c:forEach items="${users}" var="user">
										<option value="${user.userName}">${user.userName}</option>
									</c:forEach>
								</select>	
							</td>
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
				<div id="admin-deposit-grid"></div><br/>						
				<center>
					<input class="button orange" id="backToDepo" type="button" value='<spring:message code="operator.back.depo"/>'/>&nbsp;
					<input class="button orange" id="createDepoDeposit" type="button" value='<spring:message code="depo.deposit.form.new"/>'/>
				</center>			
			</div>
		</div>
	</div>	  
</body>
</html>