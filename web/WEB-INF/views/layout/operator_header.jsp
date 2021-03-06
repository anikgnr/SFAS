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
			<div style="float:left;">
				<img src="<%= contextPath %>/resources/images/smalllogo.png"/>
				<span style="color: green;font-size: 13px; padding-left:5px;">Mabco Group : Serving Fibre, Food, Feed &amp; Fertilizer Since 1958</span>
			</div>						
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
		<img style="height: 63px; float: right;" src="<%= contextPath %>/resources/images/sub_logo.png"/>
		<div style="color: white;font-size: 16px;font-style: italic;padding-top: 20px;">					
			FMCG Automation System ( <spring:message code="layout.module.operator"/> )
		</div>					 
	</div>
</div>
<div id="primary_nav" class="clearfix">
	<div class="main_width">
		<ul class="ipsList_inline" id="community_app_menu">					
			<li id="nav_menu_1" class="left  ">
				<a id="menu1" href="<%= contextPath %>/operator/depoList.html" title="">DEPO Module</a>
			</li>												
			<li id="nav_menu_2" class="left  ">
				<a id="menu2" href="<%= contextPath %>/operator/distributorList.html" title="">Distributor Module</a>
			</li>
			<li id="nav_menu_3" class="left  ">
				<a id="menu3" href="<%= contextPath %>/operator/outletList.html" title="">Outlet Module</a>
			</li>
		</ul>
	</div>
 </div>