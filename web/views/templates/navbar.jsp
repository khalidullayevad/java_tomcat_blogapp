<%@ page import="db.Student" %>
<nav class="navbar navbar-expand-md navbar-inverse" style="background-color: white;">
    <a class="navbar-brand" href="#"><img src="https://sun9-66.userapi.com/v5mDCnMPipx8FkzmNnlcd_s8LGQiA-YlBxOBNw/KUAtvrGyjJU.jpg" alt="" width="220" height="90"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#about">About us</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Story
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/filter?romance=true">Romance</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/filter?fantasy=true">Fantasy</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/filter?essay=true">Essay</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/filter?article=true">Article</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/filter?drama=true">Drama</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/filter?humor=true">Humor</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/filter?mix=true">Mix</a>
                </div>
            </li>
        </ul>

            <%
                Student currentUser =(Student) request.getSession().getAttribute("CURRENT_USER");
                if (currentUser != null){
            %>
        <form class="form-inline my-2 my-lg-0">
            <a href="/studentPage?allPosts=true"  role="button"><%=currentUser.getFull_name()%></a>
        </form>
        <%} else if (currentUser == null){
        %>
        <form class="form-inline my-2 my-lg-0">
            <a href="/signIn" class="btn btn-outline-info" role="button">Sign in</a>
        </form>
        <%}%>

    </div>
</nav>
