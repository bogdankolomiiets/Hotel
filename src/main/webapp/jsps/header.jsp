<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    body{
        margin: 0px;
        background: #dddddd;
    }
    /* Add a black background color to the top navigation */
    .topnav {
        background-color: #333;
        overflow: hidden;
        line-height: 70px;
    }

    /* Style the links inside the navigation bar */
    .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 25px;
    }

    .topnav_right{
        float: right;
        background: #4CAF50;
    }

    /* Change the color of links on hover */
    .topnav a:hover {
        background-color: #ddd;
        color: black;
    }

    /* Add a color to the active/current link */
    .topnav a.active {
        background-color: #4CAF50;
        color: white;
    }

    ul{
        margin: 0px;
        padding: 0px;
    }

    li{
        display: inline;
        text-decoration: none;
    }
</style>

<div class="topnav">
    <div>
        <a class="active" href="/">Home</a>
<%--        ${login_out}--%>
        <a href="/book">Book online</a>
    </div>
    <div class="topnav_right">
        <ul>
            <li><a href=""><img src="/images/Letter-M-PNG-Picture.png" alt="UA"/></a></li>
            <li><a href=""><img src="/images/Ukraine-Flag.png" alt="UA"/></a></li>
        </ul>
    </div>
</div>