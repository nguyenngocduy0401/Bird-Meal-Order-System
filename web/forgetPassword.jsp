<%-- 
    Document   : forgetPassword
    Created on : Jul 14, 2023, 1:20:50 AM
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
        <script src="https://kit.fontawesome.com/afb5d5fd3d.js" crossorigin="anonymous"></script>
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
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i class="fas fa-dove fs-1 text-primary me-3"></i></a>
                    <a href="login.jsp" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <div class="container-fluid pt-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Forget password</h6>
                    <h1 class="display-5 text-uppercase mb-0">Forget password</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-7">
                        <form action="SendEmailForgetPasswordServlet" method="POST" class="form-floating" >
                            <c:set var = "errors" value="${requestScope.EMAIL_ERROR}"/> 
                            <c:choose>
                                <c:when test="${not empty errors.emailFormatError}">
                                    <div class="col-12">
                                        <input type="text" class="form-control  bg-light px-4 is-invalid" placeholder="Enter email" 
                                               style="height: 55px;" name="txtEmail" value="${param.txtEmail}">
                                    </div>
                                    <div style="margin-top: 10px">
                                    <font color ="red">
                                    ${errors.emailFormatError}
                                    </font>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3 mt-3" type="submit">Continue</button>
                                    </div>
                                </c:when>
                                <c:when test="${not empty errors.accountNotAvailable}">
                                    <div class="col-12">
                                        <input type="text" class="form-control  bg-light   px-4 is-invalid" placeholder="Email*" 
                                               style="height: 55px;" name="txtEmail" value="${param.txtEmail}">
                                    </div>
                                    <div style="margin-top: 10px">
                                    <font color ="red">
                                    ${errors.accountNotAvailable}
                                    </font>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3 mt-3 " type="submit">Continue</button>
                                    </div>
                                </c:when>
                                <c:when test="${not empty errors.emailNotFound}">
                                    <div class="col-12">
                                        <input type="text" class="form-control bg-light  px-4 is-invalid" placeholder="Email*" 
                                               style="height: 50px;" name="txtEmail" value="${param.txtEmail}">
                                    </div>
                                    <div style="margin-top: 10px">
                                    <font color ="red">
                                    ${errors.emailNotFound}
                                    </font>
                                    <div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3 mt-3" type="submit">Continue</button>
                                    </div>
                                </c:when>
                                    <c:when test="${not empty requestScope.SEND_EMAIL_SUCCESS}">
                                    <div class="col-12">
                                        <input type="text" class="form-control bg-light  px-4" placeholder="Email*" 
                                               style="height: 55px;" name="txtEmail" value="${param.txtEmail}">
                                    </div>
                                    <div style="margin-top: 10px">
                                    <font color ="green">A link reset password has been sent. Please check your email inbox or spam.
                                    </font>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3 mt-3" type="submit">Send link again</button>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-12">
                                        <input type="text" class="form-control bg-light  px-4" placeholder="Email*" 
                                               style="height: 55px;" name="txtEmail" value="${param.txtEmail}">
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3 mt-3" type="submit">Send</button>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                    </div>
                </div>
                </form>

            </div>
        </div>

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