<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="rtt" uri="http://mariam.com/jstl/rtt" %>
<%@ taglib prefix="rlt" uri="http://mariam.com/jstl/rlt" %>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/cabinet.css" type="text/css">
<link rel="stylesheet" href="styles/process.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - process page"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body>
<div style="height: 80%">
            <form name="queryForm" class="infoLabel">
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
            </form>

        <hr>

        <c:if test="${availableRooms.size() == 0}">
        <form name="noAvailableRoomForm" method="post" class="infoLabel">
            <fmt:message key="room.noAvailable"/>
            <input type="hidden" name="denyQueryId" value="${query.getQueryId()}">
            <button class="alert" type="submit" onclick="this.form.submit()"><fmt:message key="room.noAvailable.deny"/></button>
            <button name="suggest" type="button" onclick="alert('TO BE CONTINUED...')"><fmt:message key="room.noAvailable.suggest"/></button>
        </form>
        </c:if>

        <c:forEach var="availableRoom" items="${availableRooms}">
            <form name="availableRoomForm" method="post" class="infoLabel">
                <label><fmt:message key="room.availableText"/></label>
                <label><fmt:message key="room.number"/> ${availableRoom.getRoomNumber()}</label>
                <label>
                    <rtt:roomType intType="${availableRoom.getRoomType().getIntValue()}" />
                </label>
                <label>
                    <c:if test="${availableRoom.getRoomLevel().getIntValue() == 1}"><fmt:message key="roomLevel.economy"/></c:if>
                    <c:if test="${availableRoom.getRoomLevel().getIntValue() == 2}"><fmt:message key="roomLevel.standard"/></c:if>
                    <c:if test="${availableRoom.getRoomLevel().getIntValue() == 3}"><fmt:message key="roomLevel.improved"/></c:if>
                    <c:if test="${availableRoom.getRoomLevel().getIntValue() == 4}"><fmt:message key="roomLevel.deluxe"/></c:if>
                </label>
                <label><fmt:message key="room.pricePerDay"/> ${availableRoom.getStringRoomPrice()} </label>
                <label><fmt:message key="room.StartDate"/> ${availableRoom.getRoomStartDate()} </label>
                <label><fmt:message key="room.EndDate"/> ${availableRoom.getRoomEndDate()} </label>
                <input type="hidden" name="queryId" value="${query.getQueryId()}">
                <input type="hidden" name="roomNo" value="${availableRoom.getRoomNumber()}">
                <button name="assign" type="submit"><fmt:message key="book.query.assign"/></button>
            </form>
        </c:forEach>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
