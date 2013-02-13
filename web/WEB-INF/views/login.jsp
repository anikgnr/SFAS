<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<body>
	<center>
	<div class="category_block block_wrap" style="width: 650px;padding-top:50px;">
		<h3 class="maintitle"><spring:message code="login.form.title"/></h3>
		<div class="ipsBox table_wrap" style="height:250px;">
			<div class="ipsBox_container" style="height:230px;">				
				<br/><br/><br/>
			    <form name="f" action="j_spring_security_check" method="POST">			    	
			      <table class="ipb_table" cellspacing="10" cellpadding="0" style="text-align: center;">
			        <tr>
			        	<td style="text-align: right"><b><spring:message code="login.label.username"/></b></td>
			        	<td style="text-align: left;width: 370px;"><input type='text' size='20' name='j_username' value='<c:if test="${not empty error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td>
			        </tr>
			        <tr>
			        	<td style="text-align: right"><b><spring:message code="login.label.password"/></b></td>
			        	<td style="text-align: left;width: 370px;"><input type='password' size='20' name='j_password'></td>
			        </tr>
			        <tr>
			        	<td style="text-align: right">&nbsp;</td>
			        	<td style="text-align: left;width: 370px;"><input class="button orange" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button orange" name="submit" type="submit" value='<spring:message code="login.button.submit"/>'/></td>			        	
			        </tr>
			      </table>			
			    </form>
				<c:if test="${not empty error}">
				<br>
			      <b><font color="red">
			        ${error}
			      </font></b>
			    </c:if>			
			</div>
		</div>
	</div>		
	</center>
  
</body>
</html>