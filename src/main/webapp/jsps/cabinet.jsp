<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="rtt" uri="http://mariam.com/jstl/rtt" %>
<%@ taglib prefix="rlt" uri="http://mariam.com/jstl/rlt" %>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/cabinet.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Personal cabinet"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body>
<div style="height: 80%">
        <form class="infoLabel">
            <c:if test="${clientQueries.size() == 0}">
                <label><fmt:message key="book.user.query.none"/></label>
            </c:if>
        </form>
        <c:forEach var="query" items="${clientQueries}">
            <form name="queryForm" method="post" class="infoLabel">
                <label><fmt:message key="book.query.waitStart"/></label>
                <label>
                    <rtt:roomType intType="${query.getRoomType().getIntValue()}" />
                </label>
                <label>
                    <rlt:roomLevel intType="${query.getRoomLevel().getIntValue()}"/>
                </label>
                <label><fmt:message key="room.BookingDate"/>${query.getRoomBookingDate()}</label>
                <label><fmt:message key="room.StartDate"/>${query.getRoomStartDate()}</label>
                <label><fmt:message key="room.EndDate"/>${query.getRoomEndDate()}</label>
                <label><fmt:message key="room.Amount"/>${query.getQueryAmount()}</label>
                <label>
                    <c:if test="${query.getQueryStatus() == 1}"><fmt:message key="book.query.processing"/></c:if>
                    <c:if test="${query.getQueryStatus() == 2}"><fmt:message key="book.query.deny"/></c:if>
                    <c:if test="${query.getQueryStatus() == 3}"><fmt:message key="book.query.success"/></c:if>
                </label>
                <input type="hidden" name="queryId" value="${query.getQueryId()}">
                <button name="cancel" type="submit"><fmt:message key="book.query.cancel"/></button>
            </form>
        </c:forEach>
        <hr>
        <c:forEach var="payment" items="${payments}">
        <form name="paymentForm" method="post" class="infoLabel">
            <label><fmt:message key="payment.account" /></label>
            <label>
                ${clientName}
                ${clientSurname}
                <fmt:message key="room.Amount"/> ${payment.getStringPaymentAmount()}
                <fmt:message key="bank.name"/> "${bank.getBankFullName()}"
                <fmt:message key="bank.bankIdentifierCode"/> "${bank.getBankIdentifierCode()}"
                <fmt:message key="bank.bankTaxpayerIdentificationNumber"/> "${bank.getBankTaxpayerIdentificationNumber()}"
                <fmt:message key="bank.bankCardNumber"/> "${bank.getBankCardNumber()}"
            </label>
            <input type="hidden" name="payment" value="redirect">
            <button name="pay" type="submit"><fmt:message key="payment.pay" /></button>
        </form>
    </c:forEach>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
