<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ page import="com.codeyard.sfas.util.Utils"%>

<%
	final String contextPath = request.getContextPath();
	pageContext.setAttribute("SFASErrorMessage", Utils.getErrorMessage(request));
	pageContext.setAttribute("SFASSuccessMessage", Utils.getSuccessMessage(request));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- <title><tiles:insertAttribute name="title" ignore="true"/></title> --%>
	<link type="text/css" href="<%= contextPath %>/resources/ext4/resources/css/ext-all.css" rel="stylesheet" />
	<link type="text/css" href="<%= contextPath %>/resources/styles/base.css" rel="stylesheet" />   
	<link type="text/css" href="<%= contextPath %>/resources/styles/cupertino/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="<%= contextPath %>/resources/ext4/ext-all.js"></script>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/jquery-ui-1.8.18.custom.min.js"></script>
	<script type="text/javascript" src="<%= contextPath %>/resources/js/common.js"></script>
	<link type="text/css" href="<%= contextPath %>/resources/styles/ipb_styles.css" rel="stylesheet" />	
	<link rel="icon" type="image/jpeg" href="<%= contextPath %>/resources/images/favicon.jpg">
	<title>SFAS</title>
</head>
<body id="ipboard_body">

  <div id="ipbwrapper">
		<tiles:insertAttribute name="header" />
			
		<c:if test="${SFASErrorMessage != ''}">
			<div id="errorMsgDiv">${SFASErrorMessage}</div>
		</c:if>
		<c:if test="${SFASSuccessMessage != ''}">
			<div id="successMsgDiv">${SFASSuccessMessage}</div>
		</c:if>
		<div id="content" class="clearfix" style="min-height: 600px;">
			<br/><br/>
	    	<tiles:insertAttribute name="body" />
	 	</div>
	    <tiles:insertAttribute name="footer" />
  </div> 
</body>
</html>