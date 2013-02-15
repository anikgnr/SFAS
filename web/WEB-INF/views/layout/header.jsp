<%	final String contextPath = request.getContextPath(); %>

<div id="header_bar" class="clearfix">
	<div class="main_width">
		<div id="user_navigation" class="not_logged_in">
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
		<div id="logo">
			<a href="#" title="Go to Home Page" rel="home" accesskey="1"><img src="<%=contextPath%>/resources/images/logo.png" alt="Logo"></a>
		</div>
		<!-- <div id="search" class="right">
		test dialog
		</div> -->
	</div>
</div>
<div id="primary_nav" class="clearfix"><div class="main_width" style="padding-bottom: 10px;">&nbsp;</div></div>