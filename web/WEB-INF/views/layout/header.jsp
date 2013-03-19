<%	final String contextPath = request.getContextPath(); %>

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