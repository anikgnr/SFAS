<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.codeyard.sfas.util.Utils"%>

<%
	final String contextPath = request.getContextPath();	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="<%= contextPath %>/resources/styles/base.css" rel="stylesheet" />   
	<link type="text/css" href="<%= contextPath %>/resources/styles/ipb_styles.css" rel="stylesheet" />	
	<title>Access Denied</title>
</head>
<body id="ipboard_body">

		  <div id="ipbwrapper">
				<div id="header_bar" class="clearfix">
			<div class="main_width">
				<div id="user_navigation" class="not_logged_in">
					<div style="float:left;">
						<img src="<%= contextPath %>/resources/images/smalllogo.png"/>
						<span style="color: green;font-size: 13px; padding-left:5px;">Mabco Group : Serving Fibre, Food, Feed &amp; Fertilizer</span>
					</div>								
					<ul class="ipsList_inline right">				
						<li>
							<a href="<%= contextPath %>/login.html" title="Sign In" class="register_link">Sign In</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div id="branding">
			<div class="main_width">
				<div id="logo" style="color: white;font-size: 20px;font-family: cursive;font-style: italic;">
					<br/>		
					Sales Forced Automation System
				</div>	
			</div>
		</div>
		<div id="primary_nav" class="clearfix"><div class="main_width" style="padding-bottom: 10px;">&nbsp;</div></div>
	
		<div id="content" class="clearfix" style="min-height: 600px;">
			<br/><br/>
	    	<div style="padding: 160px 0 0 0; text-align: center;">
				<div style="font-size: 16px;color: #2c5687;font-weight: bold;">Access Denied for your request</div>
				<div style="color: #da7c0c; padding-top:5px;font-weight: bold;">Please try again with proper privilege <a href="<%=contextPath%>/login.html">click here</a> </div>
			</div>	
	 	</div>
	    <div id="primary_nav" class="clearfix">
		<div class="main_width" style="padding-bottom: 10px;">
			<div id="copyright" style="text-align: center;"><spring:message code="layout.footer.label"/></div>
		</div>
</div>
  </div> 
</body>
</html>