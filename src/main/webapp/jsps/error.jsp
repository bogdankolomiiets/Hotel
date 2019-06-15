<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="styles/error.css" type="text/css">
<link rel="stylesheet" href="styles/index.css" type="text/css">
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />
<div class="container">
    <div class="errorDiv">
        <fmt:message key="error.massage1"/>
    </div>
    <div class="errorDiv">
        <fmt:message key="error.massage2"/>
    </div>
    <div class="errorDiv">
        <fmt:message key="error.massage3"/>
    </div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
