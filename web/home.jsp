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
        #categorybar {
            position: sticky;
            top: 0;
            z-index: 999;
        }
        .card {
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
        }
        .paging{
            margin-bottom: 30px;
        }
        .product{
            padding-bottom: 10px;
            padding-top: 10px;
            padding-left: 5px;
            padding-right: 5px;
        }

        .products-row{
            margin-bottom: 10px;
            margin-top: 10px;
        }
        .price-box{
            size: 20px;
            width: 100px;
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
    </style>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <body>
        <c:set var="result" value="${requestScope.PRODUCTS}" />
        <c:set var="cateList" value="${requestScope.CATEGORY_LIST}"/>
        <c:set var="sizeList" value="${requestScope.SIZE_LIST}"/>
        <c:set var="birdList" value="${requestScope.BIRD_LIST}"/>

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


        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="MainController?btAction=Home" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <div class="col-md-7 container-fluid">
                    <form action="MainController">
                        <div class="search" style="height: 45px">
                            <i class="fa fa-search"></i>
                            <input name="txtSearchValue" type="text" class="form-control" placeholder="Search any product...">
                            <button class="btn btn-primary">Search</button>
                            <input type="hidden" name="txtSearchValue" value="${requestScope.txtSearchValue}" />
                            <input type="hidden" value="Search" name="btAction"/>
                        </div>
                    </form>
                </div>
                <div class="navbar-nav ms-auto py-0">
                    <a href="MainController?btAction=Home" class="nav-item nav-link active">Home</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 ">
                        <i class="bi bi-cart  fs-1 text-primary me-1"></i>
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
                                <i class="bi bi-person fs-1 text-primary me-1"></i>
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


        <nav class="col-9 navbar card bg-white navbar-light mt-5 mx-auto" id="categorybar">
            <div class="col-md-8 container-fluid">
                <select id="bird" name="ddbBird" class="text-primary bg-light border-0">
                    <option value="">Bird</option>
                    <c:forEach var="bird" items="${birdList}">
                        <option value="${bird.birdName}">${bird.birdName}</option>
                    </c:forEach>
                </select>
                <select id="cate" name="ddbCategory" class="text-primary bg-light border-0">
                    <option selected="selected" value=-1>Category</option>
                    <c:forEach var="cate" items="${cateList}">
                        <option value="${cate.categoryID}">${cate.categoryName}</option>
                    </c:forEach>
                </select>
                <select id="size" name="ddbSize" class="text-primary bg-light border-0">
                    <option value="">Size</option>
                    <c:forEach var="size" items="${sizeList}">
                        <option value="${size}">${size} g</option>
                    </c:forEach>
                </select>
                <input id="minPrice" class="text-primary bg-light border-0 price-box" type="number" name="minPrice" value="" placeholder="Min Price"/>
                <input id="maxPrice" class="text-primary bg-light border-0 price-box" type="number" name="maxPrice" value="" placeholder="Max Price"/>
                <button onclick="filter()" class="btn btn-primary">Filter</button>
            </div>
        </nav>


        <section class=" col-centered col-md-9 mt-5 mx-auto">
            <c:if test="${empty result}">
                <p class="text-uppercase mb-1">Không có sản phẩm tương tự được tìm thấy!!</p>
            </c:if>
        </section>
        <c:if test="${not empty result}" >
            <section class="col-centered col-md-9 mt-5 mx-auto">
                <c:if test="${not empty requestScope.RESULT_AMOUNT}">
                    <p class="text-uppercase mb-1">Kết quả tìm kiếm cho từ khóa <i class ="text-uppercase text-primary rounded">'${requestScope.txtSearchValue}'</i></p>
                </c:if>
            </section>
            <section class="col-md-9 container-fluid products">
                <div id ="content" class="row products-row product-list">
                    <c:forEach var="dto" items="${result}">
                        <div class="block col-md-4 mt-1">
                            <section class="panel">
                                <div class="card product-item position-relative bg-light d-flex flex-column text-center product">
                                    <img class="img-fluid mb-3" src="${dto.imgPath}" alt="">
                                    <h6 class="name text-uppercase">${dto.productName}</h6>
                                    <h5 class="text-primary mb-0">${dto.price} $</h5>
                                    <div class="btn-action d-flex justify-content-center">
                                        <div class="d-flex">
                                            <c:if test="${dto.quantity eq 0}">
                                            </c:if>
                                            <c:if test="${dto.quantity ne 0}">
                                                <button type="submit" value="Add" onclick="addToCart(${dto.productID})" class="btn btn-primary py-2 px-3" type="button">
                                                    <i class="bi bi-cart-fill me-1 "></i>
                                                </button>
                                            </c:if>
                                        </div>
                                        <a class="btn btn-primary py-2 px-3" href="ProductDetailController?productID=${dto.productID}"><i class="bi bi-eye"></i></a>
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
                                                <span aria-hidden="true"><i class="bi bi-arrow-left"></i></span>
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
                                                <span aria-hidden="true"><i class="bi bi-arrow-right"></i></span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </c:if>
                </div>
            </section>
        </c:if>
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