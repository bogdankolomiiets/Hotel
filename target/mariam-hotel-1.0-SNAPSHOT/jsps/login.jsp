<%@ page import="com.hotel.mariam.entity.Hotel" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% Hotel hotel = (Hotel) request.getAttribute("hotel");%>
    <title><% out.print(hotel.getName());%></title>
    <style>
        <%@include file="/styles/login.css"%>
    </style>
</head>
<body>

</body>
</html>