<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bird Meal Order System</title>
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
                        <a href="MainController?btAction=Home" class="nav-item nav-link active">Home</a>
                        <a href="blog.html" class="nav-item nav-link">Blog</a>
                        <a href="cart.html" class="nav-item nav-link pt-3 "><i
                                class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                        <a href="edit.html" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Profile <i
                                class="bi bi-arrow-down"></i></a>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->
        </header>
        
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <div class="navbar-collapse">
                <a href="addnew.html" class="nav-item nav-link active">Add new product</a>
                <a href="MainController?btAction=StaffHome" class="nav-item nav-link">Product list</a>
                <a href="MainController?btAction=StaffOrderHome" class="nav-item nav-link">Order list</a>
                <a href="feedback.html" class="nav-item nav-link">Feedback</a>

            </div>
        </nav>
        
        <h1 class="h123">Order list</h1>

        <div class="d-flex form-inputs" id="class">
            <form action="MainController">
                <input type="text" placeholder="Search any product...">
                <i class="bx bx-search"></i>
                <button  name="btAction" value="StaffOrderHome">List All</button>
            </form>
        </div>
        <c:set var="result" value="${requestScope.ORDERS}" />
        <c:if test="${empty result}" >
            Khong co order
        </c:if>
        <c:if test="${not empty result}" >
            <table border="1">
                    <thead style="background: #7AB730; color: white">
                        <tr>
                            <th>User</th>
                            <th>Order Day</th>
                            <th>Shipping day</th>
                            <th>Status</th>
                            <th>Order Address</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id = "content">
                        <c:forEach var="dto" items="${result}">
                            <tr>
                                <td>${dto.userID}</td>
                                <td>${dto.orderDate}</td>
                                <td>${dto.shippingDate}</td>
                                <td>${dto.status}</td>
                                <td>${dto.orderAddress}</td>
                                <td>
                                    <button>Delete</button>
                                    <button>Edit</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
        </c:if>
    <div class="col-12 mt-5">
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
                    <c:if test="${TAGS >= 1 && TAGS < PAGE}">
                        <li class="page-item">
                            <a onclick="nextPage()" class="page-link rounded-0" aria-label="Next">
                                <span aria-hidden="true"><i class="bi bi-arrow-right"></i></span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
                                        function nextPage() {
                                            var amount = ${requestScope.TAGS};

                                            $.ajax({
                                                type: "get",
                                                url: "pagingOrder",
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
                                                url: "pagingOrder",
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
                                                url: "pagingOrder",
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
