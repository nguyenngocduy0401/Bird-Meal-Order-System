<<<<<<< HEAD
<%-- 
    Document   : home
    Created on : May 27, 2023, 12:48:39 PM
    Author     : Duy
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <c:set var="result" value="${requestScope.PRODUCTS}" />
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


        <!-- Navbar Start -->
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
                    <a href="cart.html" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                    <a href="Login.html" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->


        <section class=" col-centered col-md-9 mt-5 mx-auto ">
            <c:forEach var="dto" items="${result}">
            <div class="row product-list">
                <div class="col-md-4 mt-1">
                    <section class="panel">
                        <div class="product-item position-relative bg-light d-flex flex-column text-center">
                            <img class="img-fluid mb-4" src="https://reviewaz.vn/storage/thuc-an-cho-chim.png" alt="">
                            <h6 class="text-uppercase">${dto.productName}</h6>
                            <h5 class="text-primary mb-0">${dto.Price} VND</h5>
                            <div class="btn-action d-flex justify-content-center">
                                <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-cart"></i></a>
                                <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-eye"></i></a>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
            </c:forEach>
        </section>
        <div class="col-12 mt-5">
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-lg m-0">
                    <li class="page-item disabled">
                        <a class="page-link rounded-0" href="#" aria-label="Previous">
                            <span aria-hidden="true"><i class="bi bi-arrow-left"></i></span>
                        </a>
                    </li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link rounded-0" href="#" aria-label="Next">
                            <span aria-hidden="true"><i class="bi bi-arrow-right"></i></span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div> 
    </div>
</html>
=======
<%-- 
    Document   : home
    Created on : May 27, 2023, 12:48:39 PM
    Author     : Duy
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


    <!-- Navbar Start -->
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
                <a href="cart.html" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                <a href="Login.html" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->
    

<section class=" col-centered col-md-9 mt-5 mx-auto ">
        <div class="row product-list">
            <div class="col-md-4 mt-1">
                <section class="panel">
                    <div class="product-item position-relative bg-light d-flex flex-column text-center">
                        <img class="img-fluid mb-4" src="https://reviewaz.vn/storage/thuc-an-cho-chim.png" alt="">
                        <h6 class="text-uppercase">Quality Pet Foods</h6>
                        <h5 class="text-primary mb-0">$199.00</h5>
                        <div class="btn-action d-flex justify-content-center">
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-cart"></i></a>
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-eye"></i></a>
                        </div>
                    </div>
                </section>
            </div>
            <div class="col-md-4 mt-1">
                <section class="panel">
                    <div class="product-item position-relative bg-light d-flex flex-column text-center">
                        <img class="img-fluid mb-4" src="https://reviewaz.vn/storage/thuc-an-cho-chim.png" alt="">
                        <h6 class="text-uppercase">Quality Pet Foods</h6>

                        <h5 class="text-primary mb-0">$199.00</h5>
                        <div class="btn-action d-flex justify-content-center">
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-cart"></i></a>
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-eye"></i></a>
                        </div>
                    </div>
                </section>
            </div>
            <div class="col-md-4 mt-1">
                <section class="panel">
                    <div class="product-item position-relative bg-light d-flex flex-column text-center">
                        <img class="img-fluid mb-4" src="https://reviewaz.vn/storage/thuc-an-cho-chim.png" alt="">
                        <h6 class="text-uppercase">Quality Pet Foods</h6>
                        <h5 class="text-primary mb-0">$199.00</h5>
                        <div class="btn-action d-flex justify-content-center">
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-cart"></i></a>
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-eye"></i></a>
                        </div>
                    </div>
                </section>
            </div>
            <div class="col-md-4 mt-1">
                <section class="panel">
                    <div class="product-item position-relative bg-light d-flex flex-column text-center">
                        <img class="img-fluid mb-4" src="https://reviewaz.vn/storage/thuc-an-cho-chim.png" alt="">
                        <h6 class="text-uppercase">Quality Pet Foods</h6>
                        <h5 class="text-primary mb-0">$199.00</h5>
                        <div class="btn-action d-flex justify-content-center">
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-cart"></i></a>
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-eye"></i></a>
                        </div>
                    </div>
                </section>
            </div>
            <div class="col-md-4 mt-1">
                <section class="panel">
                    <div class="product-item position-relative bg-light d-flex flex-column text-center">
                        <img class="img-fluid mb-4" src="https://reviewaz.vn/storage/thuc-an-cho-chim.png" alt="">
                        <h6 class="text-uppercase">Quality Pet Foods</h6>
                        <h5 class="text-primary mb-0">$199.00</h5>
                        <div class="btn-action d-flex justify-content-center">
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-cart"></i></a>
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-eye"></i></a>
                        </div>
                    </div>
                </section>
            </div>
            <div class="col-md-4 mt-1">
                <section class="panel">
                    <div class="product-item position-relative bg-light d-flex flex-column text-center">
                        <img class="img-fluid mb-4" src="https://reviewaz.vn/storage/thuc-an-cho-chim.png" alt="">
                        <h6 class="text-uppercase">Quality Pet Foods</h6>
                        <h5 class="text-primary mb-0">$199.00</h5>
                        <div class="btn-action d-flex justify-content-center">
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-cart"></i></a>
                            <a class="btn btn-primary py-2 px-3" href=""><i class="bi bi-eye"></i></a>
                        </div>
                    </div>

                </section> 
                <div class="col-12 mt-5">
                    <nav aria-label="Page navigation">
                      <ul class="pagination pagination-lg m-0">
                        <li class="page-item disabled">
                          <a class="page-link rounded-0" href="#" aria-label="Previous">
                            <span aria-hidden="true"><i class="bi bi-arrow-left"></i></span>
                          </a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                          <a class="page-link rounded-0" href="#" aria-label="Next">
                            <span aria-hidden="true"><i class="bi bi-arrow-right"></i></span>
                          </a>
                        </li>
                      </ul>
                    </nav>
                </div>    
        </div>
    </div>
</div>
</html>
>>>>>>> 7cfde488bb6f9c58c900bddb032101e309cddbca
