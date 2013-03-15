<%@ page import="java.security.Principal,com.codeyard.sfas.util.Utils,com.codeyard.sfas.entity.Role"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<%	
	final String contextPath = request.getContextPath();
	final Principal loggedUser = request.getUserPrincipal(); 
	pageContext.setAttribute("isInventoryAdmin", Utils.isInRole(Role.INVENTORY_ADMIN.getValue()));
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
			<spring:message code="layout.module.inventory"/>
		</div>
		<div id="logo">
			<a href="#" title="Go to Home Page" rel="home" accesskey="1"><img src="<%=contextPath%>/resources/images/logo.png" alt="Logo"></a>
		</div>		 
	</div>
</div>
<div id="primary_nav" class="clearfix">
				<div class="main_width">
					<ul class="ipsList_inline" id="community_app_menu">					
						<li id="nav_menu_1" class="left  ">
							<a id="menu1" href="<%= contextPath %>/inventory/stockList.html" title="">Current Stock List</a>
						</li>
						<li id="nav_menu_2" class="left  ">
							<a id="menu2" href="<%= contextPath %>/inventory/damageList.html" title="">Damage Stock List</a>
						</li>
						<li id="nav_menu_3" class="left  ">
							<a id="menu3" href="<%= contextPath %>/inventory/stockIn.html" title="">Stock In Entry</a>
						</li>			
						<c:if test="${isInventoryAdmin == true}">																				
						<li id="nav_menu_4" class="left  ">
							<a id="menu4" href="<%= contextPath %>/inventory/stockInList.html" title="">Stock In List</a>
						</li>
						</c:if>
					</ul>
				</div>
 </div>