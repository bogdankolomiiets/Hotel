<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="rtt" uri="http://mariam.com/jstl/rtt" %>
<%@ taglib prefix="rlt" uri="http://mariam.com/jstl/rlt" %>
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
    <div class="centerDiv">
        <c:forEach var="room" items="${roomList}">
            <form>
                <label class="infoDiv">
                    <rtt:roomType intType="${room.getRoomType().getIntValue()}" />
                </label>
                <label class="infoDiv">
                    <rlt:roomLevel intType="${room.getRoomLevel().getIntValue()}"/>
                </label>
                <label class="priceDiv">
                    <fmt:message key="room.price"/><c:out value="${room.getStringRoomPrice()}"/>
                </label>
            </form>
        </c:forEach>
    </div>
    <jsp:include page="/jsps/footer.jsp" />
</body>
</html>
