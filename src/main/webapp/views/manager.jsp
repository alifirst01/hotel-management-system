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
        </header>
        <!-- Header -->
        <section class="container">
            <h1 id="text">Welcome</h1>
            <hr/>
            <div class="row" id="buttons">
                <section class="col col-lg-6">
                    <a href="/getpendingrooms"><button type = "button" class="btn btn-primary btn-lg">View All Pending Rooms</button></a>
                </section>
            </div>
            <br>
            <div class="panel-group" id="queues-accordion">
                <c:if test="${not empty RPanel}">
                    ${RPanel}
                </c:if>
            </div>
        </section>

    </div>
</section>

<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script src="<c:url value='/resources/js/myscript.js'/>"></script>
</body>
</html>

