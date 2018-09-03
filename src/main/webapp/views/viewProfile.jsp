<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet" media="screen">
    <link href="<c:url value='/resources/css/mystyles.css'/>" rel="stylesheet" media="screen">
</head>
<body>
<header>
    <nav class="navbar navbar-inverse navbar-fixed-top" id="navigation">
        <div class="container-fluid">
            <div class="navbar-header" id="brand">
                <a class="navbar-brand" href="<c:url value="/"/>">ROUX Hotel</a>
            </div>
            <ul class="nav navbar-nav pull-right" ]>
                <a href="<c:url value="/profile"/>" style="text-decoration: none">
                    <li style="display: inline-block"><h4 style="color:white;"><strong>${name}</strong> &emsp;&emsp;&emsp;
                    </h4></li>
                </a>
                <a href="<c:url value="/signout"/>" style="text-decoration: none">
                    <li style="display: inline-block"><h4 style="color:white;">Sign Out</h4></li>
                </a>
            </ul>
        </div>
    </nav>
    <c:if test="${type != 3}">
        <br>
        <br>
        <br>
        <br>
    </c:if>
    <c:if test="${type == 3}">
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
    </c:if>
</header>
<div class="container">
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h4 style="color:white;" class="modal-title">Update Profile</h4>
                </div>
                <div class="modal-body" style="color:white;">
                    <br/>
                    <form action="/updateProfile", method="post" style="color:white;">
                        <input type="text" name ="firstName" pattern="[A-Za-z]+" title="Alphabets Only"  placeholder=${fname} />
                        <input type="text" name ="lastName" pattern="[A-Za-z]+" title="Alphabets Only"  placeholder=${lname} />
                        <input type="text" name ="username" pattern="[A-Za-z]+[^0-9]+" placeholder=${username} />
                        <input type="email" name ="email" placeholder=${email} />
                        <input type="number" name ="contact" pattern=".{7,11}" placeholder=${contact} />
                        <input type="text" name ="country" pattern="[A-Za-z]+" title="Alphabets Only"  placeholder=${country} />
                        <input type="number" name="zipCode" placeholder=${zipcode} />
                        <input type="password" pattern=".{8,20}" name="password" placeholder="New Password"/>
                        <hr/>
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>

        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${name}</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-lg-3 " align="center" style="float:right;"><img alt="User Pic"
                                                                    src="http://wiki.dreamwidth.net/wiki/images/3/3f/Userhead-100x100-unfaded.png"
                                                                    class="img-circle img-responsive"></div>

                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>
                        <tr>
                            <td>ID:</td>
                            <td>${userID}</td>
                        </tr>
                        <tr>
                            <td>Username:</td>
                            <td>${username}</td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><a href="mailto:info@support.com">${email}</a></td>
                        </tr>

                        <tr>
                        <tr>
                            <td>Contact:</td>
                            <td>${contact}</td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td>${country}</td>
                        </tr>
                        <tr>
                            <td>Zip Code:</td>
                            <td>${zipcode}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="panel-footer" style="min-height: 55px;">
            <c:if test="${not empty message}">
                ${message}
            </c:if>
            <button class="btn btn-primary"style="float:right;" data-toggle="modal" data-target="#myModal">Update Profile</button>
        </div>

    </div>
</div>
<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script src="<c:url value='/resources/js/myscript.js'/>"></script>
</body>
</html>
