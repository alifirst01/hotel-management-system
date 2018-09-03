<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Access Denied</title>
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
                        <c:if test="${not empty panel}">
                            <a href="<c:url value="/profile"/>" style="text-decoration: none"><li style="display: inline-block"><h4 style="color:white;"><strong>${name}</strong> &emsp;&emsp;&emsp;</h4></li></a>
                            <a href="<c:url value="/signout"/>" style="text-decoration: none"><li style="display: inline-block"><h4 style="color:white;">Sign Out</h4></li></a>
                        </c:if>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- Header -->
<div class="container" style="margin-top: 100px;">
    <h2 style="color: white;">Access Denied</h2>
</div>
</body>
</html>