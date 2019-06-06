<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/index.css" type="text/css">
<link rel="stylesheet" href="styles/login.css" type="text/css">
<html>
<head>
    <title><c:out value="${hotel.getName()} - Login page"/></title>
</head>
<body>
<jsp:include page="/jsps/header.jsp" />

<body>
<div class="wrapper">
    <div class="content">
        <div class="login-page">
            <div class="form">
                <form class="register-form">
                    <input type="text" placeholder="name"/>
                    <input type="password" placeholder="password"/>
                    <input type="text" placeholder="email address"/>
                    <button>create</button>
                    <p class="message">Already registered? <a href="#">Sign In</a></p>
                </form>
                <form class="login-form">
                    <input type="text" placeholder="username"/>
                    <input type="password" placeholder="password"/>
                    <button>login</button>
                    <p class="message">Not registered? <a href="/signup">Create an account</a></p>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsps/footer.jsp" />
</body>
</html>