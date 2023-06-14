<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.user==null||sessionScope.user.role ne 1}">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
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

        .h123{
            margin-top: 30px;
            margin-left: 50px;
        }
        .bg-success {
            background-color: #7ab730 !important;
        }
        .h3 {
            font-size: 1.75rem;
            font-weight: 400;
            line-height: 1.2;
        }

        .mb-4 {
            margin-bottom: 1.5rem;
        }

        <!-- CSS-THEM -->
        .search-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 40px;
            background-color: #f2f2f2;
            border-radius: 20px;
            padding: 5px;
        }

        .search-container input[type="text"] {
            width: 80%;
            border: none;
            background-color: #f2f2f2;
            outline: none;
            font-size: 16px;
        }

        .search-container button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            margin-left: 10px;
            padding: 5px 15px;
            font-size: 16px;
        }


    </style>

    <head>
        <meta charset="utf-8">
        <title>Bird Meal Order System</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />

        <!-- Favicon -->
        <link href=" img/favicon.ico" rel="icon">

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
        <!-- Template Stylesheet -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css" 
              integrity="sha512-ePvKjeDk8O9sig3l4+Jxstq26n4yWdbsRm8g7MUroTqMNXGf2rS6jzJFCCVipBjfYsI0wL+oNT/zKKcjjl5bGQ==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous">
        </script>
    </head>

    <body>
        <header>
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
                <a href="Home.html" class="navbar-brand ms-lg-5">
                    <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store
                    </h1>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">

                    <div class="navbar-nav ms-auto py-0">
                        <a href="Home.html" class="nav-item nav-link active">Home</a>
                        <a href="blog.html" class="nav-item nav-link">Blog</a>
                        <a href="cart.html" class="nav-item nav-link pt-3 "><i
                                class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                        <a href="LogoutController" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Logout <i
                                class="bi bi-arrow-down"></i></a>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->
        </header>


        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <div class="navbar-collapse">
                <a href="addnew.html" class="nav-item nav-link active">Add new product</a>
                <a href="Staff.html" class="nav-item nav-link">Product list</a>
                <a href="orderlist.html" class="nav-item nav-link">Order list</a>
                <a href="feedback.html" class="nav-item nav-link">Feedback</a>

            </div>
        </nav>
        <c:set var="result" value="${requestScope.PRODUCTS}" />
        <div class="container my-5">
            <h1 class="h3 mb-4">Product list</h1>

            <div class="row">
                <div class="col-md-6 form-inputs">
                    <form action="MainController">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Search any product...">
                            <button class="btn btn-outline-secondary" type="button"><i class="bi bi-search"></i></button>
                        </div>
                        <div class="col-md-11 text-end">
                            <button class="btn btn-primary me-2 custom-button" name="btAction" value="Create New Product">Add new product</button>
                            <button class="btn btn-success me-2 custom-button" name="btAction" value="StaffHome">List All</button>
                        </div>
                    </form>
                    <div class="col-md-11 text-end">
                        <button class="btn btn-primary me-2 custom-button" data-bs-toggle="modal" data-bs-target="#add-category-model">Add new category product</button>
                    </div>
                </div>
            </div>
            <div class="search-container">
                <input type="text" placeholder="Tìm kiếm...">
                <button type="submit">Tìm</button>
            </div>
        </div>

        <div id="content" class="container my-5">
            <c:if test="${empty result}">
                Vui long them moi san pham!!
            </c:if>
            <c:if test="${not empty result}">
                <table class="table table-striped table-hover">
                    <thead class="bg-success text-white">
                        <tr>
                            <th>imgPath</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Category</th>
                            <th>Details</th>
                            <th>Size</th>
                            <th>Age Recommendation</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Country</th>
                            <th>Action</th> 
                        </tr>
                    </thead>
                    <tbody id = "content">
                        <c:forEach var="dto" items="${result}">
                            <tr>
                                <td><img class="img-fluid mb-4" src="${dto.imgPath}" alt=""></td>
                                <td>${dto.productName}</td>
                                <td>${dto.price}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.categoryID}</td>
                                <td>${dto.productDetail}</td>
                                <td>${dto.size}</td>
                                <td>${dto.ageRecommendation}</td>
                                <td>${dto.date}</td>
                                <td>${dto.status}</td>
                                <td>${dto.country}</td>
                                <td>
                                    <button class="btn btn-outline-primary"><a href="UpdateProductServlet?action=edit&txtProductId=${dto.productID}" >Edit</a><i class="bi bi-pencil"></i></button>
                                    <button class="btn btn-outline-danger"><i class="bi bi-trash"></i> Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:set var="prePage" value="${TAGS - 1}" />
            <c:set var="nextPage" value="${TAGS + 1}" />
            <div class="col-12 mt-5">
                <c:if test="${requestScope.PAGE != 1}">
                    <div class="d-flex justify-content-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination pagination-lg m-0">
                                <c:if test="${TAGS <= PAGE && TAGS > 1}">
                                    <li class="page-item">
                                        <a class="page-link rounded-0" onclick="previousPage()" aria-label="Previous">
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
                                        <a onclick="nextPage()" class="page-link rounded-0" aria-label="Next">
                                            <span aria-hidden="true"><i class="bi bi-arrow-right"></i></span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </c:if>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
<<<<<<< Updated upstream
                                function loadPage(param) {
                                    var amount = param;
                                    $.ajax({
                                        type: "get",
                                        url: "PagingStaffProduct",
                                        data: {
                                            index: amount,
                                            txtSearchValue: '${requestScope.txtSearchValue}',
                                        },
                                        success: function (data) {
                                            var row = document.getElementById("content");
                                            row.innerHTML = data;
                                        }
                                    });
                                }
=======
                                            function nextPage() {
                                                var amount = ${requestScope.TAGS};

                                                $.ajax({
                                                    type: "get",
                                                    url: "PagingStaffProduct",
                                                    data: {
                                                        index: amount + 1,
                                                        txtSearchValue: '${requestScope.txtSearchValue}',
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("content");
                                                        row.innerHTML = data;
                                                    }
                                                });
                                            }
                                            function previousPage() {
                                                var amount = ${requestScope.TAGS};
                                                $.ajax({
                                                    type: "get",
                                                    url: "PagingStaffProduct",
                                                    data: {
                                                        index: amount - 1,
                                                        txtSearchValue: '${requestScope.txtSearchValue}',
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("content");
                                                        row.innerHTML = data;
                                                    }
                                                });
                                            }
                                            function loadPage(param) {
                                                var amount = param;
                                                $.ajax({
                                                    type: "get",
                                                    url: "PagingStaffProduct",
                                                    data: {
                                                        index: amount,
                                                        txtSearchValue: '${requestScope.txtSearchValue}',
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("content");
                                                        row.innerHTML = data;
                                                    }
                                                });
                                            }
>>>>>>> Stashed changes
        </script>


        <footer>
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
        </footer>
    </body>

</html>
