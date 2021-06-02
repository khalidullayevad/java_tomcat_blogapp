<%@ page import="db.Post" %>
<%@ page import="java.util.ArrayList" %>
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
            <form action="/adminSearchPost" method="get">
                <div class="form-row">
                    <div class="form-group col">
                        <label >Post name</label>
                        <input type="text" class="form-control" id="inputEmail5" name="title">
                    </div>
                    <div class="form-group col">
                        <label >Post Category</label>
                        <select class="form-control" name="category">
                            <option value="All">All category</option>
                            <option value="Essay">Essay</option>
                            <option value="Romance">Romance</option>
                            <option value="Fantasy">Fantasy</option>
                            <option value="Drama">Drama</option>
                            <option value="Humor">Humor</option>
                            <option value="Mix">Mix</option>
                        </select>
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
                    <th scope="col">Post Title</th>
                    <th scope="col">Author</th>
                    <th scope="col">Date</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Post> posts = ( ArrayList<Post>) request.getAttribute("posts");
                    for(Post p: posts){
                %>
                <tr>
                    <th scope="row"><%=p.getId()%></th>
                    <td><%=p.getTitle()%></td>
                    <td><%=p.getAuthor().getFull_name()%></td>
                    <td><%=p.getPost_date()%></td>
                    <td>
                     <% if (p.isChecked()==false){%>
                        <button class="btn btn-primary" data-toggle="modal" data-target="#edit_modal<%=p.getId()%>">GET POINT</button>
                        <%}
                         else if (p.isChecked()==true){%>
                        <button class="btn btn-primary" data-toggle="modal" data-target="#edit_modal<%=p.getId()%>">EDIT POINT</button>
                        <%}%>
                        <div class="modal fade" id="edit_modal<%=p.getId()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">EDIT</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="/editAdminPost?post_id=<%=p.getId()%>" method="post">
                                        <div class="container">
                                            <div class="form-group">
                                                <label>Title : </label>
                                                <p><%=p.getTitle()%></p>
                                            </div>
                                            <div class="form-group">
                                                <label>Content : </label>
                                                <p><%=p.getContent()%></p>
                                            </div>
                                            <div class="form-group">
                                                <label >Category</label>
                                               <p><%=p.getCategory()%></p>
                                            </div>

                                            <div class="form-group">
                                                <label>Point : </label>
                                                <input type="number" class="form-control" value="<%=p.getPoint()%>" name = "point" min="2" max="5">
                                            </div>

                                            <div class="form-group">
                                                <label>Commentary : </label>
                                                <input type="text" class="form-control" value="<%=p.getTeachersComment()%>" name = "comment" >
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

                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
