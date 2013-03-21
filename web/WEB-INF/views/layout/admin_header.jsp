<%@ page import="java.security.Principal"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%	
	final String contextPath = request.getContextPath();
	final Principal loggedUser = request.getUserPrincipal(); 
%>
<div id="header_bar" class="clearfix">
	<div class="main_width">
		<div id="user_navigation" class="not_logged_in">
			<div style="float:left;">
				<img src="<%= contextPath %>/resources/images/smalllogo.png"/>
				<span style="color: green;font-size: 13px; padding-left:5px;">Mabco Group : Serving Fibre, Food, Feed &amp; Fertilizer</span>
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
		<div class="right" style="padding: 25px 0;font-size: 14px;color: white;font-style: italic;">
			<spring:message code="layout.module.admin"/>
		</div>
		<div style="color: white;font-size: 20px;font-family: cursive;font-style: italic;padding-top: 17px;">					
			Sales Forced Automation System
		</div>				
	</div>
</div>
<div id="primary_nav" class="clearfix">
				<div class="main_width">
					<ul class="ipsList_inline" id="community_app_menu">					
						<li id="nav_menu_1" class="left  ">
							<a id="menu1" href="<%= contextPath %>/admin/userList.html" title="">User Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu2" href="<%= contextPath %>/admin/rsmList.html" title="">RSM Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu3" href="<%= contextPath %>/admin/asmList.html" title="">ASM Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu4" href="<%= contextPath %>/admin/tsoList.html" title="">TSO Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu5" href="<%= contextPath %>/admin/distributorList.html" title="">Distributor Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu6" href="<%= contextPath %>/admin/srList.html" title="">SR Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu7" href="<%= contextPath %>/admin/outletList.html" title="">Outlet Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu8" href="<%= contextPath %>/admin/productList.html" title="">Product Module</a>
						</li>												
						<li id="nav_menu_1" class="left  ">
							<a id="menu9" href="<%= contextPath %>/admin/depoList.html" title="">DEPO Module</a>
						</li>												
					</ul>
				</div>
 </div>