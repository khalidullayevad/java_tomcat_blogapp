<%@ page import="db.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <%@include file="templates/head.jsp"%>
    <style><%@include file="templates/css/main.css"%></style>
</head>
<body>
<%@include file="templates/adminNavbar.jsp"%>

<div class="container">
    <div class="row">
        <div class="col">
            <div class="card mt-4" style="width: 18rem;">
                <div class="card-header" style="background: darkcyan">
                    Post
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a href="/admin?allPosts=true"> All Posts</a></li>
                    <li class="list-group-item"><a href="/admin?verifiedPosts=true"> Checked Posts</a></li>
                    <li class="list-group-item"><a href="/admin?unverifiedPosts=true"> Unchecked Posts</a></li>
                </ul>
            </div>
            <div class="card mt-4" style="width: 18rem;">
                <div class="card-header" style="background: darkcyan">
                    Group Class
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a href="/adminStudent?All"> All Students </a></li>
                    <li class="list-group-item"><a href="/adminStudent?12A"> Class 12A</a></li>
                    <li class="list-group-item"><a href="/adminStudent?12B"> Class 12B</a></li>
                    <li class="list-group-item"><a href="/adminStudent?12C"> Class 12C</a></li>
                    <li class="list-group-item"><a href="/adminStudent?12D"> Class 12D</a></li>
                    <li class="list-group-item"><a href="/adminStudent?11A"> Class 11A</a></li>
                    <li class="list-group-item"><a href="/adminStudent?11B"> Class 11B</a></li>
                    <li class="list-group-item"><a href="/adminStudent?11C"> Class 11C</a></li>
                    <li class="list-group-item"><a href="/adminStudent?11D"> Class 11D</a></li>
                    <li class="list-group-item"><a href="/adminStudent?10A"> Class 10A</a></li>
                    <li class="list-group-item"><a href="/adminStudent?10B"> Class 10B</a></li>
                    <li class="list-group-item"><a href="/adminStudent?10C"> Class 10C</a></li>
                    <li class="list-group-item"><a href="/adminStudent?10D"> Class 10D</a></li>
                </ul>
            </div>
            <div class="card mt-4" style="width: 18rem;">
                <div class="card-header" style="background: darkcyan">
                    Genre
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a href="/admin?drama"> Drama</a></li>
                    <li class="list-group-item"><a href="/admin?humor"> Humor </a></li>
                    <li class="list-group-item"><a href="/admin?romance"> Romance</a></li>
                    <li class="list-group-item"><a href="/admin?mix"> Mix</a></li>
                    <li class="list-group-item"><a href="/admin?fantasy"> Fantasy</a></li>
                    <li class="list-group-item"><a href="/admin?essay"> Essay</a></li>
                    <li class="list-group-item"><a href="/admin?article"> Article</a></li>

                </ul>
            </div>
        </div>
        <div class="col-8 mt-4 pt-2">
            <form action="/adminSearchStudent" method="get">
                <div class="form-row">
                    <div class="form-group col col-8">
                        <label >Post name</label>
                        <input type="text" class="form-control" id="inputEmail5" name="name">
                    </div>
                    <div class="form-group col">
                        <label style="color: transparent">Find</label>
                        <button type="submit" class="btn btn-primary form-control">Find</button>
                    </div>
                </div>
            </form>
            <table class="table table-hover" >
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Student full name</th>
                    <th scope="col">Group class</th>
                    <th scope="col">Email</th>
                    <th>Student post</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Student> students = ( ArrayList<Student>) request.getAttribute("students");
                    for(Student p: students){
                %>
                <tr>
                    <th scope="row"><%=p.getId()%></th>
                    <td><%=p.getFull_name()%></td>
                    <td><%=p.getGroup()%></td>
                    <td><%=p.getEmail()%></td>
                    <td><a href="/detailStudent?student_id=<%=p.getId()%>" class="btn" style="background-color: transparent; border: 1px solid midnightblue; color: midnightblue; font-weight: bold;">student posts</a></td>


                </tr>
                <%}%>

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>

