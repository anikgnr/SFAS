<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/account.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="account.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td>
								<spring:message code="account.form.bankName"/> : <input type="text" id="bankName" name="bankName" />
							</td>
							<td>
								<spring:message code="account.form.account"/> : <input type="text" id="bankAccount" name="bankAccount" />
							</td>
							<td>
								<spring:message code="user.form.isActive"/> : 
								<select id="ia" name="ia" style="width: 100px;">
									<option value=""></option>
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
				<div id="admin-account-grid"></div><br/>
				<center>
					<input class="button orange" id="createNew" type="button" value='<spring:message code="account.form.new"/>'/>
				</center>		
			</div>
		</div>
	</div>	  
</body>
</html>