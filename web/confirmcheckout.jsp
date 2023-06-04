<%-- 
   Document   : confirmcheckout
   Created on : May 28, 2023, 8:41:43 PM
   Author     : DucAnh
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0 mb-5">
            <a href="index.html" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Pet Shop</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto py-0">
                    <a href="index.html" class="nav-item nav-link">Home</a>
                    <a href="about.html" class="nav-item nav-link">About</a>
                    <a href="service.html" class="nav-item nav-link">Service</a>
                    <a href="product.html" class="nav-item nav-link">Product</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu m-0">
                            <a href="price.html" class="dropdown-item active">Pricing Plan</a>
                            <a href="team.html" class="dropdown-item">The Team</a>
                            <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                            <a href="blog.html" class="dropdown-item">Blog Grid</a>
                            <a href="detail.html" class="dropdown-item">Blog Detail</a>
                        </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Contact <i class="bi bi-arrow-right"></i></a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->
        <div class="container-fluid pt-5">
            <c:set var="list" value="${sessionScope.CHECK_OUT_ITEMS}" />
            <!-- NOT EMPTY LIST OF SELECTED ITEMS FOR CHECK-OUT -->
            <c:if test="${not empty list}">
                <div class="container">
                    <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                        <h6 class="text-primary text-uppercase">Check Out Your Cart</h6>
                        <h1 class="display-5 text-uppercase mb-0">Check Out Your Cart</h1>
                    </div>
                    <div class="row g-5">
                        <div class="col-lg-7">
                            <form action="CheckOutOrderServlet">

                                <div class="row g-3">
                                    <div class="col-12">
                                        Name * 
                                        <input type="text" class="form-control bg-light border-0 px-4"
                                               name="txtName" style="height: 55px;" value="">
                                    </div>
                                    <div class="col-12">
                                        Address *
                                        <input type="text" class="form-control bg-light border-0 px-4" name="txtAdress"
                                               style="height: 55px;" value="">
                                    </div>
                                    <div class="col-12">
                                        PhoneNumber *
                                        <input type="text" class="form-control bg-light border-0 px-4" name="txtPhoneNumber"
                                               style="height: 55px;" value="">
                                    </div>
                                    <div class="col-12">
                                        shippingDate *
                                        <input type="text" class="form-control bg-light border-0 px-4" name="txtshippingDate" 
                                               style="height: 55px;" value="">
                                    </div>
                                </div>

                                <div class="row g-5">
                                    <c:forEach var="item" items="${list}" varStatus="counter">
                                        <c:set var="dto" value="${item.key}" />
                                        <c:set var="quantity" value="${item.value}" />
                                        <c:set var="price" value="${dto.price}" />
                                        <c:set var="total" value="${total + quantity * price}" />
                                        <div class="col-lg-4">
                                            <div class="bg-light text-center pt-5 mt-lg-5">
                                                <h2 class="text-uppercase">Basic</h2>
                                                <h6 class="text-body mb-5">The Best Choice</h6>
                                                <div class="text-center bg-primary p-4 mb-2">
                                                    <h1 class="display-4 text-white mb-0">
                                                        <small class="align-top"
                                                               style="font-size: 22px; line-height: 45px;">

                                                        </small>
                                                        Price: <fmt:formatNumber value="${price}" 
                                                                          maxFractionDigits="0"/>
                                                        <small
                                                            class="align-bottom" style="font-size: 16px; line-height: 40px;">$
                                                        </small>
                                                    </h1>
                                                </div>
                                                <div class="text-center p-4">
                                                    <div class="d-flex align-items-center justify-content-between mb-1">
                                                        <span>No. ${counter.count}</span>
                                                        <i class="bi bi-check2 fs-4 text-primary"></i>
                                                    </div>
                                                    <div class="d-flex align-items-center justify-content-between mb-1">
                                                        <span>ProductID: ${dto.productID}</span>
                                                        <i class="bi bi-check2 fs-4 text-primary"></i>
                                                    </div>
                                                    <div class="d-flex align-items-center justify-content-between mb-1">
                                                        <span>Quantity: ${quantity}</span>
                                                        <i class="bi bi-check2 fs-4 text-primary"></i>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="float-right text-right">
                                    <h4>Subtotal:</h4>
                                    <h1><fmt:formatNumber value="${total}" maxFractionDigits="0"/>đ
                                        <input type="hidden" name="txtTotal" value="${total}" /></h1>
                                </div>
                                <div class="col-12">
                                    <div class="btn btn-primary w-100 py-3" >
                                        <input type="submit" value="Check Out" class="btn" />
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>

                </div>
            </c:if>
            <div class="no-list row">
                <c:if test="${empty list}">
                    <h2>No Selected Item for CheckOut!</h2>
                    <a class="btn btn-primary" href="viewcart.jsp">Go Back To Your Cart</a> 
                    
                </c:if>
            </div>
        </div>
    </body>
</html>
