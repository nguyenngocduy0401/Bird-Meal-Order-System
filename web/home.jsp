
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

    <style>
        .cards{

            transition: all 0.2s ease;
            cursor: pointer;
        }
        .cards:hover{
            transform: scale(1.05);
        }

        #categorybar {
            position: grid;
            display: block;
            margin-top: 50px;
            top: 10%;
            left: 0;
            width: 200px;
            height: 50%;
            padding-top: 50px;
            padding-bottom: 70px;
            transition: all 0.5s ease;
            z-index: 1;
        }
        .card {
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
        }
        .paging{
            margin-bottom: 30px;
        }
        .product{
            padding-bottom: 10px;
        }

        .products{
            margin-top: 25px;
        }

        .products-row{
            margin-bottom: 20px;
            margin-top: 20px;
        }
        .block{
            margin-bottom: 20px;
        }
        .price-box{
            display: block;
            size: 20px;
            width: 100px;
            margin-bottom: 20px;
            margin-top: 20px;
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
        .name{
            margin-left: 20px;
            margin-right: 20px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .top-product{
            margin-left: 20px;
            margin-right: 20px;
        }

        .containerdd {
        }
        /* Dropdown Button */
        .dropbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            border-radius: 16px;
        }

        /* ĐỔi màu nền khi hover và focus Dropdown button */
        .dropbtn:hover, .dropbtn:focus {
            background-color: #3e8e41;
        }

        /* Định dạng các thẻ bao bọc các menu */
        .dropdown {
            display: inline-block;
        }

        /* Dropdown Content, mặc định sẽ được ẩn đi */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        /* Định dạng các thẻ a là các menu con */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* Thay đổi màu nền khi các menu con được hover */
        .dropdown-content a:hover {background-color: #f1f1f1}

        /* Hiển thị menu, ta sẽ dùng javascript để thêm class này vào các nôi dung cần được hiển thị */
        .show {display:block;}

        .btn-filter{
            height:50px;
            width:100px;
        }
        .btn-cate{
            border: none;
            background: none;
            margin-top: 10px;
            margin-bottom: 10px;
        }
        .btn-cate:hover{
            transform: scale(1.05);
            background: #4CAF50;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <body>
        <c:set var="result" value="${requestScope.PRODUCTS}" />
        <c:set var="cateList" value="${requestScope.CATEGORY_LIST}"/>
        <c:set var="sizeList" value="${requestScope.SIZE_LIST}"/>
        <c:set var="birdList" value="${requestScope.BIRD_LIST}"/>
        <c:set var="top5sale" value="${requestScope.TOP5SALE}"/>
        <c:set var="top5new" value="${requestScope.TOP5NEW}"/>

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
                <div class="col-lg-3  text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-phone-vibrate fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Call Us</h6>
                            <span>+123454654</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 border-start text-center py-2">
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


        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="MainController?btAction=Home" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="col-md-7 container-fluid" >
                    <form action="MainController">
                        <div class="search" >
                            <i class="fa fa-search"></i>
                            <input name="txtSearchValue" type="text" class="form-control" placeholder="Search any product...">
                            <button class="btn btn-primary">Search</button>
                            <input type="hidden" name="txtSearchValue" value="${requestScope.txtSearchValue}" />
                            <input type="hidden" value="Search" name="btAction"/>
                        </div>
                    </form>
                </div>
                <div class="navbar-nav  py-0">
                    <a href="HomePageController" class="nav-item nav-link ">Home</a>
                    <a href="MainController?btAction=Home" class="nav-item nav-link active">Product</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 ">
                        <i class="bi bi-cart  fs-1  me-1" style="line-height: 0.6"></i>
                        <span class="position-absolute top-10 left-100 translate-middle badge rounded-pill bi bg-light text-primary" id="reloadNumberCart">${sessionScope.countItemsCart}</span>
                    </a>
                    <c:if test="${empty sessionScope.user}">
                        <a href="login.jsp" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">
                            Login
                            <i class="bi bi-arrow-right"></i>
                        </a>
                    </c:if>
                    <c:if test="${not empty sessionScope.user}">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown">
                                <i class="bi bi-person fs-1  me-1"></i>
                            </a>
                            <div class="dropdown-menu m-0 dropdown-menu-end">
                                <a href="details.jsp" class="dropdown-item">My profile</a>
                                <a href="MainController?btAction=Purchase" class="dropdown-item">My purchase</a>
                                <a href="LogoutController" class="dropdown-item">Logout</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </nav>

        <div class="container1 content">
            <div class="row">

                <nav class="col-3 navbar card bg-white navbar-light mt-5" id="categorybar">
                    <div class="col-md-8 container-fluid">
                        <div class="sidebar-heading text-primary head">
                            Category
                        </div>
                        <c:forEach var="cate" items="${cateList}">
                            <button class="btn-cate" onclick="category(${cate.categoryID})">${cate.categoryName}</button>
                        </c:forEach>
                        <div class="sidebar-heading text-primary head">
                            Filter
                        </div>
                        <select id="bird" name="ddbBird" class="text-primary bg-light border-0 price-box test">
                            <option value="">Bird</option>
                            <c:forEach var="bird" items="${birdList}">
                                <option value="${bird.birdName}">${bird.birdName}</option>
                            </c:forEach>
                        </select>
                        <select id="cate" name="ddbCategory" class="text-primary bg-light border-0 price-box test"> 
                            <option selected="selected" value=-1>Category</option>
                            <c:forEach var="cate" items="${cateList}">
                                <option value="${cate.categoryID}">${cate.categoryName}</option>
                            </c:forEach>
                        </select>
                            <select id="size" name="ddbSize" class="text-primary bg-light border-0 price-box test" >
                            <option value="">Size</option>
                            <c:forEach var="size" items="${sizeList}">
                                <option value="${size}">${size} g</option>
                            </c:forEach>
                        </select>
                        <input id="minPrice" class="text-primary bg-light border-0 price-box test" type="number" name="minPrice" value="" placeholder="Min Price"/>
                        <input id="maxPrice" class="text-primary bg-light border-0 price-box test" type="number" name="maxPrice" value="" placeholder="Max Price"/>
                        <button onclick="filter()" class="btn btn-primary btn-filter"><i class="bi bi-search"></i></button>
                    </div>
                </nav>

                <c:if test="${empty result}">
                    <section class="col-centered col-md-9 mt-5">
                        <p class="text-uppercase mb-1">"No similar products found!"</p>
                    </section>
                </c:if>

                <c:if test="${not empty result}" >
                    <div class="col-9 container-fluid products">

                        <c:if test="${not empty requestScope.RESULT_AMOUNT}">
                            <p class="text-uppercase mb-1">Search results for the keyword<i class ="text-uppercase text-primary rounded">'${requestScope.txtSearchValue}'</i></p>
                        </c:if>
                        <div id ="content" class="row products-row product-list">
                            <c:forEach var="dto" items="${result}">
                                <div class="cards block col-md-4 mt-1">
                                    <section class="panel">

                                        <div class="card product-item position-relative bg-light d-flex flex-column text-center product">
                                            <div class="clickable" onclick="document.getElementById('formid_${dto.productID}').submit()">
                                                <img class="img-fluid mb-3" src="${dto.imgPath}" alt="">
                                                <p class="name text-uppercase">${dto.productName}</p>
                                                <h5 class="text-primary mb-0">${dto.price} VND</h5>
                                            </div>
                                            <div class="btn-action d-flex justify-content-center">
                                                <div class="d-flex">
                                                    <c:if test="${dto.quantity eq 0}">
                                                    </c:if>
                                                    <c:if test="${dto.quantity ne 0}">

                                                        <button  value="Add" onclick="addToCart(${dto.productID})" class="btn btn-cart btn-primary py-2 px-3" type="button">
                                                            <i class="bi bi-cart-fill fa-2x "  style="font-size: 25px;"></i>
                                                        </button>

                                                    </c:if>
                                                </div>
                                                <div class="d-flex">

                                                    <form action="ProductDetailController" method="post" style="display: none;" id="formid_${dto.productID}">
                                                        <input type="hidden" name="productID" value="${dto.productID}">
                                                        <button type="submit" class="btn btn-primary py-2 px-3">
                                                            <i class="bi bi-eye"></i>
                                                        </button>
                                                    </form>


                                                </div>
                                            </div>

                                        </div>
                                    </section>
                                </div>
                            </c:forEach>
                            <c:set var="prePage" value="${TAGS - 1}" />
                            <c:set var="nextPage" value="${TAGS + 1}" />
                            <c:if test="${requestScope.PAGE != 1}">
                                <div class="col-12 mt-5 paging" >
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination pagination-lg m-0">
                                            <c:if test="${TAGS <= PAGE && TAGS > 1}">
                                                <li class="page-item">
                                                    <a class="page-link rounded-0" onclick="loadPage(${prePage})" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${PAGE}" var="i">
                                                <li class="${TAGS == i?"page-item active":"page-item"}">
                                                    <a class="page-link" onclick="loadPage(${i})">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${TAGS >= 1 &&TAGS < PAGE}">
                                                <li class="page-item">
                                                    <a onclick="loadPage(${nextPage})" class="page-link rounded-0" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </c:if>
                        </div>
                    </div>

                </c:if>
            </div>
        </div>
        <div class="col-9 container-fluid">
            <h5>Top Sale</h5>
            <div class="row products-row product-list">
                <c:forEach var="dto" items="${top5sale}">
                    <div class="cards block top-product col-md-2">
                        <section class="panel">
                            <div class="clickable" onclick="document.getElementById('formid_top5sale${dto.productID}').submit()">
                                <div class="card product-item position-relative bg-light d-flex flex-column text-center product">
                                    <img class="img-fluid mb-3" src="${dto.imgPath}" alt="">
                                    <p class="name text-uppercase">${dto.productName}</p>
                                    <h5 class="text-primary mb-0">${dto.price} VND</h5>
                                    <div class="btn-action d-flex justify-content-center">
                                        <div class="d-flex">
                                            <c:if test="${dto.quantity eq 0}">
                                                <!-- Handle quantity equals 0 -->
                                            </c:if>
                                            <c:if test="${dto.quantity ne 0}">
                                                <form>
                                                    <button value="Add" onclick="addToCart(${dto.productID})" class="btn btn-primary py-2 px-3" type="button">
                                                        <i class="bi bi-cart-fill me-1"></i>
                                                    </button>
                                                </form>
                                            </c:if>
                                        </div>
                                        <div class="d-flex">
                                            <form action="ProductDetailController" method="post" style="display: none;" id="formid_top5sale${dto.productID}">
                                                <input type="hidden" name="productID" value="${dto.productID}">
                                                <button type="submit" class="btn btn-primary py-2 px-3">
                                                    <i class="bi bi-eye"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-9 container-fluid">
            <h5>Top New</h5>
            <div class="row products-row product-list">

                <c:forEach var="dto" items="${top5new}">
                    <div class="cards block top-product col-md-2">
                        <section class="panel">
                            <div class="clickable" onclick="document.getElementById('formid_top5new${dto.productID}').submit()">
                                <div class="product-item card product-item position-relative bg-light d-flex flex-column text-center product">
                                    <img class="img-fluid mb-3" src="${dto.imgPath}" alt="">
                                    <p class="name text-uppercase">${dto.productName}</p>
                                    <h5 class="text-primary mb-0">${dto.price} VND</h5>
                                    <div class="btn-action d-flex justify-content-center">
                                        <div class="d-flex">
                                            <c:if test="${dto.quantity eq 0}">
                                                <!-- Handle quantity equals 0 -->
                                            </c:if>
                                            <c:if test="${dto.quantity ne 0}">
                                                <form>
                                                    <button value="Add" onclick="addToCart(${dto.productID})" class="btn btn-primary py-2 px-3" type="button">
                                                        <i class="bi bi-cart-fill me-1"></i>
                                                    </button>
                                                </form>
                                            </c:if>
                                        </div>
                                        <div class="d-flex">
                                            <form action="ProductDetailController" method="post" style="display: none;" id="formid_top5new${dto.productID}">
                                                <input type="hidden" name="productID" value="${dto.productID}">
                                                <button type="submit" class="btn btn-primary py-2 px-3">
                                                    <i class="bi bi-eye"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </section>
                    </div>
                </c:forEach>
            </div>

        </div>
        <a href="#" class="btn btn-primary py-3 fs-4 back-to-top"><i class="bi bi-arrow-up"></i></a>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
                                                        function loadPage(param) {
                                                            var amount = param;
                                                            $.ajax({
                                                                type: "get",
                                                                url: "PagingProduct",
                                                                data: {
                                                                    index: amount,
                                                                    txtSearchValue: '${requestScope.txtSearchValue}',
                                                                    cateFilter: $('#cate').val(),
                                                                    sizeFilter: $('#size').val(),
                                                                    birdFilter: $('#bird').val(),
                                                                    minPrice: $('#minPrice').val(),
                                                                    maxPrice: $('#maxPrice').val()
                                                                },
                                                                success: function (data) {
                                                                    var row = document.getElementById("content");
                                                                    row.innerHTML = data;
                                                                }
                                                            });
                                                        }
                                                        function addToCart(param) {
                                                            var id = param;
                                                            $.ajax({
                                                                type: "post",
                                                                url: "AddToCartController",
                                                                data: {
                                                                    pid: id,
                                                                },
                                                                success: function () {
                                                                    Swal.fire({

                                                                        icon: 'success',
                                                                        title: 'Successful!',
                                                                        showConfirmButton: false,
                                                                        timer: 1000
                                                                    })

                                                                    $('#reloadNumberCart').load(window.location.href + ' #reloadNumberCart');
                                                                }
                                                            });
                                                        }
                                                        function viewDetail(param) {
                                                            var id = param;
                                                            $.ajax({
                                                                type: "post",
                                                                url: "ProductDetailController",
                                                                data: {
                                                                    productID: id,
                                                                },
                                                                success: function () {
                                                                }
                                                            });
                                                        }
                                                        function filter() {
                                                            $.ajax({
                                                                type: "post",
                                                                url: "PagingFilter",
                                                                data: {
                                                                    index: 1,
                                                                    txtSearchValue: '${requestScope.txtSearchValue}',
                                                                    cateFilter: $('#cate').val(),
                                                                    sizeFilter: $('#size').val(),
                                                                    birdFilter: $('#bird').val(),
                                                                    minPrice: $('#minPrice').val(),
                                                                    maxPrice: $('#maxPrice').val()
                                                                },
                                                                success: function (data) {
                                                                    var row = document.getElementById("content");
                                                                    row.innerHTML = data;
                                                                }
                                                            });
                                                        }

                                                        function category(param) {
                                                            $.ajax({
                                                                type: "post",
                                                                url: "PagingFilter",
                                                                data: {
                                                                    index: 1,
                                                                    txtSearchValue: '${requestScope.txtSearchValue}',
                                                                    cateFilter: param,
                                                                    sizeFilter: "",
                                                                    birdFilter: "",
                                                                    minPrice: "",
                                                                    maxPrice: ""
                                                                },
                                                                success: function (data) {
                                                                    var row = document.getElementById("content");
                                                                    row.innerHTML = data;
                                                                }
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