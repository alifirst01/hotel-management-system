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
        <div class="container">
            <div id="bookbtn">
                <button class="btn btn-primary" data-toggle="modal" data-target="#myModal"
                        style="padding-top: 50px; margin-left: 70px;"><h3>Add a Room</h3></button>
            </div>
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header" style=" color: white;">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add Room</h4>
                        </div>
                        <div class="modal-body">
                            <form action="/addRoom" method="post" autocomplete="on" enctype="multipart/form-data">
                                <label style="color:white;">Type:</label>
                                <select name="type" required="">
                                    <option value="" disabled selected>Select Room Type</option>
                                    <option value="Single">Single</option>
                                    <option value="Double">Double</option>
                                    <option value="Suite">Suite</option>
                                </select>
                                <label style="color:white;">Price: $<input required="" type="text" name="price"></label>

                                <div style="color: white;">
                                    <label for="pictures" data-icon="e">Select Images</label> <br>
                                    <input id="pictures" type="file" name="pictures" size="25" multiple/>
                                </div>

                                <br/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button id="configRoom" type="submit" class="btn btn-primary">Confirm</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
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

