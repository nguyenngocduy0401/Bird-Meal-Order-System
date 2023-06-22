<%-- 
    Document   : checkOutForGuest
    Created on : Jun 13, 2023, 11:06:21 PM
    Author     : Duy
--%>

<style>
    .card {
        box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
    }




    #default:hover {
        /* Define the styles for the hover state */
        color: #7AB730;
        cursor: pointer;
        /* Add any other desired styles */
    }
</style>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="sample.dto.ProductDTO" %>
<%@ page import="sample.dao.ProductDAO" %>
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
                    <a href="MainController?btAction=Home" class="nav-item nav-link active">Home</a>
                    <a href="blog.html" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                        <c:if test="${not empty sessionScope.user}">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown">
                                <i class="bi bi-person fs-1 text-primary me-1"></i>
                            </a>
                            <div class="dropdown-menu m-0 dropdown-menu-end">
                                <a href="details.jsp" class="dropdown-item">My profile</a>
                                <a href="MainController?btAction=Purchase" class="dropdown-item">My purchase</a>
                                <a href="LogoutController" class="dropdown-item">Logout</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <a href="login.jsp" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
                        </c:if>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <div class="container-fluid pt-5 " >
            <c:set var="list" value="${sessionScope.cartCheckOutForGuest}" />
            <!-- NOT EMPTY LIST OF SELECTED ITEMS FOR CHECK-OUT -->
            <c:if test="${not empty list}">
                <div class="container ">
                    <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                        <h6 class="text-primary text-uppercase">Check Out</h6>
                        <h1 class="display-5 text-uppercase mb-0">Check Out</h1>
                    </div>
                    <div class="card container px-5 ps-5 px-lg-5 my-5 ms-5 custom-card">
                        <div class="row px-5 ps-5 px-lg-5 my-5 ms-3">
                            <div class="col-md-6" id="listInfo">
                                <form action="MainController">

                                    <div class="row g-3 " id="addressCustomer" >

                                        <c:if test="${not empty sessionScope.user}">
                                            <c:if test="${not empty sessionScope.addressList}">
                                                <c:forEach var="address" items="${addressList}" >
                                                    <c:set var="userID" value="${address.userID}"/>
                                                    <c:set var="addressID" value="${address.addressID}"/>
                                                    <c:set var="fullName" value="${address.fullName}"/>
                                                    <c:set var="addressDetail" value="${address.addressDetail}"/>
                                                    <c:set var="phoneNumber" value="${address.phoneNumber}"/>
                                                    <div class="row g-3 container border border-opacity-25 border-secondary rounded border-dark pb-3 " style="border-radius: 5px; ">
                                                        <div class="col-md-1 ">
                                                            <div class=" text-center py-4">
                                                                <input type="radio" name="selectAddress"  value="${addressID}" />
                                                            </div>

                                                        </div>
                                                        <div class="col-md-11 ">
                                                            <div class="row">
                                                                <div class="col-md-5   py-1"  >${fullName}</div>
                                                                <div class="col-md-4  border-start  py-1">${phoneNumber}</div>
                                                                <div class="col-md-3 "></div>
                                                            </div>
                                                            <div class="">${addressDetail} </div>
                                                        </div>



                                                    </div>
                                                </c:forEach>
                                                <div class="col-12">
                                                    Notes *
                                                    <textarea class="form-control bg-light border-0 px-4" name="txtNotes"
                                                              style="height: 100px;"></textarea>
                                                </div>
                                                <div class="row ">
                                                    <div class="col-md-6">

                                                    </div>
                                                    <div class="row mt-4 d-flex align-items-center">
                                                        <div class="col-sm-7   text-right">
                                                        </div>
                                                        <div class="col-sm-5   ">
                                                            <button class="btn btn-primary mb-4 btn-lg pl-5 pr-5" style="font-size: 14px" type="submit" name="btAction" value="submitCheckOutGuest">Manage my address</button>
                                                        </div>

                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty sessionScope.addressList}">
                                                <div class="col-12">
                                                    Name * 
                                                    <input type="text" class="form-control bg-light border-0 px-4" name="txtName" 
                                                           style="height: 55px;" value="" required="">
                                                </div>
                                                <div class="col-12">
                                                    Address *
                                                    <input type="text" class="form-control bg-light border-0 px-4" name="txtAddress"
                                                           style="height: 55px;" value="" required="">
                                                </div>
                                                <div class="col-12">
                                                    Phone Number *
                                                    <input type="text" class="form-control bg-light border-0 px-4" name="txtPhoneNumber" pattern="[0-9]{10}"
                                                           title="Please enter a 10-digit phone number" style="height: 55px;" value="">
                                                </div>
                                                <div class="col-12">
                                                    Notes *
                                                    <textarea class="form-control bg-light border-0 px-4" name="txtNotes"
                                                              style="height: 100px;"></textarea>
                                                </div>

                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty sessionScope.user}">
                                            <div class="col-12">
                                                Name * 
                                                <input type="text" class="form-control bg-light border-0 px-4" name="txtName" 
                                                       style="height: 55px;" value="" required="">
                                            </div>
                                            <div class="col-12">
                                                Address *
                                                <input type="text" class="form-control bg-light border-0 px-4" name="txtAddress"
                                                       style="height: 55px;" value="" required="">
                                            </div>
                                            <div class="col-12">
                                                Phone Number *
                                                <input type="text" class="form-control bg-light border-0 px-4" name="txtPhoneNumber" pattern="[0-9]{10}"
                                                       title="Please enter a 10-digit phone number" style="height: 55px;" value="">
                                            </div>
                                            <div class="col-12">
                                                Notes *
                                                <textarea class="form-control bg-light border-0 px-4" name="txtNotes"
                                                          style="height: 100px;"></textarea>
                                            </div>
                                        </c:if>


                                    </div>
                                    <c:forEach var="item" items="${cartCheckOutForGuest}" varStatus="counter">
                                        <c:set var="pid" value="${item.key}" />
                                        <c:set var="product" value="${ProductDAO.getProductByID(pid)}" />
                                        <c:set var="quantity" value="${item.value}" />
                                        <c:set var="price" value="${product.price}" />
                                        <c:set var="total" value="${total + (quantity * product.price)}"/>
                                    </c:forEach>

                                    <div class="float-right text-right">
                                        <h4>Subtotal:</h4>
                                        <div class="row mt-4 d-flex align-items-center">
                                            <div class="col-sm-6 order-md-2">
                                                <h1><span id="subtotalAmount">${total}</span></h1>
                                            </div>
                                        </div>
                                        <div class="row mt-4 d-flex align-items-center">
                                            <div class="col-sm-8   text-right">
                                            </div>
                                            <div class="col-sm-3   ">
                                                <button class="btn btn-primary mb-4 btn-lg pl-5 pr-5" type="submit"  name="btAction" value="submitCheckOutGuest">Submit</button>
                                            </div>
                                            <div class="col-sm-1  text-right">
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            </form>
                            <div id="listproduct" class="col-md-6 text-right border border-dark"> 
                                <table id="shoppingCart" class="table table-condensed table-responsive">
                                    <thead>
                                        <tr>
                                            <th style="width:70%">Name</th>
                                            <th style="width:8%">Price</th>
                                            <th style="width:8%">Quantity</th>
                                            <th style="width: 8%">

                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${cartCheckOutForGuest}" varStatus="counter">
                                            <c:set var="pid" value="${item.key}" />
                                            <c:set var="product" value="${ProductDAO.getProductByID(pid)}" />
                                            <c:set var="quantity" value="${item.value}" />
                                            <c:set var="price" value="${product.price}" />
                                            <c:set var="total" value="${total + (quantity * product.price)}"/>
                                            <tr>
                                                <td data-th="Product">
                                                    <div class="row">
                                                        <div class="col-md-3 text-left">
                                                            <img src=${product.imgPath} alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                                        </div>
                                                        <div class="col-md-9 text-left mt-sm-2">
                                                            <h4>${product.productName}</h4>
                                                            <p class="font-weight-light">${product.productDetail}</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td data-th="Price"><div style="font-weight: bold;">${price}₫</div></td>
                                                <td data-th="Quantity">
                                                    <div class="form-control bg-white form-control-lg text-center">${item.value}</div> 
                                                </td>

                                            <tr>
                                            </c:forEach>

                                    </tbody>

                                </table>
                            </div>
                        </div>
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

