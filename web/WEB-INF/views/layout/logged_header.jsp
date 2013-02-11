<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.security.Principal"%>
<%
	final String contextPath = request.getContextPath();
	final Principal loggedUser = request.getUserPrincipal(); 
%>
<div id="header">
    <div id="logo">
    	<img src="<%= contextPath %>/resources/images/wkfs-logo.jpg" />
    	<c:if test="${breadCrumbList != null}">
    		<div class="breadcrumb">
    			<c:forEach var="crumb" items="${breadCrumbList}" varStatus="idx">
					<a href="${crumb[1]}" class="notInForm">${crumb[0]}</a>
					<c:if test="${idx.index < fn:length(breadCrumbList)-1}">
						&nbsp;>&nbsp;
					</c:if>
				</c:forEach> 
			</div>			
		</c:if>	
		<div class="breadcrumbChild" style="display: none;">
		</div>
    </div>
    <div id="pageheader">
		<span id="pageheadertxt">${pageTitle}</span> &nbsp; &nbsp;
		<c:if test="${ctr != null && isNewCTR != true}">
			[<spring:message code="layout.subTitle.head"/>: ${ctr.id} : <fmt:formatDate value="${ctr.created}" pattern="MM/dd/yyyy" />]
		</c:if>
	</div>
    <div id="headerrightblock">
        <span>
			<%= loggedUser.getName() %>&nbsp; | &nbsp;<a class="notInForm" href="<%= contextPath %>/logout.html" alt="Logout"><spring:message code="layout.label.logout"/></a><br />			
		</span>
    </div>
</div>
