<%@ page import="java.awt.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/book.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Booking page"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body>
<div class="wrapper">
    <div class="content">
        <div class="form">
            <form class="signup-form" name="bookingForm" autocomplete="off" method="post">
                <label class="infoLabel"><fmt:message key="book.reservationText"/></label>
                <div>
                    <c:forEach var="type" items="${typesFromServer}">
                        <label class="radioLabel"><input name="selectedType" value="${type}" required
                            type="radio" <c:if test="${previousType == type}">checked="checked"</c:if> onclick="this.form.submit()">
                            <c:if test="${type.getIntValue() == 1}"><fmt:message key="roomType.single"/></c:if>
                            <c:if test="${type.getIntValue() == 2}"><fmt:message key="roomType.double"/></c:if>
                            <c:if test="${type.getIntValue() == 3}"><fmt:message key="roomType.triple"/></c:if>
                            <c:if test="${type.getIntValue() == 4}"><fmt:message key="roomType.quad"/></c:if>
                            <c:if test="${type.getIntValue() == 5}"><fmt:message key="roomType.king"/></c:if>
                        </label>
                    </c:forEach>
                </div>
                <div>
                    <c:forEach var="level" items="${levelsFromServer}">
                        <label class="radioLabel"><input name="selectedLevel" value="${level}" type="radio" required>
                            <c:if test="${level.getIntValue() == 1}"><fmt:message key="roomLevel.economy"/></c:if>
                            <c:if test="${level.getIntValue() == 2}"><fmt:message key="roomLevel.standard"/></c:if>
                            <c:if test="${level.getIntValue() == 3}"><fmt:message key="roomLevel.improved"/></c:if>
                            <c:if test="${level.getIntValue() == 4}"><fmt:message key="roomLevel.deluxe"/></c:if>
                        </label>
                    </c:forEach>
                </div>
                <label class="radioLabel"><fmt:message key="room.StartDate"/></label><input type="date" name="StartDate" required>
                <label class="radioLabel"><fmt:message key="room.EndDate"/></label><input type="date" name="EndDate" required>
                <input type="hidden" id="submitViaButton" name="submitViaButton" value="0">
                <button type="submit" onclick="{
                    if (document.forms['bookingForm'].checkValidity()){
                   form.elements['submitViaButton'].value = '1'
                   }
                }
                " name="submitButton"><fmt:message key="book.sendRequest"/></button>
            </form>
        </div>
    </div>
</div>
<c:catch var="ex"/>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
