<%-- 
    Document   : Login
    Created on : May 26, 2023, 6:16:36 PM
    Author     : Duy
--%>
<style>
    .line-separator {
    background-color: #545454;
    flex-grow: 5;
    height: 1px;
}
.or-container {
    align-items: center;
    color: #545454;
    display: flex;
    margin: 25px 0;
}
.btn-login{
    padding: 10px 16px;
    font-size: 15px;
    line-height: 23px
}
.or-label {
    flex-grow: 1;
    margin: 0 15px;
    text-align: center;
}

.btn {
    border-radius: 2px;
    text-transform: capitalize;
    font-size: 15px;
    padding: 10px 19px;
    cursor: pointer
}

.btn-md {
    padding: 10px 16px;
    font-size: 15px;
    line-height: 23px
}

.btn-google {
        color: #545454;
    background-color: #ffffff;
    box-shadow: 0 1px 2px 1px #ddd;
}

</style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.user!=null}">
    <c:if test="${sessionScope.user.role eq 0}">
        <c:redirect url="admin.jsp"></c:redirect>
    </c:if>
    <c:if test="${sessionScope.user.role eq 1}">
        <c:redirect url="staff.jsp"></c:redirect>
    </c:if>
    <c:if test="${sessionScope.user.role eq 2}">
        <c:redirect url="home.jsp"></c:redirect>
    </c:if>
</c:if>

<html>
    <head>
        <meta charset="utf-8">
        <title>Bird Meal Order System</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport

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
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid border-bottom d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-3 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Địa Chỉ</h6>
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
            <a href="home.jsp" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <div class="col-md-7 container-fluid">

                    <div class="search">
                        <i class="fa fa-search"></i>
                        <input type="text" class="form-control" placeholder="Have a question? Ask Now">
                        <button class="btn btn-primary">Search</button>
                    </div>
                </div>
                <div class="navbar-nav ms-auto py-0">
                    <a href="home.jsp" class="nav-item nav-link active">Home</a>
                    <a href="blog.html" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                    <a href="details" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Account <i class="bi bi-arrow-right"></i></a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <div class="container-fluid pt-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Login</h6>
                    <h1 class="display-5 text-uppercase mb-0">Login</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-7">
                        <form action="LoginController" method="post" >
                            <div class="row g-3">
                                <div class="col-12">
                                    <input placeholder="Username" type="text" class="form-control bg-light border-0 px-4"
                                           name ="txtUsername"  style="height: 55px;" >
                                </div>
                                <div class="col-12">
                                    <input placeholder="Password" type="password" class="form-control bg-light border-0 px-4" 
                                           name ="txtPassword" style="height: 55px;" >
                                </div>
                                <div class="col-6 ms">
                                    <input type="checkbox" value="savelogin" name="savelogin">Stayed signed in</td>
                                </div>
                                <div class="col-6 ms-auto">
                                    <a href="verifyEmail.html" class="nav-item nav-link">Don't  have account?Register a new!</a>

                                </div>



                                <c:if test="${param.check eq '1'}">
                                    <div style="color: red;">Don't let your password or username blank</p></div>
                                </c:if>
                                <c:if test="${param.check eq '2'}">
                                    <div style="color: red;">Your username or password is incorrect</p></div>
                                </c:if>

                                <div class="col-12">
                                    <tr><td colspan="2"><button class="btn-login btn btn-primary w-100 py-3" style="font-size: 18px;" type="submit" name="action" value="login">LOGIN</button></td></tr>
                                    

                                </div>
                            </div>
                        </form>
                        <div class="col-md-12">
                             <div class="or-container"><div class="line-separator"></div> <div class="or-label">OR</div><div class="line-separator"></div></div>
                            </div>
                        <div class="row">
                            <div class="col-md-12 " >
                                  <a style="background-color: #ffffff"class=" col-md-12 btn btn-lg btn-google btn-block text-uppercase btn-outline w-100 py-3" href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8084/Bird_Meal_Order_System/GoogleController&response_type=code
                                     &client_id=769539934522-jrnh7eillvbdckp6tcaphgsfu10a6feh.apps.googleusercontent.com&approval_prompt=force"><img style="width: auto; height: auto; background-color: #ffffff" src="https://img.icons8.com/color/16/000000/google-logo.png"> Login Using Google</a>

                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
</html>
