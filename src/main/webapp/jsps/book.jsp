<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="rtt" uri="http://mariam.com/jstl/rtt" %>
<%@ taglib prefix="rlt" uri="http://mariam.com/jstl/rlt" %>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/book.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Booking page"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body onload="setStartDate(), setEndDate(), calculateCountOfDays()">
        <div class="form">
            <form class="signup-form" id="bookingForm" name="bookingForm" autocomplete="off" method="post">
                <label class="infoLabel"><fmt:message key="book.reservationText"/></label>
                <div>
                    <c:forEach var="type" items="${typesFromServer}">
                        <label class="radioLabel"><input name="selectedType" value="${type}" required
                            type="radio" <c:if test="${previousType == type}">checked="checked"</c:if> onclick="this.form.submit()">
                            <rtt:roomType intType="${type.getIntValue()}" />
                        </label>
                    </c:forEach>
                </div>
                <div>
                    <c:forEach var="level" items="${levelsFromServer}">
                        <label class="radioLabel"><input name="selectedLevel" value="${level}" onclick="this.form.submit()" type="radio"
                                                         <c:if test="${previousLevel == level}">checked="checked"</c:if> required>
                            <rlt:roomLevel intType="${level.getIntValue()}"/>
                        </label>
                    </c:forEach>
                </div>
                <label class="radioLabel"><fmt:message key="room.StartDate"/></label>
                <input type="date" value="${previousStartDate}" name="StartDate" id="StartDate" oncancel="setStartDate()" oninput="setStartDate(), setEndDate(), calculateCountOfDays(), this.form.submit()" required>
                <label class="radioLabel"><fmt:message key="room.EndDate"/></label>
                <input type="date" value="${previousEndDate}" name="EndDate" id="EndDate" oninput="setStartDate(), setEndDate(), calculateCountOfDays(), this.form.submit()" required>
                <input type="hidden" id="countOfDays" name="countOfDays" value="0">
                <label class="highlightPrices">
                    <c:if test="${roomPrice > 0}">
                    <fmt:message key="room.price"/> ${roomPrice}
                        <c:if test="${amount > 0}">
                            <input type="hidden" name="amount" value="${amount}">
                            <fmt:message key="room.Amount"/> ${amount}
                        </c:if>
                    </c:if>
                </label>
                <input type="hidden" id="submitViaButton" name="submitViaButton" value="0">
                <button type="submit" onclick="{
                    if (document.forms['bookingForm'].checkValidity()){
                   form.elements['submitViaButton'].value = '1'
                   }
                }">
                    <fmt:message key="book.sendRequest"/>
                </button>
            </form>
        </div>

        <script type="text/javascript">
            function setStartDate() {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth()+1; //January is 0!
                var yyyy = today.getFullYear();
                if(dd<10){
                    dd='0'+dd
                }
                if(mm<10){
                    mm='0'+mm
                }
                today = yyyy+'-'+mm+'-'+dd;
                document.getElementById("StartDate").setAttribute("min", today);
                document.getElementById("EndDate").setAttribute("min", today);
            }
        </script>

        <script type="text/javascript">
            function setEndDate() {
                var startDate = document.getElementById('StartDate').valueAsDate;
                var dd = startDate.getDate()+1;
                var mm = startDate.getMonth()+1; //January is 0!
                var yyyy = startDate.getFullYear();
                if(dd<10){
                    dd='0'+dd
                }
                if(mm<10){
                    mm='0'+mm
                }
                var endDateMin = yyyy+'-'+mm+'-'+dd;
                document.getElementById("EndDate").setAttribute("min", endDateMin);
            }
        </script>
        
        <script type="text/javascript">
            function calculateCountOfDays() {
                var first = document.getElementById("StartDate").valueAsDate;
                var second = document.getElementById("EndDate").valueAsDate;
                document.getElementById("countOfDays").value = Math.round((second - first)/(1000*60*60*24));
            }
        </script>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
