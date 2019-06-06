<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Sign up page"/></title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />

<body>
<div class="wrapper">
    <div class="content"></div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>