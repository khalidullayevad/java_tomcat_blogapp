<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <%@include file="templates/head.jsp"%>
    <style><%@include file="templates/css/main.css"%></style>
</head>
<body>
<%@include file="templates/navbar.jsp"%>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://sun9-38.userapi.com/wsDj5oW7AjQiV0L_mYtJiK0yNso0jw-glkZSYA/a_Xb6wHU_rE.jpg" class="d-block w-100" alt="">
            </div>
            <div class="carousel-item">
                <img src="https://sun9-12.userapi.com/Ej_VrHblU9dG4uNQbV0Jn8vhyAIYv-g5KmOOdQ/dB4KZn5W_rQ.jpg" class="d-block w-100" alt="">
            </div>
            <div class="carousel-item">
                <img src="https://sun9-20.userapi.com/K8pv0QMDcNZrU1NSrEtz0xGQPluRvGpy-ojXIQ/38TzprMeWRA.jpg" class="d-block w-100" alt="">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <div id="lg" class="pb-4">
        <h2 class="mb-4"><center>Select a Genre </center></h2>
        <div class="container mb-4 pb-4">
            <div class="row justify-content-md-center">
                 <div class="tilt2">
                    <div class="col-lg-2 col-lg-offset-1">
                        <a href="/filter?fantasy=true"><img src="https://sun9-51.userapi.com/H5ByiNWsk9MHacDr9M8hysDmjk_lSA7UCDBA5g/qan_KDHfJH8.jpg" width="180" height="180" alt="">  </a>
                    </div>
                </div>
                <div class="tilt2">
                    <div class="col-lg-2">
                        <a href="/filter?romance=true"><img src="https://sun9-33.userapi.com/1XxZnmzSb7vFqBRnjJCucxgMQkWPlfFSWgIaaw/aTeslun2_v8.jpg" width="180" height="180" alt="">  </a>
                    </div>
                </div>
                <div class="tilt2">
                    <div class="col-lg-2">
                        <a href="/filter?romance=true"><img src="https://sun9-14.userapi.com/qZePwLRSoqVwcCJADsxdiMR-uWnVPHVBd1AiMA/8hf93rSWeGc.jpg" width="180" height="180" alt="">  </a>
                    </div>
                </div>
                <div class="tilt2">
                    <div class="col-lg-2">
                        <a href="/filter?mix=true"><img src="https://sun9-42.userapi.com/HyxgIqf-7EJoaiJ4HJY6wH9CVDKcJv4GTKWt4g/JFtRPk190OU.jpg"width="180" height="180" alt="">  </a>
                    </div>
                </div>
                <div class="tilt2">
                    <div class="col-lg-2">
                        <a href="/filter?humor=true"><img src="https://sun9-62.userapi.com/LXSNGAuRZCGrRdx9D3zCa2z47cVHptYqLllMow/ttuiJaFxDH8.jpg"width="180" height="180" alt="">  </a>
                    </div>
                </div>
            </div>
        </div>


    </div>

    <div class="container w">
        <div class="row centred mb-4">
                <p><a name="about"></a>
                    <div class="col-lg-4">
                        <i class="fa fa-heart"></i>
                        <h4>About Project </h4>
                <p>All operations can be easily performed in the system, using the
            Internet and a web browser. These system requirements are currently
                  available to all PC users, making the website open to everyone.

        </p>
            </div>
            <div class="col-lg-4">
                <i class="fa fa-laptop"></i>
                <h4>About Project Shabyt</h4>
                <p>The goal of our project is to improve the writing skills of students
                    and to improve the distribution of stories and essays in the Kazakh
                                             language.
                </p>
            </div>
            <div class="col-lg-4">
                <i class="fa fa-trophy"></i>
                <h4>Current problem</h4>
                <p>For many students, it is difficult to write stories about a specific
                    topic. They suffer especially when teachers require them to write
                                       stories or essays.
                </p>
            </div>
        </div>
    </div>
    <%@include file="templates/footer.jsp"%>
</body>
</html>

