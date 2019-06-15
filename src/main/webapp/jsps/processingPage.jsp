<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/cabinet.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - admin page"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body>
<div class="wrapper">
    <div class="content">
        <c:forEach var="query" items="${clientQueries}">
            <form name="queryForm" method="post" class="infoLabel">
                <label><fmt:message key="book.reservationText"/></label>
                <label>
                    <c:if test="${query.getRoomType().getIntValue() == 1}"><fmt:message key="roomType.single"/></c:if>
                    <c:if test="${query.getRoomType().getIntValue() == 2}"><fmt:message key="roomType.double"/></c:if>
                    <c:if test="${query.getRoomType().getIntValue() == 3}"><fmt:message key="roomType.triple"/></c:if>
                    <c:if test="${query.getRoomType().getIntValue() == 4}"><fmt:message key="roomType.quad"/></c:if>
                    <c:if test="${query.getRoomType().getIntValue() == 5}"><fmt:message key="roomType.king"/></c:if>
                </label>
                <label>
                    <c:if test="${query.getRoomLevel().getIntValue() == 1}"><fmt:message key="roomLevel.economy"/></c:if>
                    <c:if test="${query.getRoomLevel().getIntValue() == 2}"><fmt:message key="roomLevel.standard"/></c:if>
                    <c:if test="${query.getRoomLevel().getIntValue() == 3}"><fmt:message key="roomLevel.improved"/></c:if>
                    <c:if test="${query.getRoomLevel().getIntValue() == 4}"><fmt:message key="roomLevel.deluxe"/></c:if>
                </label>
                <label><fmt:message key="room.BookingDate"/>${query.getRoomBookingDate()}</label>
                <label><fmt:message key="room.StartDate"/>${query.getRoomStartDate()}</label>
                <label><fmt:message key="room.EndDate"/>${query.getRoomEndDate()}</label>
                <input type="hidden" name="queryId" value="${query.getQueryId()}">
                <input type="hidden" name="queryRoomType" value="${query.getRoomType().getIntValue()}">
                <input type="hidden" name="queryRoomLevel" value="${query.getRoomLevel().getIntValue()}">
                <input type="hidden" name="queryStartDate" value="${query.getRoomStartDate()}">
                <button name="process" type="submit"><fmt:message key="book.query.process"/></button>
            </form>
        </c:forEach>
        <c:forEach var="availableRoom" items="${availableRooms}">
            <form name="availableRoomForm" method="post" class="infoLabel">
                <label><fmt:message key="room.availableText"/></label>
                <c:out value="${availableRoom}"/>
                <input type="hidden" name="queryStartDate" value="${query.getRoomStartDate()}">
                <button name="assign" type="submit"><fmt:message key="book.query.assign"/></button>
            </form>
        </c:forEach>
    </div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
