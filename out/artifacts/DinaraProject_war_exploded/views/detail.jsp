
<%@ page import="db.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
    <%@include file="templates/head.jsp"%>
    <script src="templates\tinymce_latest_custom\tinymce\tinymce.min.js"></script>
    <script>tinymce.init({selector:'textarea'});</script>
</head>
<body>
<%@include file="templates/navbar.jsp"%>

<div class="container">
    <div class="row">
        <div class="col">
            <div class="image mt-4" style="width: 15rem;">
                <img src="<%=currentUser.getPicture_url()%>"  class="img-thumbnail rounded float-left">
            </div>

            <div class="card" style="width: 15rem;">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><%=currentUser.getFull_name()%>, <%=currentUser.getAge()%></li>
                    <li class="list-group-item"><a href="/editStudent" style="text-decoration: none; color: midnightblue; font-weight: bold">Edit page</a></li>
                    <li class="list-group-item"><a href="/studentPage?allPosts=true" style="text-decoration: none; color: black; font-weight: bold">All posts</a></li>
                    <li class="list-group-item"><a href="/studentPage?myAllPosts=true" style="text-decoration: none; color: black; font-weight: bold">My all posts</a></li>
                    <li class="list-group-item"><a href="/studentPage?verifiedPosts=true" style="text-decoration: none; color: black; font-weight: bold">My verified posts</a></li>
                    <li class="list-group-item"><a href="/studentPage?unverifiedPosts=true" style="text-decoration: none; color: black; font-weight: bold">My unverified posts</a></li>
                    <li class="list-group-item"><a href="/logout" style="text-decoration: none; color: darkred; font-weight: bold">Logout</a></li>
                </ul>
                <div class="card-footer">
                    Card footer
                </div>

            </div>
        </div>
        <div class="col-8">

            <button type="button" class="btn mt-4 mb-4" style="background-color: midnightblue; color: white; font-weight: bold"data-toggle="modal" data-target="#addModal" style="background-color: blue; float: right">
                +ADD
            </button>

            <%--            Modal--%>
            <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="/addPost" method="post">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Add News</h5>
                                <button class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroup-sizing-default 1">Title</span>
                                    </div>
                                    <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="title">
                                </div>
                                <div class="form-group">
                                    <label >Short content</label>
                                    <textarea class="form-control" name="short_content"></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1">Content</label>
                                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="4" name="content"></textarea>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                <button  class="btn btn-primary">Add</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%--            --------------END MODAL------------------%>

            <%
                Post p = (Post) request.getAttribute("post");

            %>
            <div class="card ">
                <div class="card-body mt-4">
                    <h5 class="card-title"><%=p.getTitle()%></h5>
                    <p class="card-text"><%=p.getContent()%></p>

                </div>
                <div class="card-footer text-muted">
                    Posted on <%=p.getPost_date()%> <a href="#" style="text-decoration: none; color: midnightblue; font-weight: bold;"><%=p.getAuthor().getFull_name()%></a>
                </div>
            </div>


        </div>

    </div>
</div>
</body>
</html>
