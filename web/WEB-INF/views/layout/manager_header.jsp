<%@ page import="java.security.Principal,com.codeyard.sfas.util.Utils,com.codeyard.sfas.entity.ManagerType"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<%	
	final String contextPath = request.getContextPath();
	final Principal loggedUser = request.getUserPrincipal();
	pageContext.setAttribute("accountType", ManagerType.ACCOUNT.getValue());
	pageContext.setAttribute("postType", Utils.getLoggedSysMgrPost());
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
		<div class="right" style="padding: 25px 0;font-size: 14px;color: white;font-style: italic;">
			<spring:message code="layout.module.manager"/>
		</div>
		<div style="color: white;font-size: 16px;font-style: italic;padding-top: 20px;">
			Sales Forced Automation System
		</div>		 
	</div>
</div>
<div id="primary_nav" class="clearfix">
				<div class="main_width">
					<ul class="ipsList_inline" id="community_app_menu">
						<c:if test="${accountType == postType}">					
							<li id="nav_menu_1" class="left  ">
								<a id="menu1" href="<%= contextPath %>/manager/pendingDepoDepositList.html" title="">Pending DEPO Deposits</a>
							</li>			
						</c:if>																											
						<li id="nav_menu_2" class="left  ">
							<a id="menu2" href="<%= contextPath %>/manager/depoOrderList.html" title="">Pending DEPO Orders</a>
						</li>
						<c:if test="${accountType == postType}">					
							<li id="nav_menu_1" class="left  ">
								<a id="menu1" href="<%= contextPath %>/manager/pendingDistributorDepositList.html" title="">Pending Distributor Deposits</a>
							</li>			
						</c:if>																											
						<li id="nav_menu_2" class="left  ">
							<a id="menu2" href="<%= contextPath %>/manager/distributorOrderList.html" title="">Pending Distributor Orders</a>
						</li>
					</ul>
				</div>
 </div>