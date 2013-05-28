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
			FMCG Automation System ( <spring:message code="layout.module.admin"/> )
		</div>		
	</div>
</div>
<div id="primary_nav" class="clearfix">
				<div class="main_width">
					<ul class="ipsList_inline" id="community_app_menu">					
						<li id="nav_menu_1" class="left  ">
							<a id="menu1" href="<%= contextPath %>/admin/userList.html" title="">User Module</a>
						</li>
						<li id="nav_menu_12" class="left  ">
							<a id="menu12"  href="#" class="ipbmenu" >Zone Settings <img src="<%= contextPath %>/resources/images/useropts_arrow.png"></a>
						</li>
						<li id="nav_menu_2" class="left  ">
							<a id="menu2"  href="#" class="ipbmenu" >Channel Settings <img src="<%= contextPath %>/resources/images/useropts_arrow.png"></a>
						</li>
						<li id="nav_menu_8" class="left  ">
							<a id="menu8" href="<%= contextPath %>/admin/productList.html" title="">Product Module</a>
						</li>			
						<li id="nav_menu_18" class="left  ">
							<a id="menu18"  href="#" class="ipbmenu" >Distribution Settings <img src="<%= contextPath %>/resources/images/useropts_arrow.png"></a>
						</li>														
						<li id="nav_other_apps" style="">
							<a href="#" class="ipbmenu" id="more_apps">Other Settings <img src="<%= contextPath %>/resources/images/useropts_arrow.png"></a>
						</li>											
					</ul>
				</div>
 </div>
 	
 	<div style="display: none; z-index: 9999; position: absolute;left: 601px;" id="nav_18_menucontent" class="submenu_container clearfix boxShadow">
		<div style="z-index: 10000;" class="left">
			<ul style="z-index: 10000;" class="submenu_links" id="nav_18_menucontentul">
				<li style="z-index: 10000;" id="nav_menu_9" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_9_trigger" href="<%= contextPath %>/admin/depoList.html" title="">DEPO Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_11" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_11_trigger" href="<%= contextPath %>/admin/distributorList.html" title="">Distributor Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_7" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_7_trigger" href="<%= contextPath %>/admin/outletList.html" title="">Outlet Module</a>								
				</li>
			</ul>
		</div>
	</div>
	
 	<div style="display: none; z-index: 9999; position: absolute;left: 188px;" id="nav_12_menucontent" class="submenu_container clearfix boxShadow">
		<div style="z-index: 10000;" class="left">
			<ul style="z-index: 10000;" class="submenu_links" id="nav_12_menucontentul">
				<li style="z-index: 10000;" id="nav_menu_13" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_13_trigger" href="<%= contextPath %>/admin/regionList.html" title="">Region Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_14" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_14_trigger" href="<%= contextPath %>/admin/areaList.html" title="">Area Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_15" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_15_trigger" href="<%= contextPath %>/admin/territoryList.html" title="">Territory Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_16" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_16_trigger" href="<%= contextPath %>/admin/routeList.html" title="">Route Module</a>								
				</li>
			</ul>
		</div>
	</div>
	
 	<div style="display: none; z-index: 9999; position: absolute;left: 324px;" id="nav_2_menucontent" class="submenu_container clearfix boxShadow">
		<div style="z-index: 10000;" class="left">
			<ul style="z-index: 10000;" class="submenu_links" id="nav_2_menucontentul">
				<li style="z-index: 10000;" id="nav_menu_3" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_3_trigger" href="<%= contextPath %>/admin/rsmList.html" title="">RSM Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_4" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_4_trigger" href="<%= contextPath %>/admin/asmList.html" title="">ASM Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_5" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_5_trigger" href="<%= contextPath %>/admin/tsoList.html" title="">TSO Module</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_6" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_6_trigger" href="<%= contextPath %>/admin/srList.html" title="">SR Module</a>								
				</li>
			</ul>
		</div>
	</div>
	
	<div style="display: none; z-index: 9999; position: absolute;left: 771px;" id="more_apps_menucontent" class="submenu_container clearfix boxShadow">
		<div style="z-index: 10000;" class="left">
			<ul style="z-index: 10000;" class="submenu_links" id="more_apps_menucontentul">
				<li style="z-index: 10000;" id="nav_menu_10" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_10_trigger" href="<%= contextPath %>/admin/accountList.html" title="">Company Bank Accounts</a>								
				</li>
				<li style="z-index: 10000;" id="nav_menu_17" class="submenu_li">
						<a style="z-index: 10000;" id="nav_menu_17_trigger" href="<%= contextPath %>/admin/factoryList.html" title="">Company Factories</a>								
				</li>
			</ul>
		</div>
	</div>
  <script type="text/javascript">
 	$(function(){
 		$("#nav_other_apps").mouseover(function(){
 			$('#more_apps_menucontent').show();
 		});
 		$("#more_apps_menucontent").mouseover(function(){
 			$('#more_apps_menucontent').show();
 		});
 		$("#nav_other_apps").mouseout(function(){
 			$('#more_apps_menucontent').hide();
 		});
 		$("#more_apps_menucontent").mouseout(function(){
 			$('#more_apps_menucontent').hide();
 		});
 		
 		$("#nav_menu_2").mouseover(function(){
 			$('#nav_2_menucontent').show();
 		});
 		$("#nav_2_menucontent").mouseover(function(){
 			$('#nav_2_menucontent').show();
 		});
 		$("#nav_menu_2").mouseout(function(){
 			$('#nav_2_menucontent').hide();
 		});
 		$("#nav_2_menucontent").mouseout(function(){
 			$('#nav_2_menucontent').hide();
 		});
 		
 		$("#nav_menu_12").mouseover(function(){
 			$('#nav_12_menucontent').show();
 		});
 		$("#nav_12_menucontent").mouseover(function(){
 			$('#nav_12_menucontent').show();
 		});
 		$("#nav_menu_12").mouseout(function(){
 			$('#nav_12_menucontent').hide();
 		});
 		$("#nav_12_menucontent").mouseout(function(){
 			$('#nav_12_menucontent').hide();
 		});
 		
 		$("#nav_menu_18").mouseover(function(){
 			$('#nav_18_menucontent').show();
 		});
 		$("#nav_18_menucontent").mouseover(function(){
 			$('#nav_18_menucontent').show();
 		});
 		$("#nav_menu_18").mouseout(function(){
 			$('#nav_18_menucontent').hide();
 		});
 		$("#nav_18_menucontent").mouseout(function(){
 			$('#nav_18_menucontent').hide();
 		});
 	});	
</script>
	