<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:htmlEscape defaultHtmlEscape="true" />

<!DOCTYPE html>
<html>
<head>
    <title>Welcome to ROUX Hotel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value='/resources/images/misc/favicon.ico'/>" rel="icon" type="image/x-icon">
    <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet" media="screen">
    <link href="<c:url value='/resources/css/mystyles.css'/>" rel="stylesheet" media="screen">

</head>
<body id="home">
<section class="container">
    <div class="content row">
        <!-- Header -->

        <nav class="navbar navbar-inverse navbar-fixed-top" id="navigation">
            <div class="container-fluid">
                <div class="navbar-header" id="brand">
                    <a href="<c:url value="/"/>" class="navbar-brand">ROUX Hotel </a>
                </div>
                <form action="/signin" method="post" autocomplete="on">
                    <ul class="nav navbar-nav pull-right" style="padding: 10px;">
                        <li style="color:white;padding: 20px;"><strong><c:if test="${not empty message}">
                            ${message}
                        </c:if></strong></li>
                        <li>
                            <div class="form-group" style="padding: 3px;">
                                <input type="text" id="username" class="form-control" name="username" placeholder="Username">
                            </div>
                        </li>
                        &nbsp;
                        <li>
                            <div class="form-group" style="padding: 3px;">
                                <input type="password" id="password" class="form-control" name="password" placeholder="Password">
                            </div>
                        </li>
                        &nbsp;
                        <li style="padding-top: 9px;">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary">Sign In</button>
                        </li>
                    </ul>
                </form>
            </div>
        </nav>

        <!-- myCarousel -->
        <div id="myCarousel" class="carousel hidden-sm slide container">

            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
                <li data-target="#myCarousel" data-slide-to="4"></li>
            </ol><!-- carousel-indicators -->

            <section class="carousel-inner">
                <div class="carousel-caption">
                    <a href="<c:url value="/room"/>">
                        <button type="button" class="btn btn-lg btn-info btn-lg">BOOK A ROOM</button>
                    </a>
                </div>
                <div class="active item">
                    <img src="<c:url value='/resources/images/carousel/1.jpg'/>" alt="">

                </div>
                <div class="item"><img src="<c:url value='/resources/images/carousel/2.jpg'/>" alt=""></div>
                <div class="item"><img src="<c:url value='/resources/images/carousel/3.jpg'/>" alt=""></div>
                <div class="item"><img src="<c:url value='/resources/images/carousel/4.jpg'/>" alt=""></div>
                <div class="item"><img src="<c:url value='/resources/images/carousel/5.jpg'/>" alt=""></div>
            </section><!-- carousel-inner -->

            <a href="#myCarousel" class="left carousel-control" data-slide="prev"><span
                    class="glyphicon glyphicon-chevron-left"></span></a>
            <a href="#myCarousel" class="right carousel-control" data-slide="next"><span
                    class="glyphicon glyphicon-chevron-right"></span></a>
        </div><!-- myCarousel -->

        <section class="main col col-lg-12" id="offers">
            <h2 id="text">There's never been a better time to become a Member</h2>
            <div class="row">
                <section class="col col-lg-3">
                    <img src='/resources/images/offers/1.jpg' alt="">
                </section>
                <section class="col col-lg-3">
                    <img src='/resources/images/offers/2.jpg' alt="">
                </section>
                <section class="col col-lg-3">
                    <img src='/resources/images/offers/3.jpg' alt="">
                </section>
                <section class="col col-lg-3">
                    <img src='/resources/images/offers/4.png' alt="">
                </section>
            </div>
            <div class="row" id="buttons">
                <section class="col col-lg-6 text-right">
                    <a href="#">
                        <button type="button" class="btn btn-primary btn-lg">Sign In</button>
                    </a>
                </section>
                <section class="col col-lg-6">
                    <a href="<c:url value="/signup"/>">
                        <button type="button" class="btn btn-danger btn-lg">Register now</button>
                    </a>
                </section>
            </div>
        </section><!-- main -->
    </div><!-- content -->

    <!-- Footer -->
    &nbsp;
    <section class="container">
        <footer class="row">
            <nav class="col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="<c:url value="/"/>">About Us</a></li>
                    <li><a href="<c:url value="/"/>">Privacy Policy</a></li>
                    <li><a href="<c:url value="/"/>">Website</a></li>
                </ol>
            </nav>
        </footer>
    </section>
    <!-- Footer -->
</section><!-- container -->

<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script src="<c:url value='/resources/js/myscript.js'/>"></script>
</body>
</html>
