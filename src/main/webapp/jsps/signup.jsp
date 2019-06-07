<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/login.css" type="text/css">
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title><c:out value="${hotel.getName()} - Sign up page"/></title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />

<body>
<div class="wrapper">
    <div class="content"></div>
                    <form class="register-form" method="post">
                        <input type="text" placeholder=""/>
                        <input type="password" placeholder="password"/>
                        <input type="text" placeholder="email address"/>
                        <button>create</button>
                        <p class="message">Already registered? <a href="#">Sign In</a></p>
                    </form>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>