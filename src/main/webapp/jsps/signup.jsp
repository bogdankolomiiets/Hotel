<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javascript" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmp" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/login.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Sign up page"/></title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />

<body>
<div class="wrapper">
    <div class="form">
        <form class="register-form" method="post" name="signUpForm">
            <label class="infoLabel">
                <c:set var="isRegister" value="${registerInfo}"/>
                <c:if test="${isRegister == 0}">
                    <fmt:message key="signup.registered.notOK"/>
                </c:if>
                <c:if test="${isRegister == 1}">
                    <fmt:message key="signup.registered.OK"/>
                </c:if>
                <c:if test="${isRegister == -1}">
                    <fmt:message key="signup.registered.emailExist"/>
                </c:if>
            </label>
            <input class="field" type="text" name="clientName" required placeholder="<fmp:message key="signup.clientName"/>"/>
            <input class="field" type="text" name="clientSurname" required placeholder="<fmp:message key="signup.clientSurname"/>"/>
            <input class="field" type="text" name="clientPhone" required placeholder="<fmt:message key="signup.clientPhone"/>"/>
            <input class="field" type="email" required name="clientEmail" placeholder="<fmp:message key="signup.clientEmail"/>"/>
            <input class="field" type="password" name="clientPass" required placeholder="<fmp:message key="login.pass"/>"/>
            <button type="submit" name="submitButton"><fmt:message key="signup.createButton"/></button>
            <p class="message"><fmt:message key="signup.registered"/><a href="login"><fmt:message key="signup.login"/></a></p>
        </form>
    </div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>