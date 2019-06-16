<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javascript" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/prices.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - prices page"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body>
<div class="wrapper">
    <div class="centerDiv">
        <c:forEach var="room" items="${roomList}">
            <form>
                <label class="infoDiv">
                    <c:if test="${room.getRoomType().getIntValue() == 1}"><fmt:message key="roomType.single"/></c:if>
                    <c:if test="${room.getRoomType().getIntValue() == 2}"><fmt:message key="roomType.double"/></c:if>
                    <c:if test="${room.getRoomType().getIntValue() == 3}"><fmt:message key="roomType.triple"/></c:if>
                    <c:if test="${room.getRoomType().getIntValue() == 4}"><fmt:message key="roomType.quad"/></c:if>
                    <c:if test="${room.getRoomType().getIntValue() == 5}"><fmt:message key="roomType.king"/></c:if>
                </label>
                <label class="infoDiv">
                    <c:if test="${room.getRoomLevel().getIntValue() == 1}"><fmt:message key="roomLevel.economy"/></c:if>
                    <c:if test="${room.getRoomLevel().getIntValue() == 2}"><fmt:message key="roomLevel.standard"/></c:if>
                    <c:if test="${room.getRoomLevel().getIntValue() == 3}"><fmt:message key="roomLevel.improved"/></c:if>
                    <c:if test="${room.getRoomLevel().getIntValue() == 4}"><fmt:message key="roomLevel.deluxe"/></c:if>
                </label>
                <label class="priceDiv">
                    <fmt:message key="room.price"/><c:out value="${room.getStringRoomPrice()}"/>
                </label>
            </form>
        </c:forEach>
    </div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
