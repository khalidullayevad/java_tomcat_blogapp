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
    <title>Registration</title>
</head>
<body>
<div class="container">
    <%
        String passError = request.getParameter("passworderror");
        if (passError != null){
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Incorrect password </strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%}%>
    <%
        String passErrorLength = request.getParameter("passworderrorLength");
        if (passErrorLength != null){
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Your Password length less than 8.</strong>
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
        <strong>Incorrect email </strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%}%>
    <%
        String data = request.getParameter("data");
        if (data != null){
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Please enter correct all datas!! </strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%}%>
    <%
        String full_name = request.getParameter("full_name");
        if (full_name != null){
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Incorrect name.Please enter your real name </strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%}%>

    <%
        String success = request.getParameter("success");
        if (success != null){
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>Successfull</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%}%>

</div>
<div class="login-box">
    <h1>Registration</h1>

    <form  action = "/registration" method = "post">
        <div class="textbox">
            <i class= "fa fa-user" aria-hidden="true"></i>
            <input type="text" style="color:black" placeholder="Name" name="name" value="">
        </div>
        <div class="textbox">
            <i class= "fa fa-user" aria-hidden="true"></i>
            <input type="text" style="color:black" placeholder="Surname" name="surname" value="">
        </div>
        <div class="textbox">
             <input type="date" style="color:black"  placeholder="Birth date" name="birthdate" value="">
        </div>
        <div class="textbox">
            <select class="form-control" name="group">
                <option value="12A">Group is: 12A</option>
                <option value="12B">Group is: 12B</option>
                <option value="12C">Group is: 12C</option>
                <option value="12D">Group is: 12D</option>
                <option value="11A">Group is: 11A</option>
                <option value="11B">Group is: 11B</option>
                <option value="11C">Group is: 11C</option>
                <option value="11D">Group is: 11D</option>
                <option value="10A">Group is: 12A</option>
                <option value="10B">Group is: 12B</option>
                <option value="10C">Group is: 12C</option>
                <option value="10D">Group is: 12D</option>
            </select>
        </div>
        <div class="textbox">
            <i class= "fa fa-user" aria-hidden="true"></i>
            <input type="email" style="color:black"  placeholder="E-mail" name="email" value="">
        </div>
        <div class="textbox">
            <i class= "fa fa-lock"  aria-hidden="true"></i>
            <input type="password" style="color:black" placeholder="Password" name="password" value="">
        </div>
        <div class="textbox">
            <i class= "fa fa-lock" aria-hidden="true"></i>
            <input type="password" style="color:black" placeholder="Password Confirmation" name="re-password" value="">
        </div>
        <input class="btn" type="submit" name="" value="Registration">
    </form>
</div>


</body>
</html>
