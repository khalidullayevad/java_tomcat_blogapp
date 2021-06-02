<%@ page import="db.Student" %>
<%@ page import="db.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="templates/head.jsp"%>
    <style><%@include file="templates/css/main.css"%></style>
    <title>Student Page</title>
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
            </div>
        </div>
        <div class="col-8">
            <form class="form-inline my-2 my-lg-0" action="/search" method="get">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" name="search_text" aria-label="Search">
                <button class="btn btn-outline-info mr-2" type="submit">Search</button>
            </form>

            <button type="button" class="btn mt-4 mb-4" style="background-color: midnightblue; color: white; font-weight: bold"data-toggle="modal" data-target="#addModal" style="background-color: blue; float: right">
                +ADD
            </button>

            <%--            Modal--%>
            <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="/addPost" method="post">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Add New Post</h5>
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
                                    <label for="exampleFormControlTextarea1">Content</label>
                                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="4" name="content"></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1">Category</label>
                                    <select class="form-control" name="category">
                                        <option value="Essay">Essay</option>
                                        <option value="Romance">Romance</option>
                                        <option value="Fantasy">Fantasy</option>
                                        <option value="Drama">Drama</option>
                                        <option value="Humor">Humor</option>
                                        <option value="Mix">Mix</option>
                                    </select>
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
                ArrayList<Post> posts = ( ArrayList<Post>) request.getAttribute("posts");
                for(Post p: posts){
            %>
            <div class="card ">
                <div class="card-body mt-4">
                    <h5 class="card-title"><%=p.getTitle()%></h5>
                    <a href="/detail?post_id=<%=p.getId()%>" class="btn" style="background-color: transparent; border: 1px solid midnightblue; color: midnightblue; font-weight: bold;">More</a>
                    <% if (p.isChecked() == false && p.getAuthor().getId() == currentUser.getId()){%>
                    <button class="btn btn-primary" data-toggle="modal" data-target="#edit_modal<%=p.getId()%>">EDIT</button>
                    <div class="modal fade" id="edit_modal<%=p.getId()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">EDIT</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="/editPost?post_id=<%=p.getId()%>" method="post">
                                    <div class="container">
                                        <div class="form-group">
                                            <label>Title : </label>
                                            <input type="text" class="form-control" value="<%=p.getTitle()%>" name = "title">
                                        </div>
                                        <div class="form-group">
                                            <label>Content : </label>
                                            <textarea class="form-control" rows="5" name = "content"><%=p.getContent()%></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleFormControlTextarea1">Category</label>
                                            <select class="form-control" name="category">
                                                <option value="Essay">Essay</option>
                                                <option value="Romance">Romance</option>
                                                <option value="Fantasy">Fantasy</option>
                                                <option value="Drama">Drama</option>
                                                <option value="Humor">Humor</option>
                                                <option value="Mix">Mix</option>
                                            </select>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <%}%>
                </div>
                <div class="card-footer text-muted">
                    Posted:  <%=p.getPost_date()%> by <a href="#" style="text-decoration: none; color: midnightblue; font-weight: bold;"><%=p.getAuthor().getFull_name()%></a> <br>
                    <% if (p.isChecked() == true && p.getAuthor().getId() == currentUser.getId()){%>
                    Point: <%=p.getPoint()%> <br>
                    Comment: <%=p.getTeachersComment()%>
                    <%}%>
                </div>
            </div>
            <br>
            <%
                }
            %>
        </div>
</div>


<%@include file="templates/footer.jsp"%>
</body>
</html>
