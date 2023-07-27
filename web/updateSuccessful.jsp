<%-- 
    Document   : newProduct
    Created on : Jun 8, 2023, 1:05:18 PM
    Author     : DucAnh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff</title>
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
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <li class="nav-item">
                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="staff.jsp">
                        <div class="sidebar-brand-icon">
                            <i class="fas fa-dove"></i>
                        </div>
                        <div class="sidebar-brand-text mx-3">Staff <sup></sup></div>
                    </a>

                    <!-- Divider -->
                    <hr class="sidebar-divider my-0">

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

                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                            <li class="nav-item dropdown no-arrow d-sm-none">


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
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Settings
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Activity Log
                                    </a>
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
                        <h1 class="h3 mb-4 text-gray-800">Update a new product successful</h1>

                    </div>
                    <!-- /.container-fluid -->

                    <div class="card mb-4">
                        <div class="card-body">
                            <form>
                                <div class="form-group row">
                                    <label for="txtProductName" class="col-sm-3 col-form-label">Product Name</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.productName}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtPrice" class="col-sm-3 col-form-label">Price</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.price}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtQuantity" class="col-sm-3 col-form-label">Quantity</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.quantity}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtCategory" class="col-sm-3 col-form-label">Category ID</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.categoryID}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtProductDetail" class="col-sm-3 col-form-label">Product Detail</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.productDetail}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtSize" class="col-sm-3 col-form-label">Size</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.size}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtBird" class="col-sm-3 col-form-label">Bird</label>
                                    <div class="col-sm-9">
                                        <c:forEach items="${requestScope.LIST_BIRD}" var="bird">
                                            ${bird.birdName}
                                            <c:if test="${bird.birdName != null}"> | </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtAgeRecommendation" class="col-sm-3 col-form-label">Age Recommendation</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.ageRecommendation}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtDate" class="col-sm-3 col-form-label">Date</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.date} months
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtDateManufacture" class="col-sm-3 col-form-label">Status</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.dateManufacture}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtStatus" class="col-sm-3 col-form-label">Status</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.status}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtCountry" class="col-sm-3 col-form-label">Country</label>
                                    <div class="col-sm-9">
                                        ${updateProduct.country}
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtPicture" class="col-sm-3 col-form-label">Product Image</label>
                                    <div class="col-sm-9">

                                        <img src="${INFOR.imgPath}" alt="Product Image" class="product-image">
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                    <!-- End of HERE -->

                </div>
                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        if (!localStorage.getItem('pageLoaded')) {
                            localStorage.setItem('pageLoaded', true);
                            location.reload();
                        } else {
                            localStorage.removeItem('pageLoaded');
                        }
                    });
                </script>
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

    </body>
</html>
