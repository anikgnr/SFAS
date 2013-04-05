<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	final String contextPath = request.getContextPath(); %>
<html>

<body>	
	<script type="text/javascript" src="<%= contextPath %>/resources/js/operator/depoOrder.js"></script>
	<div class="category_block block_wrap" style="width: 1020px;padding-top:20px;padding-left: 50px;">
		<h3 class="maintitle" style="width: 1000px;"><spring:message code="depo.order.form.title"/> for ${command.depo.fullName}</h3>
		<div class="ipsBox table_wrap" style="width: 1000px;">
			<div class="ipsBox_container" style="padding: 35px;">		
				<div id="formBlock1">
					<form:form method="post" action="./saveDepoOrder.html">
						<form:hidden path="id"/>
						<form:hidden id="depoId" path="depo.id"/>
						<form:hidden path="lastDeposit.id"/>
						<form:hidden path="orderAmount"/>
						<form:hidden path="depoBalance"/>
							
							
							<div id="orderErrorBlock" <c:if test="${command.errorMsg == null || command.errorMsg == ''}">style="display:none;"</c:if>>
								${command.errorMsg}
							</div>							
							
							<table class="orderHeadTable" border="1">
								<tr style="height: 30px;">
									<th align="left" style="width: 130px;"><spring:message code="depo.form.name"/></td>
									<td align="left" style="width: 350px;"><b>${command.depo.name}</b></td>
									<th align="left" style="width: 130px;"><spring:message code="depo.order.region.name"/></td>								
									<td align="left"><b>${command.depo.rsm.region.name}</b></td>
								</tr>
								<tr style="height: 30px;">
									<th align="left"><spring:message code="distributor.form.contactName"/></td>
									<td align="left"><b>${command.depo.contactName}</b></td>
									<th align="left"><spring:message code="depo.order.rsm.name"/></td>								
									<td align="left"><b>${command.depo.rsm.name}</b></td>
								</tr>
								<tr style="height: 30px;">
									<th align="left"><spring:message code="distributor.form.contactNumber"/></td>
									<td align="left"><b>${command.depo.mobileNumber}</b></td>
									<th align="left"><spring:message code="depo.order.rsm.number"/></td>								
									<td align="left"><b>${command.depo.rsm.mobileNumber}</b></td>
								</tr>
								<tr style="height: 30px;">
									<th align="left"><spring:message code="depo.form.location"/></td>
									<td align="left"><b>${command.depo.address}</b></td>								
									<th align="left"><spring:message code="depo.order.form.orderDate"/></td>
									<td align="left">
										<c:choose>
											<c:when test="${readOnly == true}">
												<fmt:formatDate value="${command.orderDate}" pattern="MM/dd/yyyy" />
											</c:when>
											<c:otherwise>
												<input size="20" type="text" id="orderDate" name="orderDate" value='<fmt:formatDate value="${command.orderDate}" pattern="MM/dd/yyyy" />'/>
											</c:otherwise>
										</c:choose>										
									</td>
								</tr>								
							</table>
						
						<br/><br/><br/><br/>
						<table class="orderTable" border="1">
							<tr style="height: 60px;">
								<th style="width: 25px;"> Sl </th>
								<th style="width: 120px;"> Product Name </th>
								<th style="width: 40px;"> Bag <br/> Size </th>
								<th style="width: 120px;">
									<table style="height: 60px;">
										<tr style="border-bottom: 1px solid gray;">
											<th colspan="3"> Current Stock </th>
										</tr>
										<tr>
											<th style="width: 55px;border-right: 1px solid gray;">Stocks</th>
											<th style="width: 55px;border-right: 1px solid gray;">Sales</th>
											<th>Damages</th>
										</tr>
									</table> 
								</th>
								<th style="width: 90px;">
									<table style="height: 60px;">
										<tr style="border-bottom: 1px solid gray;">
											<th colspan="2"> Product Plan </th>
										</tr>
										<tr>
											<th style="width: 30px;border-right: 1px solid gray;">Plan</th>
											<th style="width: 60px;" >Balance</th>											
										</tr>
									</table>  
								</th>
								<th style="width: 60px;"> Order <br/> Qty </th>
								<th style="width: 40px;"> Rate </th>
								<th style="width: 60px;"> Amount </th>
								<th style="width: 80px;"> Remarks </th>
							</tr>
							<c:forEach items="${command.orderLiList}" var="orderli" varStatus="idx">
								<form:hidden path="orderLiList[${idx.index}].id"/>
								<form:hidden path="orderLiList[${idx.index}].depoOrder.id"/>
								<form:hidden path="orderLiList[${idx.index}].serial"/>
								<form:hidden path="orderLiList[${idx.index}].product.id"/>
								<form:hidden path="orderLiList[${idx.index}].product.productName"/>
								<form:hidden path="orderLiList[${idx.index}].product.bagSize"/>
								<form:hidden path="orderLiList[${idx.index}].currentStock"/>
								<form:hidden path="orderLiList[${idx.index}].totalSale"/>
								<form:hidden path="orderLiList[${idx.index}].totalDamage"/>
								<form:hidden id="rate-${idx.index}" path="orderLiList[${idx.index}].currentRate"/>
								<form:hidden path="orderLiList[${idx.index}].currentProfitMargin"/>
								<form:hidden id="amount-${idx.index}" path="orderLiList[${idx.index}].amount"/>								
								<tr <c:choose><c:when test="${orderli.hasError == true}">style="height: 35px;background:#ED8080;color:black;"</c:when><c:otherwise>style="height: 35px;"</c:otherwise></c:choose> >
									<td style="width: 25px;">${orderli.serial}</td>
									<td style="width: 140px;">${orderli.product.productName}</td>
									<td style="width: 40px;">${orderli.product.bagSize} pcs</td>									
									<td style="width: 120px;">
										<table style="height: 35px;">
											<tr>
												<td style="width: 40px;border-right: 1px solid gray;">${orderli.currentStock}</td>
												<td style="width: 40px;border-right: 1px solid gray;">${orderli.totalSale}</td>
												<td style="width: 40px;">${orderli.totalDamage}</td>												
											</tr>
										</table>
									</td>
									<td style="width: 90px;">
										<table style="height: 35px;">
											<tr>
												<td style="width: 30px;border-right: 1px solid gray;"></td>
												<td style="width: 60px;">0</td>																									
											</tr>
										</table>
									</td>
									<td style="width: 60px;">
										<c:choose>
											<c:when test="${readOnly == true}">
												${orderli.quantity}
											</c:when>
											<c:otherwise>
												<form:input id="qty-${idx.index}" path="orderLiList[${idx.index}].quantity" cssClass="qty" />
											</c:otherwise>
										</c:choose>										
									</td>
									<td style="width: 40px;">${orderli.currentRate}</td>
									<td id="amountDiv-${idx.index}" style="width: 60px;">${orderli.amount}</td>
									<td style="width: 80px;">
										<c:choose>
											<c:when test="${readOnly == true}">
												${orderli.remark}
											</c:when>
											<c:otherwise>
												<form:input path="orderLiList[${idx.index}].remark" cssStyle="width: 79px;font-size: 11px;"/>
											</c:otherwise>
										</c:choose>												
									</td>									
								</tr>
							</c:forEach>
								<tr style="height: 35px;">
									<td colspan="7" style="font-size: 13px; color: #2C5687;text-align: right; padding-right: 20px;">Total Payable Amount</td>									
									<td id="totalAmount" colspan="2" style="font-size: 13px; color: #2C5687;text-align:left;padding-left: 33px;">${command.orderAmount}</td>									
								</tr>
						</table>
						<br/><br/><br/><br/>
						<table class="orderHeadTable" border="1">							
								<tr style="height: 30px;">
									<th align="left" style="width: 200px;">Last Deposit Date</td>
									<td align="left" style="width: 350px;"><fmt:formatDate value="${command.lastDeposit.depositDate}" pattern="MM/dd/yyyy" /></td>
									<th align="left" style="width: 130px;">Last Deposit Amount</td>								
									<td align="left">${command.lastDeposit.depositAmount} Tk</td>
								</tr>
								<tr style="height: 30px;">
									<th align="left">Last Deposit Bank</td>
									<td align="left">${command.lastDeposit.account.bankName}</td>
									<th align="left">Last Deposit Account</td>								
									<td align="left">${command.lastDeposit.account.bankAccount}</td>
								</tr>
								<tr style="height: 30px;">
									<th align="left"><b>Current Available Balance</b></td>
									<td align="left" colspan="3" style="font-size:13px; color: #2C5687;">
										${command.depoBalance} Tk
										<input type="hidden" id="currentBalance" value="${command.depoBalance}"/>
									</td>									
								</tr>																
						</table>
						
						<br/><br/><br/><br/>
						<center>
							<input class="button orange" id="backToDepoOrderList" type="button" value='<spring:message code="operator.back.order"/>'/>&nbsp;
							<c:choose>
								<c:when test="${readOnly == true}">
									<c:if test="${approveType != null && approveType != ''}">
										<input type="hidden" id="approveType" value="${approveType}"/>
										<input class="button orange" id="approveDepoOrder" type="button" value='<spring:message code="depo.order.form.approve"/>'/>
										<c:if test="${approveType != 'mis'}">
											&nbsp;<input class="button orange" id="denyDepoOrder" type="button" value='<spring:message code="depo.order.form.denied"/>'/>
										</c:if>
									</c:if>
									<c:if test="${command.mdApproved == true && command.delivered != true}">
										<input class="button orange" id="deliverDepoOrder" type="button" value='<spring:message code="depo.order.form.deliver"/>'/>
									</c:if>
								</c:when>
								<c:otherwise>
									<input class="button orange" id="submitDepoOrder" type="submit" value='<spring:message code="depo.order.form.submit"/>'/>
								</c:otherwise>
							</c:choose>							
						</center>			
					</form:form>
				</div>										
			</div>
		</div>
	</div>	  
</body>
</html>