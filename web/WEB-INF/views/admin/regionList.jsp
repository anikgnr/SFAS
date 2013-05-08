<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/admin/region.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 100px;">
		<h3 class="maintitle"><spring:message code="region.form.title"/></h3>
		<div class="ipsBox table_wrap">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="searchBlock">
					<form id="searchForm" style="padding-left: 170px;">
					<table cellspacing="10" cellpadding="0">
						<tr>
							<td style="width: 320px;">
								<spring:message code="region.form.name"/> :&nbsp;&nbsp; <input type="text" id="nm" name="nm" style="width: 200px;"/>
							</td>
							<td>
								<input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" id="searchBtn" type="button" value='<spring:message code="search.button.title"/>'/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<br/><br/><br/>
				<div id="admin-region-grid" style="padding-left: 130px;"></div><br/>
				<center>
					<input class="button orange" id="createNew" type="button" value='<spring:message code="region.form.new"/>'/>
				</center>		
			</div>
		</div>
	</div>	  
</body>
</html>