<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/styles/header.css" type="text/css">
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>

<div class="topnav">
    <div>
        <ul class="topmenu">
            <li><a href="/mariam"><img src="/images/Letter_M.png" alt="logo img" width="250" height="132"></a></li>
            <li><a href="/mariam"><fmt:message key="header.menu.home"/></a></li>
            ${check_login}
            <li><a href="/book"><fmt:message key="header.menu.book"/></a></li>
            <li><a href="#"><fmt:message key="header.menu.language"/></a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}?language=ua"><img src="/images/UA-Ukraine-Flag-icon.png" alt="UA" width="50"/></a></li>
                    <li><a href="${pageContext.request.contextPath}?language=en"><img src="/images/United-Kingdom-flag-icon.png" alt="EN" width="50"/></a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>