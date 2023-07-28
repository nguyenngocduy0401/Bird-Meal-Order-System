<%-- 
    Document   : purchase
    Created on : Jun 6, 2023, 2:40:45 PM
    Author     : Admin
--%>
<style></style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

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
        <link href="css/style_old.css" rel="stylesheet">

        <!-- Libraries sweetalert2--> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://kit.fontawesome.com/afb5d5fd3d.js" crossorigin="anonymous"></script>
    </head>
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
        /*aa*/
        .content {
            margin-top: 60px;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            margin-top: 30px;
            margin-left: 50px;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        table#alter tr:nth-child(even) {
            background-color: #eee;
        }
        table#alter tr:nth-child(odd) {
            background-color: #fff;
        }
        table#alter th {
            color: white;
            background-color: gray;
        }

        #class{
            margin-left: 50px;
            display: inline;
        }

        #class button{
            margin-left: 10px;
        }

        h1{
            margin-left: 50px;
        }

        .blog-item{
            margin-top: 20px;
        }
        .loadButton{
            margin-top: 30px;
        }
        .add {
            margin-left: auto;
        }
        .custom-button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }
        .card{
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
        }
        .custom-button:hover {
            background-color: #3e8e41;
        }
        .product-inrow{
            background-color: #f8d7da;
        }
        .btn-active{
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }
        .btn{
            cursor: pointer;
            display: inline-block;
            border-radius: 5px;
        }
        .btn-buy{
            margin-left: 20px;
            margin-bottom: 20px;
        }
        #status{
            margin-top: 20px;
        }

        
    </style>

    <body>
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="HomePageController" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="fas fa-dove fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <div class="col-md-7 container-fluid">

                </div>
                <div class="navbar-nav ms-auto py-0">
                    <a href="HomePageController" class="nav-item nav-link  ">Home</a>
                    <a href="MainController?btAction=Home" class="nav-item nav-link ">Product</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link  pt-3  ">
                        <i class="bi bi-cart  fs-1 me-1" style="line-height: 0.6"></i>
                        <span class="position-absolute top-10 left-100 translate-middle badge rounded-pill bi bg-light text-primary" id="reloadNumberCart">${sessionScope.countItemsCart}</span>
                    </a>
                    <c:if test="${not empty sessionScope.user}">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown">
                                <i class="bi bi-person fs-1 me-1 active" style="line-height: 0.6"></i>
                            </a>
                            <div class="dropdown-menu m-0 dropdown-menu-end">
                                <a href="details.jsp" class="dropdown-item">My profile</a>
                                <a href="MainController?btAction=Purchase" class="dropdown-item active">My purchase</a>
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

        <c:set var="order" value="${requestScope.ORDERS}" />
        <div class="container content">

            <div class="row ">
                <div class="title col-md-2">
                    <div class="" style="width:100% auto; color: black; font-weight: bold;">
                        <div class="">My account</div>
                    </div>
                    <div>
                        <div>
                            <div class="">
                                <div style=""> <a class=""" aria-current="page" href="details.jsp">
                                        <div class="" >Profile</div>
                                    </a></div>
                            </div>
                        </div>
                        <div>
                            <div class="">
                                <div><a class="" href="MainController?btAction=Purchase">
                                        <div class=""  style="color: #7AB730" >Purchase</div>
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
                <div class="col-md-10">
                    <div class="fr-flbox middle bg-white" style="border: 1px solid rgb(224, 224, 224); padding: 10px 10px;">
                        <div class="row">
                            <div class="col-md-2 " style="text-align: center;">
                                <button onclick="loadStatus(5)" class="btn text-dark ">All</button>
                            </div>
                            <div class="col-md-2"  style="text-align: center;">
                                <button onclick="loadStatus(1)" class="btn text-dark" value="1">Waiting</button>
                            </div>
                            <div class="col-md-2"  style="text-align: center;">
                                <button onclick="loadStatus(2)" class="btn text-dark" value="2">Confirmed</button>
                            </div>
                            <div class="col-md-2"  style="text-align: center;">
                                <button onclick="loadStatus(3)" class="btn  text-dark" value="3">Shipping</button>
                            </div>
                            <div class="col-md-2"  style="text-align: center;">
                                <button onclick="loadStatus(4)" class="btn text-dark" value="4">Complete</button>
                            </div>
                            <div class="col-md-2"  style="text-align: center;">
                                <button onclick="loadStatus(0)" class="btn text-dark" value="0">Canceled</button>
                            </div>
                        </div>
                    </div>
                    <div id="status">
                        <div class="search mt-2 mb-2">
                            <input id="searchValue" type="text" name="searchValue" value="" class="form-control" placeholder="You can search by Product name">
                            <button onclick="search()" class="btn btn-primary"><i class="bi bi-search"></i></button>
                            <input type="hidden" name="searchValue" value="${requestScope.searchValue}" />
                        </div>
                        <div id="content" class="fr-flbox middle bg-white" style="border: 1px solid rgb(224, 224, 224); padding: 10px 10px;">
                            <c:if test="${empty order}">
                                <h5 class="text-uppercase mb-3">Không có đơn hàng nào được tìm thấy!!</h5>
                            </c:if>
                            <c:if test="${not empty order}">
                                <c:forEach var="dto" items="${requestScope.ORDERS}">
                                    <c:set var="orderID" value="${dto.orderID}" />
                                    <div class="blog-item product">
                                        <div class="row g-0 bg-light overflow-hidden col-12">
                                            <div class="col-12 h-50 d-flex flex-column justify-content-center">
                                                <div class="p-4">
                                                    <div class="d-flex ">
                                                        <c:if test="${dto.status eq 0 }">
                                                            <small class="me-3 col-4">Canceled</small>
                                                        </c:if>
                                                        <c:if test="${dto.status eq 1 }">
                                                            <small class="me-3 col-4">Waiting</small>
                                                        </c:if>
                                                        <c:if test="${dto.status eq 2 }">
                                                            <small class="me-3 col-4">Confirmed</small>
                                                        </c:if>
                                                        <c:if test="${dto.status eq 3 }">
                                                            <small class="me-3 col-4">Shipping</small>
                                                        </c:if>
                                                        <c:if test="${dto.status eq 4 }">
                                                            <small class="me-3 col-4">Complete</small>
                                                        </c:if>
                                                        <small><i class="col-4 bi bi-calendar-date me-2"></i>${dto.date}</small>
                                                        <small class="add"><p >${dto.orderAddress}</p></small>
                                                    </div>
                                                    <div class="d-flex">
                                                        <small>${dto.note}</small>
                                                    </div>
                                                    <div>
                                                        <c:forEach var="product" items="${dto.productsList}" >
                                                            <c:set var="productID" value="${product.productID}" />
                                                            <div class="row border product-inrow">
                                                                <div class="col-md-2 img-fluid">
                                                                    <img src=${product.imgPath} alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                                                </div>
                                                                <div class="col-md-6 text-left mt-sm-2">
                                                                    <h4>${product.productName}</h4>
                                                                </div>
                                                                <div class="col-md-2 text-right mt-sm-2">
                                                                    <p > Price: ${product.price} $</p>
                                                                    <p class="font-weight-light text-right">x ${product.quantity}</p>
                                                                </div>
                                                                <c:if test="${dto.status eq 4 }">
                                                                    <c:set var="feedback" value="${FeedbackDAO.getFeedbackUserProductOrder(productID, sessionScope.user.getUserID(), orderID)}" />
                                                                    <c:if test="${empty feedback.feedbackDetails}">
                                                                        <div class="col-md-2 text-right mt-sm-2">
                                                                            <form action="MainController" method="post">
                                                                                <button type="submit" value="Feedback" name="btAction" class="btn btn-primary btn-buy" type="button">
                                                                                    Feedback
                                                                                </button>
                                                                                <input type="hidden" name="orderID" value="${orderID}" />
                                                                                <input type="hidden" name="productID" value="${productID}" />
                                                                            </form>
                                                                        </div>
                                                                    </c:if>
                                                                </c:if>



                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" value="Add" onclick="addToCart(${dto.orderID})" class="btn btn-primary col-3 btn-buy" type="button">
                                                Buy again
                                            </button>
                                        </div>
                                    </div> 
                                </c:forEach>

                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>



<a href="#" class="btn btn-primary py-3 fs-4 back-to-top"><i class="bi bi-arrow-up"></i></a>
        <!-- JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
                                                function search() {
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "SearchInOrder",
                                                        data: {
                                                            searchValue: $('#searchValue').val()
                                                        },
                                                        success: function (data) {
                                                            var row = document.getElementById("content");
                                                            row.innerHTML = data;
                                                        }
                                                    });
                                                }
                                                function addToCart(orderID) {
                                                    var id = orderID;
                                                    $.ajax({
                                                        type: "post",
                                                        url: "BuyAgainController",
                                                        data: {
                                                            orderID: id,
                                                            userID: '${sessionScope.user.userID}'
                                                        },
                                                        success: function () {
                                                            window.location.href = "viewcart.jsp";
                                                        }
                                                    });
                                                }
                                                function loadStatus(param) {
                                                    $.ajax({
                                                        type: "post",
                                                        url: "LoadStatusOrder",
                                                        data: {
                                                            status: param
                                                        },
                                                        success: function (data) {
                                                            var row = document.getElementById("status");
                                                            row.innerHTML = data;
                                                        }
                                                    });
                                                }
    </script>
</html>
