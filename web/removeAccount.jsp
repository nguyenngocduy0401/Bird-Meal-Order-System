<%-- 
    Document   : removeAccount
    Created on : Jun 15, 2023, 10:33:06 AM
    Author     : haong
--%>
<style></style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        .card {
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
        }
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
        <link href="img/favicon.ico" rel="icon">

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
        <c:if test="${not empty sessionScope.user}">

            <div class="container content">
                <div class="row ">
                    <div class="title col-md-3">
                        <div class="" style="width:100% auto; color: black; font-weight: bold;">
                            <div class="">My account</div>
                        </div>
                        <div>
                            <div>
                                <div class="">
                                    <div><a class="" aria-current="page" href="details.jsp">
                                            <div class="">Profile</div>
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
                        <div class="" style="width: auto; color: black; font-weight: bold;">
                            <div class="">Profile settings</div>
                        </div>
                        <div>
                            <div>
                                <div class=""><a class="" href="editInformation.jsp">
                                        <div class="">Edit profile</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="addresses.jsp">
                                        <div class="">Address book</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="editPassword.jsp">
                                        <div class="">Change my password</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="removeAccount.jsp">
                                        <div class="" style="color: #7AB730">Remove account</div>
                                    </a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="card mb-4">
                            <div class="card-header">Delete Account</div>
                            <div class="card-body">
                                <p>We are sad that you want to leave us, but please note that removing your account is a permanent action and cannot be undone,
                                    and you will lose access to your purchase history service. If you are sure you want to remove your account, select the button below.</p>
                                <button onclick="removeFunction()" class="btn btn-outline-danger" type="button">I understand, remove my account</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <script>
            //remove confirm
            function removeFunction() {
                Swal.fire({
                    title: 'Are you sure?',
                    text: "You won't be able to revert this!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#d33',
                    cancelButtonColor: '#3085d6',
                    confirmButtonText: 'Yes, delete it!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        document.location = "removeAccountServlet";
                    }
                })
            }

            //Check session user
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