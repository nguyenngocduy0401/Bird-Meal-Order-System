
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Staff</title>
        <link href="img/icon.png" rel="icon">
        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>

    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="MainController?btAction=StaffHome">
                    <div class="sidebar-brand-icon">
                        <i class="fas fa-dove"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Staff <sup></sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">
                <!-- Nav Item - Tables -->
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
                        <form class="form-inline">
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>
                        </form>

                        <!-- Topbar Search -->


                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">
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
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <c:set var="result" value="${requestScope.listUnavailable}"/>
                                    <c:if test="${not empty result}">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
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
                                            <tfoot>
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
                                            </tfoot>
                                            <tbody>
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
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${dto.status == '0'}">
                                                                    <span class="status-icon text-danger"><i class="fas fa-times"></i></span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                    <span class="status-icon text-success"><i class="fas fa-check"></i></span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                        </td>
                                                        <td>${dto.country}</td>
                                                        <td>
                                                            <button class="btn btn-outline-primary">
                                                                <a href="UpdateProductServlet?action=edit&txtProductId=${dto.productID}">
                                                                    Edit
                                                                    <i class="bi bi-pencil"></i>
                                                                </a>
                                                            </button>

                                                            <button class="btn btn-danger">
                                                                <a href="DeleteProduct?productID=${dto.productID}" style="color: white;">
                                                                    Delete
                                                                    <i class="bi bi-trash"></i>
                                                                </a>
                                                            </button>
                                                            <!--<button class="btn btn-outline-danger"><a href="HideProduct?productID=${dto.productID}&status=0">Hide</a><i class="bi bi-trash"></i></button>-->
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->

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
    </body>
</html>
