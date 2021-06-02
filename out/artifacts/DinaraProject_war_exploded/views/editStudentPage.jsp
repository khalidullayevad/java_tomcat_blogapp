
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
                <div class="card-footer">
                    Card footer
                </div>

            </div>
        </div>
        <div class="col-8">
            <div class="container pt-4" style="width: 520px;">
                <form action="/editStudent?editProfile=true" method="post">
                    <h3>Edit Profile </h3>

                    <%
                        String emailsuccess = request.getParameter("emailsuccess");
                        if (emailsuccess != null){
                    %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <strong>Successfull</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%}%>
                    <%
                        String emailrror = request.getParameter("emailerror");
                        if (emailrror != null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Unsuccessfull</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%}%>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" value="<%=currentUser.getEmail()%>">
                        <small id="emailHelp" class="form-text text-muted">Input your email.</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Full name</label>
                        <input type="text" class="form-control" id="exampleInputEmail2" aria-describedby="emailHelp" name="full_name" value="<%=currentUser.getFull_name()%>">
                        <small id="emailHelp2" class="form-text text-muted">Input your full name.</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Birthdate</label>
                        <input type="date" class="form-control" id="exampleInputEmail3" aria-describedby="emailHelp" name="birthdate" value="<%=currentUser.getBirthdate()%>">
                        <small id="emailHelp3" class="form-text text-muted">Input your birthdate</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Group</label>
                        <select class="form-control" name="group">
                            <option value="12A">Group is: 12A</option>
                            <option value="12B">Group is: 12B</option>
                            <option value="12C">Group is: 12C</option>
                            <option value="12D">Group is: 12A</option>
                        </select>
                    </div>
                    <button type="submit" class="btn mt-2" style="background-color: midnightblue; color: white; font-weight: bold">Edit</button>

                </form>
                <form action="/editStudent?updatePhoto=true" method="post">
                    <hr class="mt-2 mb-2">
                    <h3>Edit Picture  </h3>
                    <%
                        String photosuccess = request.getParameter("photosuccess");
                        if (photosuccess != null){
                    %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <strong>Successfull</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%}%>
                    <%
                        String potrror = request.getParameter("photoerror");
                        if (potrror != null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Unsuccessfull</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%}%>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Picture url</label>
                        <input type="text" class="form-control" id="exampleInputEmail4" aria-describedby="emailHelp" name="picture_url" value="<%=currentUser.getPicture_url()%>">
                        <small id="emailHelp4" class="form-text text-muted">Input your picture</small>
                    </div>
                    <button type="submit" class="btn mt-2" style="background-color: midnightblue; color: white; font-weight: bold">Edit</button>
                </form>
                <form action="/editStudent?updatePassword" method="post">

                    <hr class="mt-2 mb-2">
                    <h3>Update password </h3>
                    <%
                        String passsuccess = request.getParameter("passwordsuccess");
                        if (passsuccess != null){
                    %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <strong>Successfull</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%}%>
                    <%
                        String passerror = request.getParameter("passworderror");
                        if (passerror != null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Unsuccessfull</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%}%>
                    <div class="form-group">
                        <label for="exampleInputPassword1" > Old password</label>
                        <input type="password" class="form-control" id="exampleInputPassword3" name="old_password">
                        <small id="passwordHelp1" class="form-text text-muted">Input your old password.</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">New password</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" name="new_password">
                        <small id="passwordHelp" class="form-text text-muted">Input your new password.</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Re-password</label>
                        <input type="password" class="form-control" id="exampleInputPassword2"  name="re_password">
                        <small id="passwordHel2p" class="form-text text-muted">Re input your password.</small>
                    </div>
                    <button type="submit" class="btn mt-2" style="background-color: midnightblue; color: white; font-weight: bold">Edit</button>
                </form>
            </div>

        </div>
        <%@include file="templates/footer.jsp"%>

</body>
</html>
