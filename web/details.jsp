<%-- 
    Document   : details
    Created on : Jun 4, 2023, 3:54:05 PM
    Author     : haong
--%>
<style></style>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <style>
        /*edit link color*/
        .title a:visited {
            color: #333333;
        }
        .title a:hover{
            color: #7AB730;
        }
        /*edit tilte position*/
        .title a>div{
            padding-left: 26px;
        }
        .title div{
            margin-top: 7px;
        }
        .content{
            margin-top: 60px;
        }
        /*dropdown hover*/
        .dropdown:hover>.dropdown-menu {
            display: block;
        }
        .dropdown>.nav-link:active {
            pointer-events: none;
        }
        .dropdown-menu {
            right: 0;
        }
    </style>
    <head>
        <meta charset="utf-8">
        <title>Bird Meal Order System</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <!-- Favicon -->
        <link href="img/icon.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&family=Roboto:wght@700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">


        <!--sweetalert2--> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>

    <body>
        <!-- Topbar Start -->
        <%@include file="header.jsp" %>
        <!-- Navbar End -->
        <c:set var="user" value="${sessionScope.user}"/>
        <c:if test="${not empty user}">


            <div class="container content">

                <div class="row ">
                    <div class="title col-md-3">
                        <div class="" style="width:100% auto; color: black; font-weight: bold;">
                            <div class="">My account</div>
                        </div>
                        <div>
                            <div>
                                <div class="">
                                    <div style=""> <a class=""" aria-current="page" href="details.jsp">
                                            <div class=""  style="color: #7AB730" >Profile</div>
                                        </a></div>
                                </div>
                            </div>
                            <div>
                                <div class="">
                                    <div><a class="" href="MainController?btAction=Purchase">
                                            <div class="" >Purchase</div>
                                        </a></div>
                                </div>
                            </div>
                        </div>
                        <div class="" style="width: auto;  color: black; font-weight: bold;">
                            <div class="">Profile settings</div>
                        </div>
                        <div>
                            <div>
                                <div class=""><a class="" href="editInformation.jsp">
                                        <div class=""> Edit profile</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="addresses.jsp">
                                        <div class="">Address book</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="til" href="editPassword.jsp">
                                        <div class="">Change my password</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="removeAccount.jsp">
                                        <div class="">Remove account</div>
                                    </a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="fr-flbox middle bg-white" style="border: 1px solid rgb(224, 224, 224); padding: 28px 20px; box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%)">
                            <div>
                                <div class="">
                                    <h3 id="" class=""><span class="">Profile</span>
                                    </h3>
                                </div>
                                <div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="">
                                                <div class="">
                                                    <h4 id="" class=""><span class="">Email address</span>
                                                    </h4>
                                                    <span class="" >${user.email}</span>
                                                </div>
                                            </div>
                                            <div class="">
                                                <div class="">
                                                    <h4 id="" class=""><span class="">Name</span></h4>
                                                    <span class="">${user.fullName}</span>
                                                </div>
                                            </div>
                                            <div class="">
                                                <div class="">
                                                    <h4 id="" class=""><span class="">Address</span></h4>
                                                    <span class="">${user.address}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="">
                                                <div class="">
                                                    <h4 id="" class=""><span class="">Mobile phone</span></h4>
                                                    <span class="">${user.phoneNumber}</span>
                                                </div>
                                            </div>
                                            <!--                                    <div class="">
                                                                                    <div class="">
                                                                                        <h4 id="" class=""><span class="">Birthday</span></h4>
                                                                                        <span class="">13/03/2002</span>
                                                                                    </div>
                                                                                </div>-->
                                            <div class="s">
                                                <div class="">
                                                    <h4 id="" class=""><span class="">Gender</span></h4>
                                                    <c:if test="${user.gender == true}">
                                                        <span class="">Female</span>
                                                    </c:if>
                                                    <c:if test="${user.gender == false}">
                                                        <span class="">Male</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <!-- 
        <c:if test="${empty user}">
            <div>please login.</div>
        </c:if>
        -->


        <script>
            //check session user
            if (${empty sessionScope.user}) {
                swal.fire({
                    icon: 'warning',
                    title: "Warning!",
                    text: "Your need to login",
                    confirmButtonText: 'Click to login'
                }).then(function () {
                    window.location = "login.jsp";
                });
            }
        </script>


        <!-- JavaScript Libraries -->

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

    </body>

</html>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>