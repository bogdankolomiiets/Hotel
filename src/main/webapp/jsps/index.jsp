<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${pageContext.response.locale}"/>
<fmt:setBundle basename="language"/>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<html>
<head>
  <title><c:out value="${hotel.getName()} - Home page"/></title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />
<div class="wrapper">
  <img style="vertical-align:middle" src="images/163297644.jpg" alt="room photo" width="1024" height="687">
  <div class="text"><fmt:message key="welcome.hotelInfo"/></div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>