<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <%@include file="templates/head.jsp"%>
    <style>
        <%@include file="templates/css/style.css"%>
        body{
        background-color: DarkCyan; /* Цвет фона веб-страницы */
        }
    </style>
    <title>Sign In</title>
</head>
<body>
<form action="/checkSignIn" method = "get" >
    <div class = "login-box">
        <h1>Login</h1>
        <%
            String passError = request.getParameter("passworderror");
            if (passError != null){
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong>Incorrect password  or some inputs are not filled</strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>
        <%
            String emailerror = request.getParameter("emailerror");
            if (emailerror != null){
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong>Incorrect email or some inputs are not filled</strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%}%>
        <div class = "textbox">
            <i class = "fa fa-user" aria-hidden="true"></i>
            <input type = "text" style="color:black"   placeholder = "Username" name = "email" value = "">
        </div>
        <div class = "textbox">
            <i class = "fa fa-lock" aria-hidden = "true"></i>
            <input type = "password" style="color:black"  placeholder = "Password" name = "password" value = "">
        </div>
        <button class="btn" type="submit" >Sign In</button>
        <a href = "/registration">  <input class = "btn" type = "button" name = "" value = " Registration"></a>
    </div>
</form>


</body>
</html>
