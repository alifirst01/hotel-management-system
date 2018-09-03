<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet" media="screen">
    <link href="<c:url value='/resources/css/mystyles.css'/>" rel="stylesheet" media="screen">
    <script type="text/javascript" src="<c:url value='/resources/js/validate.js' />"></script>
</head>

<body>
<header>
    <nav class="navbar navbar-inverse navbar-fixed-top" id="navigation">
        <div class="container-fluid">
            <div class="navbar-header" id="brand">
                <a href="<c:url value="/"/>" class="navbar-brand">ROUX Hotel</a>
            </div>
            <ul class="nav navbar-nav pull-right" id="navform">
            </ul>
        </div>
    </nav>
</header>
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<div class="wrapper">
    <h1>Register For An Account</h1>
    &nbsp;

    <form:form action="/signup" method="post" modelAttribute="user" id="registration_form">
        <div>
            <div class="form-group">
                <div>
                    <form:input type="text" path="firstName" id="firstName" title="Alphabets Only"
                                placeholder="First Name" required="required"/>
                </div>
                <div class="has-error" style="color: white;">
                    <form:errors path="firstName" class="help-inline material-red-text "/>
                </div>
                </br>
                <div>
                    <form:input type="text" path="lastName" id="lastname" pattern="[A-Za-z]+" title="Alphabets Only"
                                placeholder="Last Name" required="required"/>
                </div>
                <div class="has-error" style="color: white;">
                    <form:errors path="lastName" class="help-inline material-red-text "/>
                </div>
                </br>
                <div>
                    <form:input type="text" pattern="[A-Za-z0-9]+" path="username" id="username"
                                placeholder="Username" required="required" onchange="checkusername()"/>
                    <label id="error" style="color: white; font-style: italic; font-weight: lighter;"></label>
                </div>
                <div class="has-error" style="color: white;">
                    <form:errors path="username" class="help-inline material-red-text "/>
                </div>

                </br>
                <div>
                    <form:input type="email" path="email" id="email" style="width: 410px; height: 55px"
                                placeholder="Email" required="required" onchange="checkemail()"/>
                    <label id="error2" style="color: white; font-style: italic; font-weight: lighter;"></label>
                </div>
                <div class="has-error" style="color: white;">
                    <form:errors path="email" class="help-inline material-red-text "/>
                </div>
                </br>
                <div>
                    <form:input type="number" path="contact" id="contact" pattern=".{7,11}" placeholder="Contact Number"
                                required=""/>
                </div>
                <div class="has-error" style="color: white;">
                    <form:errors path="contact" class="help-inline material-red-text "/>
                </div>
                </br>
                <div>
                    <form:input type="password" pattern=".{8,20}" path="password" id="password"
                                placeholder="Password" required="required"/>
                </div>
                </br>
                </br>
                <div>
                    <form:select path="country" required="required">
                        <form:option value="">Select Country</form:option>
                        <form:option value="Afghanistan">Afghanistan</form:option>
                        <form:option value="America">America</form:option>
                        <form:option value="Algeria">Canada</form:option>
                        <form:option value="American Samoa">Saudi Arabia</form:option>
                        <form:option value="India">India</form:option>
                        <form:option value="Angola">Malasia</form:option>
                        <form:option value="Australia">Australia</form:option>
                        <form:option value="Antartica">New Zealand</form:option>
                        <form:option value="Antigua and Barbuda">South Africa</form:option>
                        <form:option value="Argentina">Argentina</form:option>
                        <form:option value="Armenia">Armenia</form:option>
                        <form:option value="Aruba">Aruba</form:option>
                        <form:option value="Pakistan">Pakistan</form:option>
                        <form:option value="Austria">Austria</form:option>
                        <form:option value="Azerbaijan">Azerbaijan</form:option>
                        <form:option value="Bahamas">Bahamas</form:option>
                    </form:select>
                </div>
                </br>
                <div>
                    <form:input type="number" path="zipCode" id="zipCode" placeholder="Zip Code"
                                required="required"/>
                </div>
                </br>
                </br>
                <strong ><div id = "message" style="color:red;padding: 20px;">
                </div></strong>
                <div style="text-align: center">
                    <input class="btn btn-primary waves-effect waves-light" style="width: 420px; height: 60px"
                           type="submit" value="Sign up"/>
                </div>
                </br>
            </div>
        </div>
    </form:form>
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
</div>
</body>
</html>

