<%@ page import="java.security.Principal"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<%	
	final String contextPath = request.getContextPath();
	final Principal loggedUser = request.getUserPrincipal(); 	
%>
<div id="header_bar" class="clearfix">
	<div class="main_width">
		<div id="user_navigation" class="not_logged_in">
			<ul class="ipsList_inline right">			
				<li>
					Welcome <%=loggedUser.getName()%>&nbsp;&nbsp; 
				</li>	
				<li>
					<a href="<%= contextPath %>/logout.html" title="Sign In" class="register_link">Sign Out</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<div id="branding">
	<div class="main_width">
		<div class="right" style="padding: 25px 0;font-size: 14px;color: white;font-style: italic;">
			<spring:message code="layout.module.report"/>
		</div>
		<div id="logo">
			<a href="#" title="Go to Home Page" rel="home" accesskey="1"><img src="<%=contextPath%>/resources/images/logo.png" alt="Logo"></a>
		</div>		 
	</div>
</div>
<div id="primary_nav" class="clearfix">
				<div class="main_width">
					<ul class="ipsList_inline" id="community_app_menu">					
																		
					</ul>
				</div>
 </div>