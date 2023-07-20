<%-- 
    Document   : homePage
    Created on : Jul 17, 2023, 12:12:47 AM
    Author     : Duy
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <c:set var="result" value="${requestScope.PRODUCTS}" />
    <c:set var="cateList" value="${requestScope.CATEGORY_LIST}"/>
    <c:set var="sizeList" value="${requestScope.SIZE_LIST}"/>
    <c:set var="birdList" value="${requestScope.BIRD_LIST}"/>
    <c:set var="top5sale" value="${requestScope.TOP5SALE}"/>
    <c:set var="top5new" value="${requestScope.TOP5NEW}"/>

    <head>
        <meta charset="utf-8">
        <title>PET SHOP - Pet Shop Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

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
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
        <!-- Topbar End -->

        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="HomePageController" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <div class="col-md-7 container-fluid">

                </div>
                <div class="navbar-nav ms-auto py-0">
                    <a href="HomePageController" class="nav-item nav-link active ">Home</a>
                    <a href="MainController?btAction=Home" class="nav-item nav-link ">Product</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3  ">
                        <i class="bi bi-cart  fs-1 me-1" style="line-height: 0.6"></i>
                        <span class="position-absolute top-10 left-100 translate-middle badge rounded-pill bi bg-light text-primary" id="reloadNumberCart">${sessionScope.countItemsCart}</span>
                    </a>
                    <c:if test="${not empty sessionScope.user}">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown">
                                <i class="bi bi-person fs-1 me-1" style="line-height: 0.6"></i>
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


        <!-- Hero Start -->
        <div class="container-fluid bg-primary py-5 mb-5 hero-header">
            <div class="container py-5">
                <div class="row justify-content-start">
                    <div class="col-lg-8 text-center text-lg-start">
                        <h1 class="display-1 text-uppercase text-dark mb-lg-4">Food Bird Shop</h1>
                        <h1 class="text-uppercase text-white mb-lg-4">Product For Your Bird </h1>
                        <p class="fs-4 text-white mb-lg-4">At Food Bird Shop, we offer a wide range of products for your bird's needs. Our selection includes high-quality bird food, treats, and accessories to keep your feathered friend happy and healthy.</p>
                        <div class="d-flex align-items-center justify-content-center justify-content-lg-start pt-5">

                            <button type="button" class="btn-play" data-bs-toggle="modal"
                                    data-src="https://www.youtube.com/embed/a-EPSh0pWHw" data-bs-target="#videoModal">
                                <span></span>
                            </button>
                            <h5 class="font-weight-normal text-white m-0 ms-4 d-none d-sm-block">Play Video</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Hero End -->


        <!-- Video Modal Start -->
        <div class="modal fade" id="videoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content rounded-0">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Youtube Video</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- 16:9 aspect ratio -->
                        <div class="ratio ratio-16x9">
                            <iframe class="embed-responsive-item" src="" id="video" allowfullscreen allowscriptaccess="always"
                                    allow="autoplay"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Video Modal End -->


        <!-- About Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="row gx-5">
                    <div class="col-lg-5 mb-5 mb-lg-0" style="min-height: 500px;">
                        <div class="position-relative h-100">
                            <img class="position-absolute w-100 h-100 rounded" src="img/about.jpg" style="object-fit: cover;">
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="border-start border-5 border-primary ps-5 mb-5">
                            <h6 class="text-primary text-uppercase">About Us</h6>
                            <h1 class="display-5 text-uppercase mb-0">Your destination for quality bird products and guidance</h1>
                        </div>
                        <h4 class="text-body mb-4">We specialize in providing high-quality products and expert guidance for bird owners who want to ensure the health and happiness of their feathered friends.</h4>
                        <div class="bg-light p-4">
                            <ul class="nav nav-pills justify-content-between mb-3" id="pills-tab" role="tablist">
                                <li class="nav-item w-50" role="presentation">
                                    <button class="nav-link text-uppercase w-100 active" id="pills-1-tab" data-bs-toggle="pill"
                                            data-bs-target="#pills-1" type="button" role="tab" aria-controls="pills-1"
                                            aria-selected="true">Our Mission</button>
                                </li>
                                <li class="nav-item w-50" role="presentation">
                                    <button class="nav-link text-uppercase w-100" id="pills-2-tab" data-bs-toggle="pill"
                                            data-bs-target="#pills-2" type="button" role="tab" aria-controls="pills-2"
                                            aria-selected="false">Our Vission</button>
                                </li>
                            </ul>
                            <div class="tab-content" id="pills-tabContent">
                                <div class="tab-pane fade show active" id="pills-1" role="tabpanel" aria-labelledby="pills-1-tab">
                                    <p class="mb-0">Our mission is to offer a wide range of top-notch products exclusively for birds, along with comprehensive care guidelines to support bird owners in providing the best possible care for their avian companions. We aim to be a trusted source where bird enthusiasts can find everything they need to meet their birds' nutritional, entertainment, and comfort requirements.</p>
                                </div>
                                <div class="tab-pane fade" id="pills-2" role="tabpanel" aria-labelledby="pills-2-tab">
                                    <p class="mb-0">Our vision is to be the go-to destination for bird owners, providing them with exceptional products and expert guidance to enhance their relationship with their avian pets. We strive to create a supportive community where bird owners can share knowledge and experiences while receiving personalized recommendations and reliable information. Our ultimate goal is to contribute to the well-being and longevity of birds in loving homes.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- About End -->


        <!-- Products Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Latest Products</h6>
                    <h1 class="display-5 text-uppercase mb-0">Our latest products at Food Bird Shop</h1>
                </div>
                <div class="owl-carousel product-carousel">
                    <c:forEach var="dto" items="${top5new}">
                        <div class=" cards pb-5">
                            <div class="product-item position-relative bg-light d-flex flex-column text-center">
                                <section class="panel">
                                    <div class="clickable" onclick="document.getElementById('formid_top5new${dto.productID}').submit()">
                                        <div class="product-item card product-item position-relative bg-light d-flex flex-column text-center product">
                                            <img class="img-fluid mb-3" src="${dto.imgPath}" alt="">
                                            <p class="name text-uppercase">${dto.productName}</p>
                                            <h5 class="text-primary mb-0">${dto.price} VND</h5>
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
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- Products End -->


        <!-- Products Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Bestselling Products</h6>
                    <h1 class="display-5 text-uppercase mb-0">Our bestselling products at Food Bird Shop</h1>
                </div>
                <div class="owl-carousel product-carousel">
                    <c:forEach var="dto" items="${top5sale}">
                        <div class=" cards pb-5">
                            <div class="product-item position-relative bg-light d-flex flex-column text-center">
                                <section class="panel">
                                    <div class="clickable" onclick="document.getElementById('formid_top5new${dto.productID}').submit()">
                                        <div class="product-item card product-item position-relative bg-light d-flex flex-column text-center product">
                                            <img class="img-fluid mb-3" src="${dto.imgPath}" alt="">
                                            <p class="name text-uppercase">${dto.productName}</p>
                                            <h5 class="text-primary mb-0">${dto.price} VND</h5>
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
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
        <!-- Products End -->

        <!-- Blog Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Latest Blog</h6>
                    <h1 class="display-5 text-uppercase mb-0">Latest Articles From Our Blog Post</h1>
                </div>
                <div class="row g-5 ">
                    <div class="col-lg-6 latest-post" id="latest-post-1">
                        <div class="blog-item">
                            <div class="row g-0 bg-light overflow-hidden">
                                <div class="col-12 col-sm-5 h-100">
                                    <img class="img-fluid h-100" id="image1" src="" style="object-fit: cover;">
                                </div>
                                <div class="col-12 col-sm-7 h-100 d-flex flex-column justify-content-center">
                                    <div class="p-4">
                                        <div class="d-flex mb-3">
                                            <small class="me-3"><i class="bi bi-bookmarks me-2 " id="author1"></i></small>
                                            <small><i class="bi bi-calendar-date me-2" id="date1"></i></small>
                                        </div>
                                        <h5 class="text-uppercase mb-3" id="title1"></h5>
<!--                                        <p id="content1"></p>-->
                                        <a class="text-primary text-uppercase" id="src1" href="">Read More<i class="bi bi-chevron-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 latest-post" id="latest-post-1">
                        <div class="blog-item">
                            <div class="row g-0 bg-light overflow-hidden">
                                <div class="col-12 col-sm-5 h-100">
                                    <img class="img-fluid h-100" id="image2" src="" style="object-fit: cover;">
                                </div>
                                <div class="col-12 col-sm-7 h-100 d-flex flex-column justify-content-center">
                                    <div class="p-4">
                                        <div class="d-flex mb-3">
                                            <small class="me-3"><i class="bi bi-bookmarks me-2 " id="author2"></i></small>
                                            <small><i class="bi bi-calendar-date me-2" id="date2"></i></small>
                                        </div>
                                        <h5 class="text-uppercase mb-3" id="title2"></h5>
<!--                                        <p id="content2"></p>-->
                                        <a class="text-primary text-uppercase" id="src2" href="">Read More<i class="bi bi-chevron-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Blog End -->

        <script>

            fetch('https://www.googleapis.com/blogger/v3/blogs/514344581023978724/posts?orderBy=published&maxResults=2&key=AIzaSyAMYdqn9G5i1dDLZDwm3QpuGxIDMTmuU7s')
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        displayLatestPosts(data.items);
                    })
                    .catch(error => {
                        console.error('Error fetching blog posts:', error);
                    });

            function displayLatestPosts(posts) {
                const title1 = document.getElementById('title1');
                const content1 = document.getElementById('content1');
                const date1 = document.getElementById('date1');
                const author1 = document.getElementById('author1');
                const image1 = document.getElementById('image1');
                const src1 = document.getElementById('src1');
                

                const title2 = document.getElementById('title2');
                const content2 = document.getElementById('content2');
                const date2 = document.getElementById('date2');
                const author2 = document.getElementById('author2');
                const image2 = document.getElementById('image2');
                const src2 = document.getElementById('src2');

                const post1 = posts[0];
                const post2 = posts[1];


                const regex = /<img[^>]+src="([^">]+)"/i;
                const match =  post1.content.match(regex);
                var extractedURL;
                var extractedURL2;
                if (match && match[1]) {
                    extractedURL = match[1];
                    console.log(extractedURL); 
                } else {
                    console.log("URL not found in the content.");
                }
                
                const match2 =  post2.content.match(regex);
                var extractedURL2;
                if (match2 && match2[1]) {
                    extractedURL2 = match2[1];
                    console.log(extractedURL2); 
                } else {
                    console.log("URL not found in the content.");
                }
                
                title1.textContent = post1.title;
//                content1.textContent = post1.content.slice(0, 100) + "..."; // Add ellipsis to indicate truncated content
                date1.textContent = post1.published;
                author1.textContent = post1.author.displayName;
                image1.src = extractedURL;
                src1.href = post1.url;
                

                title2.textContent = post2.title;
//                content2.textContent = post2.content.slice(0, 100) + "..."; // Add ellipsis to indicate truncated content
                date2.textContent = post2.published;
                author2.textContent = post2.author.displayName;
                image2.src = extractedURL2;
                src2.href = post.url;
            }
        </script>
    </body>
</html>
<!-- Footer Start -->
<div class="container-fluid bg-light mt-5 py-5">
    <div class="container pt-5">
        <div class="row g-5">
            <div class="col-lg-3 col-md-6">
                <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Get In Touch</h5>
                <p class="mb-4">No dolore ipsum accusam no lorem. Invidunt sed clita kasd clita et et dolor sed dolor</p>
                <p class="mb-2"><i class="bi bi-geo-alt text-primary me-2"></i>123 Street, New York, USA</p>
                <p class="mb-2"><i class="bi bi-envelope-open text-primary me-2"></i>info@example.com</p>
                <p class="mb-0"><i class="bi bi-telephone text-primary me-2"></i>+012 345 67890</p>
            </div>
            <div class="col-lg-3 col-md-6">
                <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Quick Links</h5>
                <div class="d-flex flex-column justify-content-start">
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Home</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>About Us</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Our Services</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Meet The Team</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Latest Blog</a>
                    <a class="text-body" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Contact Us</a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Popular Links</h5>
                <div class="d-flex flex-column justify-content-start">
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Home</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>About Us</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Our Services</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Meet The Team</a>
                    <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Latest Blog</a>
                    <a class="text-body" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Contact Us</a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Newsletter</h5>
                <form action="">
                    <div class="input-group">
                        <input type="text" class="form-control p-3" placeholder="Your Email">
                        <button class="btn btn-primary">Sign Up</button>
                    </div>
                </form>
                <h6 class="text-uppercase mt-4 mb-3">Follow Us</h6>
                <div class="d-flex">
                    <a class="btn btn-outline-primary btn-square me-2" href="#"><i class="bi bi-twitter"></i></a>
                    <a class="btn btn-outline-primary btn-square me-2" href="#"><i class="bi bi-facebook"></i></a>
                    <a class="btn btn-outline-primary btn-square me-2" href="#"><i class="bi bi-linkedin"></i></a>
                    <a class="btn btn-outline-primary btn-square" href="#"><i class="bi bi-instagram"></i></a>
                </div>
            </div>
            <div class="col-12 text-center text-body">
                <a class="text-body" href="">Terms & Conditions</a>
                <span class="mx-1">|</span>
                <a class="text-body" href="">Privacy Policy</a>
                <span class="mx-1">|</span>
                <a class="text-body" href="">Customer Support</a>
                <span class="mx-1">|</span>
                <a class="text-body" href="">Payments</a>
                <span class="mx-1">|</span>
                <a class="text-body" href="">Help</a>
                <span class="mx-1">|</span>
                <a class="text-body" href="">FAQs</a>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid bg-dark text-white-50 py-4">
    <div class="container">
        <div class="row g-5">
            <div class="col-md-6 text-center text-md-start">
                <p class="mb-md-0">&copy; <a class="text-white" href="#">Your Site Name</a>. All Rights Reserved.</p>
            </div>
            <div class="col-md-6 text-center text-md-end">
                <p class="mb-0">Designed by <a class="text-white" href="https://htmlcodex.com">HTML Codex</a></p>
            </div>
        </div>
    </div>
</div>
<!-- Footer End -->


<!-- Back to Top -->
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

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
</body>

</html>
