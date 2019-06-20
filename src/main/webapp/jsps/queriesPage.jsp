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
    <title><c:out value="${hotel.getName()} - queries page"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body>
<div style="height: 80%">
        <form class="infoLabel">
            <c:if test="${clientQueries.size() == 0}">
                <label><fmt:message key="book.admin.query.none"/></label>
            </c:if>
        </form>
        <c:forEach var="query" items="${clientQueries}">
            <form name="queryForm" method="post" class="infoLabel">
                <label><fmt:message key="book.reservationText"/></label>
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
                <input type="hidden" name="queryId" value="${query.getQueryId()}">
                <input type="hidden" name="queryRoomType" value="${query.getRoomType().getIntValue()}">
                <input type="hidden" name="queryRoomLevel" value="${query.getRoomLevel().getIntValue()}">
                <input type="hidden" name="queryStartDate" value="${query.getRoomStartDate()}">
                <button name="process" type="submit"><fmt:message key="book.query.process"/></button>
            </form>
        </c:forEach>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
