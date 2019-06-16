<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="styles/header.css" type="text/css">
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>

<div class="topnav">
    <div>
        <ul class="topmenu">
            <li><a href="home"><img src="images/Letter_M.png" alt="logo img" width="250" height="132"></a></li>
            <li><a href="home"><fmt:message key="header.menu.home"/></a></li>
            <li><a href="prices"><fmt:message key="header.menu.prices"/></a></li>
            <li><a href="book"><fmt:message key="header.menu.book"/></a></li>
            <li><a href="#"><fmt:message key="header.menu.language"/></a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.queryString}?language=ua"><img src="images/UA-Ukraine-Flag-icon.png" alt="UA" width="50"/></a></li>
                    <li><a href="${pageContext.request.queryString}?language=en"><img src="images/United-Kingdom-flag-icon.png" alt="EN" width="50"/></a></li>
                </ul>
            </li>
            <div class="right">
                <c:set var="user" value="${cookie.get('user').value}"/>
                <c:if test="${user == null || user == ''}">
                    <li><a href="login"><fmt:message key="header.menu.login"/></a></li>
                    <li><a href="signup"><fmt:message key="header.menu.signup"/></a></li>
                </c:if>
                <c:if test="${user == 'valid'}">
                    <li><a href="cabinet"><fmt:message key="header.menu.cabinet"/></a></li>
                    <li><a href="logout"><fmt:message key="header.menu.logout"/></a></li>
                </c:if>
            </div>
            <div style="clear: right"></div>
        </ul>
    </div>
</div>