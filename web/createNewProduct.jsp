<%-- 
    Document   : createNewProduct
    Created on : Jun 8, 2023, 2:37:58 AM
    Author     : DucAnh
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .content {
                margin-top: 60px;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }

            <!-- CSS THEM -->
            h1 {
                font-size: 48px;
                font-weight: bold;
                color: #333;
                margin-bottom: 30px;
            }

            body {
                background-color: #f3f3f3;
            }

            .container {
                padding-top: 50px;
                padding-bottom: 50px;
            }

            .card-header {
                background-color: #f8f9fa;
                font-weight: bold;
            }
        </style>
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
            <a href="index.html" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store
                </h1>
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
                    <a href="index.html" class="nav-item nav-link active">Home</a>
                    <a href="blog.html" class="nav-item nav-link">Blog</a>
                    <a href="cart.html" class="nav-item nav-link pt-3 "><i
                            class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown"><i
                                class="bi bi-person fs-1 text-primary me-1"></i>
                        </a>
                        <div class="dropdown-menu m-0 dropdown-menu-end">
                            <a href="details.html" class="dropdown-item">My profile</a>
                            <a href="purchase.html" class="dropdown-item">My purchase</a>
                            <a href="Home.html" class="dropdown-item">Logout</a>

                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->
        <div class="container">
            <div class="d-flex justify-content-center">
                <div class="col-md-7">
                    <!-- Create a new product card-->
                    <div class="col-md-12 text-center mb-5">
                        <h1> Create a new product</h1>
                        
                    </div>
                    <div class="card mb-4">
                        
                        <div class="card-body">
                            <c:set var="category" value="${categoryname}"/>
                            <form action="CreateNewProductServlet" method="post" enctype="multipart/form-data">
                                <!-- Form Group (productName)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtProductName">productName</label>
                                    <input class="form-control" type="text" name="txtProductName" id="txtProductName" required/>
                                </div>
                                <!-- Form Group (price)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtPrice">price</label>
                                    <input class="form-control" type="text" name="txtPrice" id="txtPrice" required/>
                                </div>
                                <!-- Form Group (quantity)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtQuantity">quantity</label>
                                    <input class="form-control" type="number" name="txtQuantity" id="txtQuantity" required/>
                                </div>
                                <!-- Form Group (categoryID)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtCatory">categoryID</label>
                                    <select class="form-control" name="txtCatory">
                                        <c:forEach var="cate" items="${category}">
                                            <option value="${cate.categoryID}">${cate.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <!-- Form Group (productDetail)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtProductDetail">productDetail</label>
                                    <input class="form-control" type="text" name="txtProductDetail" id="txtProductDetail" required/>
                                </div>
                                <!-- Form Group (size)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtSize">size</label>
                                    <input class="form-control" type="text" name="txtSize" id="txtSize" required/>
                                </div>
                                <!-- Form Group (ageRecommendation)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtAgeRecommendation">ageRecommendation</label>
                                    <input class="form-control" type="number" name="txtAgeRecommendation" id="txtAgeRecommendation" required/>
                                </div>
                                <!-- Form Group (date)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtDate">date</label>
                                    <input class="form-control" type="date" name="txtDate" id="txtDate" required/>
                                </div>
                                <!-- Form Group (status)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtStatus">status</label>
                                    <select class="form-control" name="txtStatus">
                                        <option value="1">ON</option>
                                        <option value="0">OFF</option>
                                    </select>
                                </div>
                                <!-- Form Group (country)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtCountry">country</label>
                                    <input class="form-control" type="text" name="txtCountry" id="txtCountry" required/>
                                </div>
                                <!-- Form Group (imgPath)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="txtPicture">imgPath</label>
                                    <input class="form-control" type="file" name="txtPicture" id="txtPicture" required/>
                                </div>
                                <button class="btn btn-primary" type="submit" name="btAction" value="Create New Product servlet">Create</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
