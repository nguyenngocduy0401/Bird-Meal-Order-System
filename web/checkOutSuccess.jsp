<%-- 
    Document   : checkOutSuccess
    Created on : May 28, 2023, 8:52:10 PM
    Author     : DucAnh
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div class="col-md-9">
            <c:set var="order" value="${sessionScope.ORDER}" />
            <div class="card-header">Account Details</div>
            <!-- Account details card-->
            <div class="card mb-4">


                <c:if test="${not empty order}">
                    <div>
                        <h1>Check Out Success</h1>
                    </div>
                    <div>
                        <h2>Your Receipt</h2>
                    </div>
                    <div class="card-body">
                        <c:set var="orderDetails" value="${sessionScope.LIST_ORDER_DETAILS}" />
                        <c:if test="${not empty orderDetails}">
                            <form>
                                <!-- Form Group (Full name)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Full name</label>
                                    Order ID: ${order.orderID} <br/>
                                    Date: ${order.orderDate} <br/>
                                    Date: ${order.shippingDate} <br/>
                                    Customer Name: ${order.fullName} <br/>
                                    Customer Phone ${order.phoneNumber} <br/>
                                    Customer Address: ${order.orderAddress} <br/>
                                </div>

                                <div>
                                    <table border="1">
                                        <thead>
                                            <tr>
                                                <th>ProductID</th>
                                                <th>Quantity</th>
                                                <th>Price</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="dto" items="${orderDetails}">
                                                <c:set var="quantity" value="${item.value}" />
                                                <c:set var="price" value="${dto.price}" />
                                                <c:set var="total" value="${total + quantity * price}" />
                                                <tr>
                                                    <td>
                                                        ${dto.productID}
                                                    </td>

                                                    <td style="text-align: center">
                                                        ${dto.quantity}
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber value="${dto.price}" 
                                                                          maxFractionDigits="0" />đ
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td colspan="4" style="text-align: right">
                                                    Total
                                                </td>
                                                <td>
                                                    <fmt:formatNumber value="${order.total}"
                                                                      maxFractionDigits="0"/>đ
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>

                                <!-- Save changes button-->

                            </form>
                        </c:if>


                    </div>

                </div>
            </c:if>
            <div>
                <a href="HomeController">
                    <input type="submit" value="Go Shopping" class="btn btn-primary" />
                </a>
                <a href="viewcart.jsp">
                    <input type="submit" value="View Cart" class="btn btn-primary" />
                </a>
            </div>
            <c:if test="${empty order}">
                <div>
                    <h1>No invoice created!</h1>
                </div>

                <div>
                    <a href="HomeController">
                        <input type="submit" value="Go Shopping" class="btn btn-primary" />
                    </a>
                    <a href="viewcart.jsp">
                        <input type="submit" value="View Cart" class="btn btn-primary" />
                    </a>
                </div>
            </c:if>
        </div>
    </body>
</html>
