<%-- 
    Document   : updateProduct
    Created on : Jun 9, 2023, 12:52:09 PM
    Author     : DucAnh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sample.dao.ProductDAO"%>
<%@page import="sample.dto.ProductDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff - Update product</title>
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
            .product-image {
                max-width: 100%;
                height: auto;
            }
        </style>
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
        <!-- Template new -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!--multi-select scripts css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/css/multi-select-tag.css">
        
        
        <!--multi-select scripts js-->
        <script src="https://cdn.jsdel<sivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>

    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="staff.jsp">
                    <div class="sidebar-brand-icon">
                        <i class="fas fa-dove"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Staff <sup></sup></div>
                </a>

                <li class="nav-item">
                    <form action="MainController">
                        <input type="hidden" name="btAction" value="StaffHome" />
                        <button type="submit" class="nav-link" style="border: none; background: none;">
                            <i class="fas fa-fw fa-table"></i>
                            <span>Product</span>
                        </button>
                    </form>
                </li>

                <li class="nav-item">
                    <form action="ListCategory">

                        <button type="submit" class="nav-link" style="border: none; background: none;">
                            <i class="fas fa-fw fa-table"></i>
                            <span>Category</span>
                        </button>
                    </form>
                </li>

                <li class="nav-item">
                    <form action="ListUnavailable">

                        <button type="submit" class="nav-link" style="border: none; background: none;">
                            <i class="fas fa-fw fa-trash"></i>
                            <span>Trash</span>
                        </button>
                    </form>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a target="_blank" class="nav-link" href="https://birdfoodswp.blogspot.com/">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Blog</span></a>
                </li>

                <!-- Nav Item - reply -->

                <li class="nav-item">
                    <form action="ListFeedBack">
                        <button type="submit" class="nav-link" style="border: none; background: none;">
                            <i class="fas fa-fw fa-reply"></i>
                            <span>Reply</span>
                        </button>
                    </form>
                </li>

                <!-- Nav Item - Orders -->
                <li class="nav-item">
                    <form action="GetOrdersListServlet">
                        <button type="submit" class="nav-link" style="border: none; background: none;">
                            <i class="fas fa-fw fa-clipboard"></i>
                            <span>Order</span></a>
                        </button>
                    </form>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Search -->
                        <div class="navbar-nav ms-left py-0">
                            <a href="staff.jsp" class="nav-item nav-link active">STAFF HOME</a>
                        </div>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">


                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->

                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.fullName}</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="details.jsp">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <a class="dropdown-item" href="MainController?btAction=Home">
                                        <i class="fas fa-home fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Home
                                    </a>
                                    <!--                                <a class="dropdown-item" href="#">
                                                                        <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                                                        Activity Log
                                                                    </a>-->
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>  
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-4 text-gray-800">Update product</h1>

                    </div>
                    <!-- /.container-fluid -->
                    <%--<c:set var="category" value="${categoryName}"/>--%>
                    <c:set var="INFOR" value="${INFOR}"/>
                    <div class="card mb-4">
                        <div class="card-body">
                            <form action="UpdateProductServlet?action=${ACTION}" method="post" enctype="multipart/form-data">
                                <c:set var="errors" value="${requestScope.UPDATE_PRODUCT_ERROR}"/>
                                <div class="form-group row">
                                    <label for="txtProductId" class="col-sm-3 col-form-label">Product ID</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtProductId" value="${INFOR.productID}" id="txtProductId" class="form-control" readonly="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtProductName" class="col-sm-3 col-form-label">Product Name</label>
                                    <div class="col-sm-9">
                                        <c:if test="${not empty errors.productNameLengthError}">
                                            <input type="text" name="txtProductName" value="${param.txtProductName}"
                                                   id="txtProductName" class="form-control" required=""
                                                   placeholder="Enter product name from 3 to 500 characters">
                                            <font color ="red">
                                            ${errors.productNameLengthError}
                                            </font>
                                        </c:if>
                                        <c:if test="${empty errors.productNameLengthError}">
                                            <input type="text" name="txtProductName" value="${INFOR.productName}"
                                                   id="txtProductName" class="form-control" required=""
                                                   placeholder="Enter product name from 3 to 500 characters">
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtPrice" class="col-sm-3 col-form-label">Price(VND)</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtPrice" value="${INFOR.price}" id="txtPrice" class="form-control" required=""
                                               onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))"
                                               placeholder="Enter the price of product (EX: 500,000)">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtQuantity" class="col-sm-3 col-form-label">Quantity</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtQuantity" value="${INFOR.quantity}" id="txtQuantity" class="form-control" required=""
                                               onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))"
                                               placeholder="Enter quantity of product (EX: 100)">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtCategory" class="col-sm-3 col-form-label">Category ID</label>
                                    <div class="col-sm-9">
                                        <select name="txtCategory" class="form-control form-select">
                                            <c:forEach var="cate" items="${sessionScope.categoryname}">
                                                <option value="${cate.categoryID}" ${INFOR.categoryID == cate.categoryID ? 'selected' : ''}>${cate.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtProductDetail" class="col-sm-3 col-form-label">Product Detail</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtProductDetail" value="${INFOR.productDetail}" id="txtProductDetail" class="form-control" required=""
                                               placeholder="Enter product detail from 5 to 2000 characters">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtSize" class="col-sm-3 col-form-label">Size(gram)</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtSize" value="${INFOR.size}" id="txtSize"
                                               placeholder="Enter the number grams of product (Ex: 500)" class="form-control" required="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <c:set var="birds" value="${sessionScope.LIST_BIRD}"/>
                                    <c:set var="birdSelects" value="${requestScope.LISTBIRD}"/>
                                    <label for="txtBird" class="col-sm-3 col-form-label">Bird</label>
                                    <div class="col-sm-9">
                                        <!--<input type="number" name="txtAgeRecommendation" id="txtAgeRecommendation" class="form-control" required="">-->
                                        <c:if test="${empty errors.productCategoriesBirdNotSelect}">
                                            <select name="txtBirds" id="birds" multiple>
                                                <c:forEach var="bird" items="${birds}">
                                                    <option value="${bird.birdID}"
                                                            <c:forEach var="birdSelect" items="${birdSelects}">
                                                                <c:if test="${birdSelect.birdID == bird.birdID}"> selected </c:if>
                                                            </c:forEach>
                                                            >${bird.birdName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${not empty errors.productCategoriesBirdNotSelect}">
                                            <select name="txtBirds" id="birds" class="form-control" multiple>
                                                <c:forEach var="bird" items="${birds}">
                                                    <option value="${bird.birdID}"
                                                            >${bird.birdName}</option>
                                                </c:forEach>
                                            </select>
                                            <font color ="red">
                                            ${errors.productCategoriesBirdNotSelect}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtAgeRecommendation" class="col-sm-3 col-form-label">Age Recommendation(months)</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtAgeRecommendation" value="${INFOR.ageRecommendation}" id="txtAgeRecommendation" class="form-control" required=""
                                               onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))"
                                               placeholder="Enter the number of months suitable for the bird(EX: 12)">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtDate" class="col-sm-3 col-form-label">Date expire</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtDate" value="${INFOR.date}" id="txtDate" class="form-control" required=""
                                               placeholder="Enter number of months expire from the date manufacture (Ex: 12)">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtDateManufacture" class="col-sm-3 col-form-label">Date Manufacture</label>
                                    <div class="col-sm-9">
                                        <input type="date" name="txtDateManufacture" value="${INFOR.getDateManufacture()}" id="txtDateManufacture" class="form-control" required="">
                                    </div>
                                </div>
                                <%--<c:if test="${INFOR.quantity == 0}">
                                    <c:set var="status" value="0"/>
                                </c:if>
                                <c:if test="${INFOR.quantity != 0}">
                                    <c:set var="status" value="${INFOR.status}"/>
                                </c:if>--%>
                                <div class="form-group row">
                                    <label for="txtStatus" class="col-sm-3 col-form-label">Status</label>
                                    <div class="col-sm-9">
                                        <select name="txtStatus" class="form-control form-select">
                                            <option value="1" ${INFOR.status == 1 ? 'selected' : ''}>Available</option>
                                            <option value="0" ${INFOR.status == 0 ? 'selected' : ''}>Not Available</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtCountry" class="col-sm-3 col-form-label">Country</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="txtCountry" value="${INFOR.country}" id="txtCountry" class="form-control" required=""
                                               placeholder="Enter the country made this product (EX: Viet Nam)">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtPicture" class="col-sm-3 col-form-label">Product Image</label>
                                    <div class="col-sm-9">
                                        <img src="${INFOR.imgPath}" alt="Product Image" class="product-image" width="200px"/> </br></br>
                                        <input value="${INFOR.imgPath}" type="file" name="txtPicture" id="txtPicture" class="form-control-file" >
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-sm-12 text-right">
                                        <button type="submit" name="btAction" value="UpdateProduct" class="btn btn-primary">Save</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <script>
                        window.addEventListener("load", function () {
                            var quantity = document.getElementById("txtQuantity");
                            var status = document.getElementsByName("txtStatus")[0];

                            if (quantity.value == 0) {
                                status.value = 0;
                            }
                        });
                    </script>
                    <!-- End of HERE -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="LogoutController">Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!--multi-select scripts js-->
        <script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>
        <script>
                        //multi-select function\
                        new MultiSelectTag('birds');
        </script>
    </body>
</html>
