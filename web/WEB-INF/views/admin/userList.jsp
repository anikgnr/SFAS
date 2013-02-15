<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<body>	
	<div class="category_block block_wrap" style="width: 1000px;padding-top:20px;padding-left: 80px;">
		<h3 class="maintitle"><spring:message code="user.form.title"/></h3>
		<div class="ipsBox table_wrap" style="height:250px;">
			<div class="ipsBox_container" style="height:230px;">				
				<div id="admin-user-grid"></div>			
			</div>
		</div>
	</div>	  
</body>
</html>