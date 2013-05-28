<%@ page import="java.security.Principal, com.codeyard.sfas.util.Utils, com.codeyard.sfas.entity.Role"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<%	
	final String contextPath = request.getContextPath();
	final Principal loggedUser = request.getUserPrincipal(); 
	pageContext.setAttribute("isFactoryMgr", Utils.isInRole(Role.FACTORY_MANAGER.getValue()));	
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
			FMCG Automation System ( <%=Utils.getLoggedUserFactoryName()%> : <c:if test="${isFactoryMgr == true}">Manager</c:if><c:if test="${isFactoryMgr != true}">Operator</c:if> Panel )
		</div>		
	</div>
</div>
<div id="primary_nav" class="clearfix">
				<div class="main_width">
					<ul class="ipsList_inline" id="community_app_menu">			
						<c:if test="${isFactoryMgr == true}">		
							<li id="nav_menu_1" class="left  ">
								<a id="menu1" href="#" title="">Module Settings  <img src="<%= contextPath %>/resources/images/useropts_arrow.png"></a>
							</li>
							<li id="nav_menu_3" class="left  ">
								<a id="menu3" href="#" title="">Pending RM Purchase Requests</a>							
							</li>
							<li id="nav_menu_6" class="left  ">
								<a id="menu6" href="#" title="">Pending RM Damage Requests</a>							
							</li>
							<li id="nav_menu_4" class="left  ">
								<a id="menu4" href="#" title="">Pending Production In Requests</a>
							</li>																												
							<li id="nav_menu_5" class="left  ">
								<a id="menu5" href="#" title="">Pending Product Sell Requests</a>
							</li>			
						</c:if>			
						<c:if test="${isFactoryMgr != true}">
							<li id="nav_menu_1" class="left  ">
								<a id="menu1" href="#" title="">Raw Material Inventory</a>
							</li>
							<li id="nav_menu_2" class="left  ">
								<a id="menu2" href="#" title="">RM Purchase Request</a>
							</li>
							<li id="nav_menu_5" class="left ">
								<a id="menu5" href="#" title="">RM Damage Request</a>
							</li>
							<li id="nav_menu_3" class="left  ">
								<a id="menu3" href="#" title="">Finished Product Inventory</a>							
							</li>																												
							<li id="nav_menu_4" class="left  ">
								<a id="menu4" href="#" title="">Production In Request</a>
							</li>							
						</c:if>
					</ul>
				</div>
 </div>
 
 <div style="display: none; z-index: 9999; position: absolute;left: 83px;" id="nav_1_menucontent" class="submenu_container clearfix boxShadow">
		<div style="z-index: 10000;" class="left">
			<ul style="z-index: 10000;" class="submenu_links" id="nav_1_menucontentul">
				<li style="z-index: 10000;" id="nav_menu_7" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_7_trigger" href="<%= contextPath %>/factory/rawMaterialList.html" title="">Raw Materials</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_8" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_8_trigger" href="<%= contextPath %>/factory/productList.html" title="">Finished Products</a>								
				</li>
			</ul>
		</div>
</div>
<script type="text/javascript">
 	$(function(){ 		
 		$("#nav_menu_1").mouseover(function(){
 			$('#nav_1_menucontent').show();
 		});
 		$("#nav_1_menucontent").mouseover(function(){
 			$('#nav_1_menucontent').show();
 		});
 		$("#nav_menu_1").mouseout(function(){
 			$('#nav_1_menucontent').hide();
 		});
 		$("#nav_1_menucontent").mouseout(function(){
 			$('#nav_1_menucontent').hide();
 		});
 		
 	});	
</script>	