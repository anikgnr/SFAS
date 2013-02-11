<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
	<title><spring:message code="layout.title.head"/> : <spring:message code="login.title"/> </title>	
</head>

<body>
  
<div id="formblock" style="height: 400px; padding-top: 50px;">
	<input id="close" type="hidden" value="${close}"/>	
    <center>
    <form name="f" action="j_spring_security_check" method="POST">
      <table cellspacing="10" cellpadding="0">
        <tr><td><spring:message code="login.label.username"/></td><td><input type='text' size='20' name='j_username' value='<c:if test="${not empty error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td><spring:message code="login.label.password"/></td><td><input type='password' size='20' name='j_password'></td></tr><tr><td>&nbsp;</td></tr>        
        <tr><td colspan='2' align='right'><input class="button" name="reset" type="reset" value='<spring:message code="login.button.reset"/>'/>&nbsp;<input class="button" name="submit" type="submit" value='<spring:message code="login.button.submit"/>'/></td></tr>
      </table>

    </form>
	<c:if test="${not empty error}">
      <font color="red">
        ${error}
      </font>
    </c:if>

	</center>

</div>
  
</body>
</html>
