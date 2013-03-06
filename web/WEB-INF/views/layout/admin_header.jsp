<%@ page import="java.security.Principal"%>

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
		<div id="logo">
			<a href="#" title="Go to Home Page" rel="home" accesskey="1"><img src="<%=contextPath%>/resources/images/logo.png" alt="Logo"></a>
		</div>
		<!-- <div id="search" class="right">
		test dialog
		</div> -->
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
							<a id="menu3" href="#" title="">ASM Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu4" href="#" title="">TSO Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu5" href="#" title="">SR Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu6" href="#" title="">Depo Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu7" href="#" title="">Distributor Module</a>
						</li>
						<li id="nav_menu_1" class="left  ">
							<a id="menu8" href="#" title="">Outlet Module</a>
						</li>						
					</ul>
				</div>
 </div>