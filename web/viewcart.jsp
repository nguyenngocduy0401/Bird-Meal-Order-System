<%-- 
    Document   : viewcart
    Created on : May 26, 2023, 3:46:31 PM
    Author     : DucAnh
--%>
<style>
    .card{
        box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
    }
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="index.html" class="navbar-brand ms-lg-5">
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
                    <a href="Home.html" class="nav-item nav-link active">Home</a>
                    <a href="blog.html" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                    <a href="" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->
        
        <div class="container-fluid pt-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Cart</h6>
                    <h1 class="display-5 text-uppercase mb-0">Cart</h1>
                </div>
            </div>
        </div>


        <section class="card col-md-8 mx-auto container-fluid pt-5" >
            <c:set var="cart" value="${sessionScope.CART}" />
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}" />
                <!-- NOT EMPTY ITEMS -->
                <c:if test="${not empty items}">
                    <div class="container">
                        <div class="row w-100">
                            <div class="col-lg-12 col-md-12 col-12">
                                
                                    <table id="shoppingCart" class="table table-condensed table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width:65%">Name</th>
                                                <th style="width:15%">Price</th>
                                                <th style="width:10%">Quantity</th>
                                                <th style="width:10%"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="item" items="${items}" varStatus="counter">
                                                <c:set var="dto" value="${item.key}" />
                                                <c:set var="quantity" value="${item.value}" />
                                                <c:set var="price" value="${dto.price}" />
                                                <c:set var="total" value="${total + quantity * price}" />
                                                <tr>
                                                    <td data-th="Product">
                                                        <div class="row">
                                                            <div class="col-md-3 text-left">
                                                                <img src=${dto.imgPath} alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                                            </div>
                                                            <div class="col-md-9 text-left mt-sm-2">
                                                                <h4>${dto.productName}</h4>
                                                                <p class="font-weight-light">${dto.productDetail}</p>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td data-th="Price"><fmt:formatNumber value="${dto.price}" maxFractionDigits="0"/></td>
                                                    <td data-th="Quantity">
                                                        <input type="number" class="form-control form-control-lg text-center" value=${quantity}>
                                                    </td>
                                                    <td class="actions" data-th="">
                                                        <div class="text-right">
                                                            <div class="btn btn-white border-secondary bg-white btn-md mb-2">
                                                                <input on ="select(${dto.productID})" class="align-items-center" type="checkbox" name="chkItem" 
                                                                       value="${dto.productID}" />
                                                            </div>

                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="float-right text-right">
                                        <h4>Subtotal:</h4>
                                        <h1><fmt:formatNumber value="${total}" maxFractionDigits="0"/>đ
                                            <input type="hidden" name="txtTotal" value="${total}" /></h1>
                                    </div>
                                    <div class="row mt-4 d-flex align-items-center">
                                        <div class="col-sm-6 order-md-2 text-right">
                                            <div class="btn btn-primary mb-4 btn-lg pl-5 pr-5"><input type="submit" class="btn"
                                                                                                      value="Remove Selected Foods" 
                                                                                                      name="btAction" /></div>
                                        </div>
                                        <div class="col-sm-6 order-md-2 text-right">
                                            <div class="btn btn-primary mb-4 btn-lg pl-5 pr-5"><input type="submit" class="btn"
                                                                                                      value="Check Out Selected Foods"
                                                                                                      name="btAction" /></div>
                                        </div>
                                    </div>
                               

                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty items}">
                    <div>
                        <h2>
                            No item exited in your cart!
                        </h2>
                        <a href="HomeController">Add More Books to Your Cart</a>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${empty cart}">
                <div>
                    <h1>No cart is existed!</h1>
                    <a href="HomeController">Click Here To Go Shopping</a>
                </div>
            </c:if>
        </section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
                                function select(param) {
                                    $.ajax({
                                        type: "get",
                                        url: "SelectServlet",
                                        data: {
                                            productID: param,
                                        },
                                        success: function () {
                                           alert("say somthing");
                                        }
                                        
                                    });
                                }
        </script>
            
    </body>
</html>

