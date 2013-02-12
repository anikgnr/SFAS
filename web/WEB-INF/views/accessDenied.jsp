<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- <title><tiles:insertAttribute name="title" ignore="true"/></title> --%>
	<link type="text/css" href="resources/styles/base.css" rel="stylesheet" />   
	<title>ddd :&nbsp; dddd </title>
</head>
<body>
	<div id="header">
    	<div id="logo"><img src="resources/images/wkfs-logo.jpg" /></div>
    	<div id="pageheader">ttt</div>
	</div>
    <div id="formblock" style="height: 400px; padding-top: 50px;">
        <div style="padding-left:20px;">
        	<h2>ddd</h2>
        	<p>
        		dddddddddddddddddddddddddddddddd <a href="${url}">Click here</a>    	       	
        	</p>
        </div>
	</div>
 
    <div id="footer">
    	<div id="copyright"><spring:message code="layout.footer.label"/></div>
	</div>
</body>
</html>