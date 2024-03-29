<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="sample.dto.UserDTO" %>
<%@ page import="sample.dao.UserDAO" %>
<%@ page import="sample.dao.ReportDAO" %>
<%@ page import="sample.dto.ReportDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .card{
        box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
    }
    .reply{
        margin-bottom: 20px;
        margin-right: 20px; 
        padding-top: 5px;
        padding-bottom: 5px;
        padding-right: 5px;
        padding-left: 5px;
    }
    .fb{
        margin-top: 5px;
    }
    .btn{
        margin-top: 15px;
        margin-bottom: 15px;
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
<html lang="vi">
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
        <link href="css/style.css" rel="stylesheet">
    </head>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://kit.fontawesome.com/afb5d5fd3d.js" crossorigin="anonymous"></script>
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
            <a href="HomePageController" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="fas fa-dove fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="col-md-7 container-fluid" >

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
                                <i class="bi bi-person fs-1  me-1" style="line-height: 0.6"></i>
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
        <!-- Navbar End -->
        <section class="py-7">
            <div class="card container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${productDTO.imgPath}" alt="..." /></div>
                    <div class="col-md-6">
                        <!--                        <div class="small mb-1">SKU: BST-498</div>-->
                        <h1 class="display-5 fw-bolder " >${productDTO.productName}</h1>
                        <div class="fs-5 mb-5">
                            <span class="display-6" style="color: #7AB730;" >${productDTO.price} VND</span>
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
                                                Swal.fire({

                                                    icon: 'success',
                                                    title: 'Successful!',
                                                    showConfirmButton: false,
                                                    timer: 1000
                                                })
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
                                <td><${productDTO.date} months</td>
                            </tr>
                            <tr>
                                <td>MFG</td>
                                <td>${productDTO.dateManufacture}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="mb-5">
                    <h3 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">${amountFeedback} Feedback</h3>
                    <c:forEach items="${requestScope.LIST_FEEDBACK}" var="fb">
                        <c:set var="report" value="${ReportDAO.getReportByFeedbackID(fb.feedbackID)}"/>
                        <c:if test="${report eq 1}">
                            <c:set var="feedbackID" value="${fb.feedbackID}" />
                            <div class="d-flex mb-4 blog-item card">
                                <div class="ps-3 fb">
                                    <h6><a href="">${UserDAO.getUserByID(fb.userID).userName}</a> <small><i>${fb.feedbackDate}</i></small></h6>
                                    <p>Rate:  ${fb.rate} Star</p>
                                    <p>${fb.feedbackDetails}</p>
                                    <c:if test="${not empty fb.replyDetails}">
                                        <div class="ps-3 reply card">
                                            <h6><a class="text-primary">${fb.replyStaff}</a> <small>${fb.replyDate}</small></h6>
                                            <p>${fb.replyDetails}</p>
                                        </div>
                                    </c:if>
                                    <c:if test="${sessionScope.user.role eq 1}">
                                        <c:if test="${empty fb.replyDetails}">
                                            <textarea name="detailsReply" id="details" class="card form-control bg-light border-0 px-4 py-3" placeholder="Details"></textarea>
                                            <c:set var="REPLYDETAILS" value="${detailsReply}" />
                                            <button class="btn btn-primary py-3" type="submit" onclick="replyFeedback(${feedbackID},${REPLYDETAILS})">Reply</button>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <!-- Comment List End -->
            </div>

        </div>
    </div>

</section>
<script>
    function replyFeedback(fbID) {
        $.ajax({
            type: "post",
            url: "FeedbackReply",
            data: {
                feedbackID: fbID,
                details: $('#details').val()
            },
            success: function () {
                Swal.fire({

                    icon: 'success',
                    title: 'Successful!',
                    showConfirmButton: false,
                    timer: 1000
                });
                location.reload();
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