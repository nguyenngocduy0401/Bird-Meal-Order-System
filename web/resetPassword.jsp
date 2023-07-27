<%-- 
    Document   : resetPassword
    Created on : Jul 26, 2023, 9:39:12 PM
    Author     : haong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bird Meal Order System</title>
        <meta charset="UTF-8">
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
        <style>
            .password{
                align-items: center;
            }
            .form-label{
                font-weight: 700;
            }
        </style>
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid border-bottom d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-3 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Our Address</h6>
                            <span>Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-center border-start border-end py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-envelope-open fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Email Us</h6>

                            <span>fpt@example.com</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-phone-vibrate fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Call Us</h6>
                            <span>+123454654</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-facebook fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Facebook</h6>
                            <span><a href="https://www.facebook.com/" class="link">https://www.facebook.com/</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="MainController?btAction=Home" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <div class="col-md-7 container-fluid">


                </div>
                <div class="navbar-nav ms-auto py-0">
                    <a href="MainController?btAction=Home" class="nav-item nav-link active">Home</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                    <a href="" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <div class="container-fluid pt-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Reset password</h6>
                    <h1 class="display-5 text-uppercase mb-0">Reset password</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-7">
                        <form action="resetPasswordServlet" class="form-floating" >
                            <c:set var = "errors" value="${requestScope.RESET_PASSWORD_ERROR}"/>
                            <input type="hidden" name="txtEmail" value="${sessionScope.USER_RESETPASSWORD.email}" />
                            <div class="row password">
                                <div class="col-4 form-label">
                                    PASSWORD*
                                </div>
                                <div class="col-8">
                                    <c:if test="${empty errors.passwordLengthError}">
                                        <input type="password" class="form-control bg-light px-4"
                                               style="height: 50px;" name="txtPassword" value="${param.txtPassword}">
                                    </c:if>
                                    <c:if test="${not empty errors.passwordLengthError}">
                                        <input type="password" class="form-control bg-light px-4 is-invalid"
                                               style="height: 50px;" name="txtPassword" value="${param.txtPassword}">
                                        <font color ="red">
                                        ${errors.passwordLengthError}
                                        </font>
                                    </c:if>
                                </div>
                            </div>
                            <div class="row password mt-4">
                                <div class="col-4 form-label">
                                    CONFIRM PASSWORD*
                                </div>
                                <div class="col-8">
                                    <c:if test="${not empty errors.confirmNotMatched}">
                                        <input type="password" class="form-control bg-light px-4 is-invalid"
                                               style="height: 50px;" name="txtConfirmPassword">
                                        <font color ="red">
                                        ${errors.confirmNotMatched}
                                        </font>
                                    </c:if>
                                    <c:if test="${empty errors.confirmNotMatched}">
                                        <input type="password" class="form-control bg-light px-4"
                                               style="height: 50px;" name="txtConfirmPassword">
                                    </c:if>
                                </div>
                            </div> 
                            <div>
                                <button class="btn btn-primary w-100 py-3 mt-3" type="submit">CHANGE MY PASSWORD</button>
                            </div>
                        </form>
                    </div>
                </div>
                <script>
                    //alert sucsess
                    if (${not empty requestScope.RESET_PASSWORD_SUCCESS}) {
                        swal.fire({
                            icon: 'success',
                            title: "Success!",
                            text: "Your password is reset",
                            confirmButtonText: 'Click to login'
                        }).then(function () {
                            window.location = "login.jsp";
                        });
                    }
                </script>
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
