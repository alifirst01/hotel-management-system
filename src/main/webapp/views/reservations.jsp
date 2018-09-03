<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to ROUX Hotel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet" media="screen">
    <link href="<c:url value='/resources/css/mystyles.css'/>" rel="stylesheet" media="screen">

</head>
<body id="home">
<section class="container">
    <div class="content row">
        <!-- Header -->
        <header>
            <nav class="navbar navbar-inverse navbar-fixed-top" id="navigation">
                <div class="container-fluid">
                    <div class="navbar-header" id="brand">
                        <a class="navbar-brand" href="<c:url value="/"/>">ROUX Hotel</a>
                    </div>
                    <ul class="nav navbar-nav pull-right"]>
                        <a href="<c:url value="/profile"/>" style="text-decoration: none"><li style="display: inline-block"><h4 style="color:white;"><strong>${name}</strong> &emsp;&emsp;&emsp;</h4></li></a>
                        <a href="<c:url value="/signout"/>" style="text-decoration: none"><li style="display: inline-block"><h4 style="color:white;">Sign Out</h4></li></a>
                    </ul>
                </div>
            </nav>
            <nav class="navbar navbar-default navbar-inverse" style="padding-top: 60px;background-color: #5e5e5e">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <ul style="list-style-type: none;font-size: 25px;">
                        <a href="<c:url value="/profile"/>" style="text-decoration: none;"><li style="display: inline; color:white;padding-left: 200px;">Profile</li></a>
                        <a href="<c:url value="/reservations"/>" style="text-decoration: none;"><li style="display: inline; color:white;padding-left: 200px;">Reservations</li></a>
                        <a href="<c:url value="/room"/>" style="text-decoration: none;"><li style="display: inline; color:white;padding-left: 200px;">Book A Room</li></a>
                    </ul>
                </div><!-- /.container -->
            </nav><!-- /.navbar -->
        </header>
        <!-- Header -->
        <div class="container" style="margin-top: 10px;">
            <a href="<c:url value="/getAllReservations"/>"><button class = "btn btn-info"><h5>View All Reservations</h5></button></a>
        </div>
        &nbsp;
        &nbsp;
        <div class="panel-group" id="queues-accordion">
            <c:if test="${not empty panel}">
                ${panel}
            </c:if>
        </div>
    </div>
</section>
<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script src="<c:url value='/resources/js/service.js'/>"></script>
</body>
</html>



