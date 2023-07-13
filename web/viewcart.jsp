<%-- 
    Document   : viewcart
    Created on : May 26, 2023, 3:46:31 PM
    Author     : DucAnh
--%>
<style>
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
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="sample.dto.ProductDTO" %>
<%@ page import="sample.dao.ProductDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<c:if test="${param.check ne 1}">
    <c:redirect url="ShowCartController" />
</c:if>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    function goBack() {
        window.location.href = document.referrer;
    }
    var subtotal = 0;
//    function validateInput(inputElement,quantityRepo) {
//        if (inputElement.value < 1) {
//            inputElement.value = 1;
//        }else if(inputElement.value > quantityRepo){
//            inputElement.value = quantityRepo;
//        }
//    }
    function calculateTotal(quantity, price, checkbox) {
        var total = quantity * price;
        if (checkbox.checked) {

            subtotal += total;
        } else {
            subtotal -= total;
        }
        document.getElementById("subtotalAmount").textContent = formatCurrency(subtotal);
    }

    function formatCurrency(amount) {
        return amount.toLocaleString("vi-VN", {style: "currency", currency: "VND"});
    }

    function toggleSelectAll(totalALL, checkbox) {
        var checkboxes = document.getElementsByName("select");

        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = checkbox.checked;
        }
        var total = totalALL;
        if (checkbox.checked) {
            subtotal = total;
        } else {
            subtotal = 0;
        }
        document.getElementById("subtotalAmount").textContent = formatCurrency(subtotal);
    }

//    function toggleSelectAll(totalALL, button) {
//
//        var checkboxes = document.getElementsByName("select");
//        var total = totalALL;
//
//        if (button.innerText === 'Select All') {
//            for (var i = 0; i < checkboxes.length; i++) {
//                checkboxes[i].checked = true;
//            }
//            subtotal = total;
//            button.innerText = 'Deselect All';
//        } else {
//            subtotal = 0;
//            for (var i = 0; i < checkboxes.length; i++) {
//                checkboxes[i].checked = false;
//            }
//            button.innerText = 'Select All';
//        }
//
//        document.getElementById("subtotalAmount").textContent = formatCurrency(subtotal);
//    }

    $(document).on("change", ".form-control", function () {
        var quantity = $(this).val();
        var key = $(this).data("key");
        var repo = $(this).data("repo");

        if (quantity < 1) {
            quantity = 1;
        } else if (quantity > repo) {
            quantity = repo;
        }

        UpdateCart(quantity, key);
    });

    function UpdateCart(value, key) {
        var quantity = value;
        var id = key;
        $.ajax({
            type: "post",
            url: "UpdateCartController",
            data: {
                pid: id,
                qty: quantity
            },
            success: function () {
                $('#reloadCalculator').load(window.location.href + ' #reloadCalculator');
                subtotal = 0;
            }
        });
    }
    function removeSelectedProduct() {
        var selectedProductIds = [];
        var checkboxes = document.getElementsByName("select");

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedProductIds.push(checkboxes[i].value);
            }
        }

        $.ajax({
            type: "post",
            url: "RemoveProductController",
            data: {
                selectedProductIds: selectedProductIds
            },
            success: function () {
                $('#reloadCalculator').load(window.location.href + ' #reloadCalculator');
            },
            error: function () {
                alert("Error occurred while removing products.");
            }
        });
    }

    function removeProduct(pid) {
        var selectedProductIds = [];


        selectedProductIds.push(pid);


        $.ajax({
            type: "post",
            url: "RemoveProductController",
            data: {
                selectedProductIds: selectedProductIds
            },
            success: function () {
                $('#reloadCalculator').load(window.location.href + ' #reloadCalculator');
            },
            error: function () {
                alert("Error occurred while removing products.");
            }
        });
    }
    function checkOutSelectedProduct() {
        var selectedProductIds = [];
        var checkboxes = document.getElementsByName("select");

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedProductIds.push(checkboxes[i].value);
            }
        }
        if (selectedProductIds.length > 0) {
            $.ajax({
                type: "post",
                url: "CheckOutProductController",
                data: {
                    select: selectedProductIds
                },
                success: function () {
                    window.location.href = "checkOutForGuest.jsp";
                }
            });

        } else
        {
            document.getElementById("checkselect").textContent = "Please select a product for checkout."

        }
    }
</script>
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
                    <a href="MainController?btAction=Home" class="nav-item nav-link ">Home</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 active "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
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

        <div class="container-fluid pt-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Cart</h6>
                    <h1 class="display-5 text-uppercase mb-0">Cart</h1>
                </div>
            </div>
        </div>
        <div id="content"></div>
       
        <section class="card col-md-9 mx-auto container-fluid pt-5" >
            <div class="ps-5" id="checkselect" style="color: red;"></div>
            <c:set var="cart" value="${sessionScope.cart}" />
            <c:if test="${not empty cart}">
                <div class="container" id="reloadCalculator">
                    <div class="row w-100">
                        <div class="col-lg-12 col-md-12 col-12">
                            <form action="MainController">
                                <table id="shoppingCart" class="table table-condensed table-responsive">
                                    <thead>
                                        <c:forEach var="item" items="${cart}" varStatus="counter">
                                            <c:set var="pid" value="${item.key}" />
                                            <c:set var="product" value="${ProductDAO.getProductByID(pid)}" />
                                            <c:set var="quantity" value="${item.value}" />
                                            <c:set var="price" value="${product.price}" />
                                            <c:set var="total" value="${total + (quantity * product.price)}"/>
                                        </c:forEach>
                                        <tr>
                                            <th style="width: 7%">
                                                <div class="btn btn-white border-secondary bg-white btn-md mb-2">
                                                    <input type="checkbox" id="selectAll" onclick="toggleSelectAll(${total}, this)" />
                                                </div>
                                            </th>
                                            <th style="width:70%">Name</th>
                                            <th style="width:8%">Price</th>
                                            <th style="width:10%">Quantity</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${cart}" varStatus="counter">
                                            <c:set var="pid" value="${item.key}" />
                                            <c:set var="product" value="${ProductDAO.getProductByID(pid)}" />
                                            <c:set var="quantity" value="${item.value}" />
                                            <c:set var="price" value="${product.price}" />
                                            <c:set var="total" value="${total + (quantity * product.price)}"/>
                                            <c:set var="quantityRepo" value="${product.quantity}"/>
                                            <tr>
                                                <td class="actions" data-th="">
                                                    <div class="text-right">
                                                        <div class="btn btn-white border-secondary bg-white btn-md mb-2">
                                                            <input type="checkbox" name="select" onchange="calculateTotal(${item.value},${product.price}, this)" value="${product.productID}" />
                                                        </div>

                                                    </div>
                                                </td>
                                                <td data-th="Product">
                                                    <div class="row">
                                                        <div class="col-md-3 text-left">
                                                            <img src=${product.imgPath} alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                                        </div>
                                                        <div class="col-md-9 text-left mt-sm-2">
                                                            <h4>${product.productName}</h4>
                                                            <p class="font-weight-light">Size: ${product.size}g</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td data-th="Price"><div style="font-weight: bold;">${price}₫</div></td>
                                                <td data-th="Quantity">
                                                    <input type="number" data-repo="${quantityRepo}" data-key="${item.key}"  class="form-control form-control-lg text-center" min="1" value=${quantity} >
                                                </td>
                                                <td class="actions" data-th="">
                                                    <div class="text-right">
                                                        <div class="btn btn-white  bg-white btn-md mb-2">
                                                            <button class="btn btn-primary mb-4 btn-lg pl-5 pr-5" type="button" onclick="removeProduct(${item.key})"><span class="bi bi-trash"></span></button>
                                                        </div>

                                                    </div>
                                                </td>
                                            <tr>
                                            </c:forEach>

                                    </tbody>
                                </table>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-7"></div>
                                        <div class="col-md-1 text-left">
                                        </div>
                                        <div class="col-md-1 text-left">
                                        </div>
                                        <div class="col-md-1 text-right">
                                        </div>
                                        <div class="col-md-2 text-right">
                                            <!--                                            <button class="btn btn-primary btn-sm" id="selectAll" type="button" onclick="toggleSelectAll($total, this)">Select All</button>-->

                                        </div>
                                    </div>
                                </div>

                                <div class="float-right text-right">
                                    <h4>Subtotal:</h4>
                                    <div class="row mt-4 d-flex align-items-center">
                                        <div class="col-sm-6 order-md-2">
                                            <h1><span id="subtotalAmount"></span></h1>
                                        </div>
                                    </div>
                                    <div class="row mt-4 d-flex align-items-center">
                                        <div class="col-sm-6 order-md-2 text-right">
                                            <button class="btn btn-primary mb-4 btn-lg pl-5 pr-5" type="button" onclick="removeSelectedProduct()">Remove Selected Product</button>
                                        </div>
                                        <div class="col-sm-6 order-md-2 text-right">
                                            <button class="btn btn-primary mb-4 btn-lg pl-5 pr-5"  type="button" onclick="checkOutSelectedProduct()" value="checkOutSelectedProduct">Check Out Selected Product</button>
                       
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty cart}">
                <div>
                    <h1>No item exited in your cart!</h1>
                    <a href="HomeController">Click Here To Go Shopping</a>
                </div>
            </c:if>
        </section>
    </body>
    <footer class="footer">
        <div class="container">
            <p>&copy; 2023 Bird Meal Order System. All rights reserved.</p>
        </div>
    </footer>
</html>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>