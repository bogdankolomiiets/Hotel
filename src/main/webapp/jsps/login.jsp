<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javascript" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/login.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Login page"/></title>
</head>
<jsp:include page="/jsps/header.jsp" />
<body>
<c:set var="exist" value="${userExists}"/>

<div class="wrapper">
    <div class="content">
        <div class="login-page">
            <div class="form">
                <form autocomplete="off" class="login-form" method="post">
                    <c:if test="${exist == -1}">
                        <label class="infoLabel"><fmt:message key="login.incorrect"/></label>
                        <label class="infoLabel"><fmt:message key="login.incorrect.pleace"/></label>
                    </c:if>
                    <input class="field" type="email" name="email" required placeholder="email"/>
                    <input class="field" type="password" name="password" required placeholder="<fmt:message key="login.pass"/>"/>
                    <button type="submit"><fmt:message key="login.loginButton"/></button>
                    <p class="message"><fmt:message key="login.not.registered"/><a href="/signup"><fmt:message key="login.create.account"/></a></p>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>