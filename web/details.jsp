<%-- 
    Document   : details
    Created on : Jun 4, 2023, 3:54:05 PM
    Author     : haong
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<style>
.content{
    margin-top: 60px;
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
</head>

<body>
    <!-- Topbar Start -->
    <div class="container-fluid border-bottom d-none d-lg-block">
        <div class="row gx-0">
            <div class="col-lg-4 text-center py-2">
                <div class="d-inline-flex align-items-center">
                    <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                    <div class="text-start">
                        <h6 class="text-uppercase mb-1">Địa Chỉ</h6>
                        <span>Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức</span>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 text-center border-start border-end py-2">
                <div class="d-inline-flex align-items-center">
                    <i class="bi bi-envelope-open fs-1 text-primary me-3"></i>
                    <div class="text-start">
                        <h6 class="text-uppercase mb-1">Email Us</h6>

                        <span>fpt@example.com</span>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 text-center py-2">
                <div class="d-inline-flex align-items-center">
                    <i class="bi bi-phone-vibrate fs-1 text-primary me-3"></i>
                    <div class="text-start">
                        <h6 class="text-uppercase mb-1">Call Us</h6>
                        <span>+123454654</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
        <a href="index.html" class="navbar-brand ms-lg-5">
            <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store
            </h1>
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
                <a href="index.html" class="nav-item nav-link active">Home</a>
                <a href="blog.html" class="nav-item nav-link">Blog</a>
                <a href="cart.html" class="nav-item nav-link pt-3 "><i
                        class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown"><i
                            class="bi bi-person fs-1 text-primary me-1"></i>
                    </a>
                    <div class="dropdown-menu m-0 dropdown-menu-end">
                        <a href="details.jsp" class="dropdown-item">My profile</a>
                        <a href="purchase.html" class="dropdown-item">My purchase</a>
                        <a href="LogoutController" class="dropdown-item">Logout</a>

                    </div>
                </div>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->
    <c:set var="user" value="${sessionScope.user}"/>
    <c:if test="${not empty user}">


    <div class="container content">
 
        <div class="row ">
            <div class="col-md-3"   >
                <div class="" style="width:100% auto;">
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
                            <div><a class="" href="purchase.jsp">
                                    <div class="">Purchase</div>
                                </a></div>
                        </div>
                    </div>
                </div>
                <div class="" style="width: auto;">
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
                        <div class=""><a class="" href="editPassword.html">
                                <div class="">Change my password</div>
                            </a></div>
                    </div>
                    <div>
                        <div class=""><a class="" href="remove.html">
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
    <c:if test="${empty user}">
        <div>please login.</div>
    </c:if>



    <!-- JavaScript Libraries -->
    
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
   
</body>

</html>
