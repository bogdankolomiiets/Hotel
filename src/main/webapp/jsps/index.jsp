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
  <img class="left" src="images/163297644.jpg" alt="room photo" width="1024" height="687">
  <p class="right"><div class="text"><fmt:message key="welcome.hotelInfo"/></div></p>
  <div style="clear: left"></div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>