<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="content row">
    <section class="main col col-lg-12" id="offers">
            <div id="bookbtn" style="padding-left: 40px;">
                <button class = "btn btn-primary" data-toggle="modal" data-target="#myModal"><h3>Search Room</h3></button>
            </div>

            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">x</button>
                            <h4 class="modal-title">Search Room</h4>
                        </div>
                        <div class="modal-body">
                            When would you like to stay at ROUX Hotel?<hr/>
                            <br/>
                            <form action="/search", method="post">
                                <label>Check In Date:</label><input required="" type="date" name="cinday">
                                <label>Check Out Date:</label><input required="" type="date" name="coutday">
                                <label style="color:white;">Type:</label>
                                <select name="type" required="">
                                    <option value="" disabled selected>Select Room Type</option>
                                    <option value="Single">Single</option>
                                    <option value="Double">Double</option>
                                    <option value="Suite">Suite</option>
                                </select>
                                <hr/>
                                <button type="submit" class="btn btn-primary">Search</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
            <strong style="color:red;padding: 20px; margin-left: 495px;"><c:if test="${not empty panel}">
                ${panel}
            </c:if></strong>
            &nbsp;&nbsp;
            <h3>Popular Searches</h3>
            <div class="row">
                <section class="col col-lg-3">
                    <a href=""data-toggle="modal" data-target="#myModal"><img class="img-rounded"  src="<c:url value='/resources/images/Rooms/7.jpg'/>"></a>
                </section>
                <section class="col col-lg-9">
                    <h4>Ratings: 4.5</h4>
                    <h3>Room 10 - 2 Person Designed</h3>
                    <h4>Elizabeth Hall</h4>
                    <p>Located in Elizabeth Hall. This room featured in top reviews of users. It presents a beatiful view of the whole city. </p>
                </section>
            </div>
            <br/>
            <div class="row">
                <section class="col col-lg-3">
                    <a href="" data-toggle="modal" data-target="#myModal"><img class="img-rounded" src="<c:url value='/resources/images/Rooms/1.jpg'/>"> </a>
                </section>
                <section class="col col-lg-9">
                    <h4>Ratings: 5</h4>
                    <h3>Room 1 - Modren Designed Art</h3>
                    <h4>1st Floor</h4>
                    <p>Best Room Award. Equived with modren accessories with stunning view of the Deserts</p>
                </section>
            </div>
            <br/>
            <div class="row">
                <section class="col col-lg-3">
                    <a href=""data-toggle="modal" data-target="#myModal"><img class="img-rounded" src="<c:url value='/resources/images/Rooms/2.jpg' />"></a>
                </section>
                <section class="col col-lg-9">
                    <h4>Ratings: 5</h4>
                    <h3>Room 12 - 2 Person Designed</h3>
                    <h4>Elizabeth Hall</h4>
                    <p>Located in Elizabeth Hall. This room offer best view of mountains. Featured in top user reviews. </p>
                </section>
            </div>
            <br/>
            <div class="row">
                <section class="col col-lg-3">
                    <a href=""data-toggle="modal" data-target="#myModal"><img class="img-rounded" src="<c:url value='/resources/images/Rooms/3.jpg'/>"></a>
                </section>
                <section class="col col-lg-9">
                    <h4>Ratings: 4</h4>
                    <h3>Room 14 - 2 or more Person Designed</h3>
                    <h4>2nd Floor</h4>
                    <p>Modren designed with furnished floor. Equiped with accessories to assist living. Located adjacent to the sea which presents a stunning view.</p>
                </section>
            </div>
            <br/>
            <div class="row">
                <section class="col col-lg-3">
                    <a href=""data-toggle="modal" data-target="#myModal"><img class="img-rounded" src="<c:url value='/resources/images/Rooms/4.jpg'/>"></a>
                </section>
                <section class="col col-lg-9">
                    <h4>Ratings: 4.5</h4>
                    <h3>Room 20 - 2 Person Designed</h3>
                    <h4>2nd Floor</h4>
                    <p>Top Room. Full accessories to assist living. Located on top floor, the room opens to sunshine and dawn of whole city.</p>
                </section>
            </div>
            <br/>
            <div class="row">
                <section class="col col-lg-3">
                    <a href=""data-toggle="modal" data-target="#myModal"><img class="img-rounded" src="<c:url value='/resources/images/Rooms/5.jpg'/>"></a>
                </section>
                <section class="col col-lg-9">
                    <h4>Ratings: 4.5</h4>
                    <h3>Room 10 - 2 Person Designed</h3>
                    <h4>Elizabeth Hall</h4>
                    <p>Located in Elizabeth Hall. This room featured in top reviews of users. It presents a beatiful view of the whole city. </p>
                </section>
            </div>
            <br/>
            <div class="row">
                <section class="col col-lg-3">
                    <a href=""data-toggle="modal" data-target="#myModal"><img class="img-rounded" src="<c:url value='/resources/images/Rooms/6.jpg'/>"></a>
                </section>
                <section class="col col-lg-9">
                    <h4>Ratings: 4</h4>
                    <h3>Room Suite Designed</h3>
                    <h4>2nd Floor</h4>
                    <p>Modren designed with furnished floor. Equiped with accessories to assist living. </p>
                </section>
            </div>


        </section><!-- main -->
    </div><!-- content -->

    <!-- Footer -->
    &nbsp;
    <section class = "container">
        <footer class = "row"  >
            <nav class = "col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="index.php">About Us</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href = "index.php">Website</a></li>
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
