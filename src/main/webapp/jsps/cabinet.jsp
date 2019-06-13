<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Personal cabinet"/></title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />

<body>
<div class="wrapper">
    <div>
        <label class="infoLabel"></label>
        <c:forEach var="query" items="${clientQueries}">
            <c:out value="${query}"/>
        </c:forEach>
    </div>

</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>
