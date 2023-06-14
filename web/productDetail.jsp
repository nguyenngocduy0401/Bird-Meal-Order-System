<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
    .card{
        box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
    }
</style>
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


<body>
    <!-- Topbar Start -->
    <div class="container-fluid border-bottom d-none d-lg-block">
        <div class="row gx-0">
            <div class="col-lg-3 text-center py-2">
                <div class="d-inline-flex align-items-center">
                    <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                    <div class="text-start">
                        <h6 class="text-uppercase mb-1">??a Ch?</h6>
                        <span>Lô E2a-7, ???ng D1, ?. D1, Long Th?nh M?, Thành Ph? Th? ??c</span>
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
                <a href="Home.jsp" class="nav-item nav-link active">Home</a>
                <a href="blog.html" class="nav-item nav-link">Blog</a>
                <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                <a href="Login.jsp" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->
    <section class="py-7">
        <div class="card container px-4 px-lg-5 my-5">
            <div class="row gx-4 gx-lg-5 align-items-center">
                <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${productDTO.imgPath}" alt="..." /></div>
                <div class="col-md-6">
                    <!--                        <div class="small mb-1">SKU: BST-498</div>-->
                    <h1 class="display-5 fw-bolder " >${productDTO.productName}</h1>
                    <div class="fs-5 mb-5">
                        <span class="display-6" style="color: #7AB730;" >$${productDTO.price}</span>
                    </div>
                    <div></div>
                    <div class="lead">${productDTO.productDetail}</div>

                    <div class="d-flex">
                        <c:if test="${productDTO.status eq '0'}">
                            <button class="btn btn-primary flex-shrink-0" type="button">
                                <i class="bi bi-cart-fill me-1 "></i>
                                Out Of 
                                Stock </button>
                            </c:if>
                            <c:if test="${productDTO.status eq '1'}">

                            <button type="submit" value="Add" onclick="addToCart(${productDTO.productID})" class="btn btn-primary py-2 px-3" type="button">
                                                        <i class="bi bi-cart-fill me-1 "></i>
                                                   Add to cart </button>
                        </c:if>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
                                                
                                                function addToCart(param) {
                                                    var id = param;
                                                    $.ajax({
                                                        type: "post",
                                                        url: "AddToCartController",
                                                        data: {
                                                            pid: id,
                                                        },
                                                        success: function () {
                                                        }
                                                    });
                                                }
        </script>
                    </div>
                </div>
            </div>
            <h3 class="box-title mt-5">General Info</h3>
            <div class="table-responsive">
                <table class="table table-striped table-product">
                    <tbody>
                        <tr>
                            <td width="390">Country</td>
                            <td>${productDTO.country}</td>
                        </tr>
                        <tr>
                            <td>Category</td>
                            <td>${categoryDTO.categoryName}</td>
                        </tr>
                        <tr>
                            <td>Bird</td>
                            <td><c:forEach items="${listBird}" var="bird">
                                    ${bird.birdName}
                                    <c:if test="${bird.birdName != null}"> | </c:if>
                                </c:forEach></td>
                        </tr>
                        <tr>
                            <td>Age</td>
                            <td>>${productDTO.ageRecommendation} months</td>
                        </tr>
                        <tr>
                            <td>Size</td>
                            <td>${productDTO.size}</td>
                        </tr>
                         <tr>
                            <td>EXP</td>
                            <td>${productDTO.date} months</td>
                        </tr>
                    </tbody>
                </table>
            </div>
                        <div class="mb-5">
                    <h3 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">3 Comments</h3>
                    <div class="d-flex mb-4">
                        <img src="img/avata.jpg" class="img-fluid" style="width: 45px; height: 45px;">
                        <div class="ps-3">
                            <h6><a href="">Customer</a> <small><i>01 Jan 2045</i></small></h6>
                            <p>Noi dung danh gia chat luong san pham</p>
                            <button class="btn btn-sm btn-light">Reply</button>
                        </div>
                    </div>
                    <div class="d-flex mb-4">
                        <img src="img/avata.jpg" class="img-fluid" style="width: 45px; height: 45px;">
                        <div class="ps-3">
                            <h6><a href="">Customer</a> <small><i>01 Jan 2045</i></small></h6>
                            <p>Noi dung danh gia chat luong san pham</p>
                            <button class="btn btn-sm btn-light">Reply</button>
                        </div>
                    </div>
                    <div class="d-flex ms-5 mb-4">
                        <img src="img/avata.jpg" class="img-fluid" style="width: 45px; height: 45px;">
                        <div class="ps-3">
                            <h6><a href="">Staff</a> <small><i>01 Jan 2045</i></small></h6>
                            <p>Noi dung phan hoi den tu staff</p>
                            <button class="btn btn-sm btn-light">Reply</button>
                        </div>
                    </div>
                </div>
                <!-- Comment List End -->
            </div>

        </div>
    </div>
    
</section>