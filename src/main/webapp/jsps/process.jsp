<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<div class="wrapper">
    <div class="content">
            <form name="queryForm" class="infoLabel">
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
                    <c:if test="${availableRoom.getRoomType().getIntValue() == 1}"><fmt:message key="roomType.single"/></c:if>
                    <c:if test="${availableRoom.getRoomType().getIntValue() == 2}"><fmt:message key="roomType.double"/></c:if>
                    <c:if test="${availableRoom.getRoomType().getIntValue() == 3}"><fmt:message key="roomType.triple"/></c:if>
                    <c:if test="${availableRoom.getRoomType().getIntValue() == 4}"><fmt:message key="roomType.quad"/></c:if>
                    <c:if test="${availableRoom.getRoomType().getIntValue() == 5}"><fmt:message key="roomType.king"/></c:if>
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
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
