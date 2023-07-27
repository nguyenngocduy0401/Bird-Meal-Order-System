<%-- 
    Document   : listProductOfStaff
    Created on : Jun 13, 2023, 1:24:01 AM
    Author     : DucAnh
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.user==null||sessionScope.user.role ne 1}">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

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
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="StaffHomeController">
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
                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>
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
                <!-- Page Heading -->
                <div class="container-fluid">
                    <h1 class="h3 mb-2 text-gray-800">PRODUCT LIST</h1>
                    <!--<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                                                                                   href="https://datatables.net">official DataTables documentation
                        </a>.</p>-->
                    <div class="card shadow mb-4">

                        <div class="card-header py-3">
                            <div class="d-flex justify-content-between align-items-center">
                                <form action="MainController">
                                    <button class="btn btn-outline-primary mr-auto" name="btAction" value="Create New Product">Add new product</button>
                                </form>
                                <form action="MainController">
                                    <button class="btn btn-outline-primary mr-auto" name="btAction" value="StaffHome">Show All</button>
                                </form>
                                <form action="MainController">
                                    <div class="form-inline navbar-search">
                                        <div class="input-group">
                                            <input name="txtSearchValue" type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                                   aria-label="Search" aria-describedby="basic-addon2">
                                            <div class="input-group-append">

                                                <button class="btn btn-primary">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                                <input type="hidden" name="btAction" value="SearchOfStaff" />

                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>

                </div>
                <!-- DataTales Example -->
                <div id="content">
                    <div class="container-fluid">



                        <!-- DataTales Example -->
                        <c:set var="result" value="${requestScope.PRODUCTS}" />

                        <div class="card shadow mb-4">

                            <div class="card-body">
                                <div class="table-responsive">
                                    <c:if test="${empty result}">
                                        Vui long them moi san pham!!
                                    </c:if>
                                    <c:if test="${not empty result}">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Image</th>
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
                                                    <th>Image</th>
                                                    <th>Name</th>
                                                    <th>Price</th>
                                                    <th>Quantity</th>
                                                    <th>Category</th>
                                                    <th>Details</th>
                                                    <th>Size</th>
                                                    <th>Age</th>
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
                                                        <td style="white-space: pre-wrap;">${dto.productDetail}</td>
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
                                                                <a href="HideProduct?productID=${dto.productID}&status=0" style="color: white;">
                                                                    Hide
                                                                    <i class="bi bi-trash"></i>
                                                                </a>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
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
                        </div>

                    </div>

                    <!-- /.container-fluid -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                    <script>
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
                    </script>
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

        <!-- Page level plugins -->

        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>
</html>
