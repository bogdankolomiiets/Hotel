<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/book.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Booking page"/></title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />

<body>
<c:set var="types" value="${types}"/>
<c:set var="level" value="${level}"/>
<div class="wrapper">
    <div class="content">
        <div class="form">
            <form class="signup-form" name="booking" autocomplete="off" method="post">
                <div>
                    <c:forEach var="t" items="${types}">
                        <label class="infoLabel"><input name="roomType" value="${t.getIntValue()}" type="radio" onclick="this.form.submit();">
                            <c:if test="${t.getIntValue() == 1}"><fmt:message key="roomType.single"/></c:if>
                            <c:if test="${t.getIntValue() == 2}"><fmt:message key="roomType.double"/></c:if>
                            <c:if test="${t.getIntValue() == 3}"><fmt:message key="roomType.triple"/></c:if>
                            <c:if test="${t.getIntValue() == 4}"><fmt:message key="roomType.quad"/></c:if>
                            <c:if test="${t.getIntValue() == 5}"><fmt:message key="roomType.king"/></c:if>
                        </label>
                    </c:forEach>
                </div>
                <label class="infoLabel"><fmt:message key="room.StartDate"/></label><input type="date" name="StartDate">
                <label class="infoLabel"><fmt:message key="room.EndDate"/></label><input type="date" name="EndDate">
                <input type="submit">
            </form>
        </div>
    </div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
