<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/footer.css" type="text/css">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<div class="footer">
    <ul>
        <li><fmt:message key="footer.hotelInfo.city"/><c:out value="${hotel.getAddress()}"/></li>
        <li><fmt:message key="footer.hotelInfo.phone"/><c:out value="${hotel.getPhone()}"/>
            <fmt:message key="footer.hotelInfo.countOfFloors"/><c:out value="${hotel.getCountOfFloors()}"/></li>
        <li>
            <fmt:message key="footer.hotelInfo.stars"/>
            <c:forEach var="i" begin="1" end="${hotel.getCountOfStars()}" step="1">
                <img src="images/star4.png" alt="stars" width="40" height="40"/>
            </c:forEach>
        </li>
    </ul>

</div>
