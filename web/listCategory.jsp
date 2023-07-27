<%-- 
    Document   : ListFeedback
    Created on : Jul 10, 2023, 10:34:40 PM
    Author     : DucAnh
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sample.dto.UserDTO" %>
<%@ page import="sample.dao.UserDAO" %>
<%@ page import="sample.dto.ProductDTO" %>
<%@ page import="sample.dao.ProductDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <style>
            .updateButton {
                background-color: #51a351;
                color: #fff;
                border: none;
                border-radius: 16px;
                padding: 5px 10px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .updateButton:hover {
                background-color: #387038;
            }
            .btn-add{
                display: block;
                background-color: #51a351;
                color: #fff;
                border: none;
                border-radius: 16px;
                padding: 5px 10px;
                cursor: pointer;
                transition: background-color 0.3s;
                width: 50px;
                margin-bottom: 10px;
            }
            .btn-add:hover {
                background-color: #387038;
            }
            .tetx-box{
                margin-left: 30px;
                margin-right: 30px;
                margin-bottom: 10px;
            }
        </style>
    </head>

    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->


                <!-- Divider -->
                <hr class="sidebar-divider my-0">
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="MainController?btAction=StaffHome">
                    <div class="sidebar-brand-icon">
                        <i class="fas fa-dove"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Staff <sup></sup></div>
                </a>


                <!-- Divider -->
                <hr class="sidebar-divider">

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

                <!-- Nav Item - Tables -->

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
                    <div class="container-fluid row">
                        <div class="container-fluid col-md-6" id="content">
                            <button class="btn-add" data-toggle="modal" data-target="#AddNewCategoryModal">+</button>
                            <!-- DataTales Example -->
                            <div class="card shadow mb-4" id="category">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Category</h6>
                                </div>
                                <c:set var="categoryList" value="${requestScope.ListCategory}"/>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <c:if test="${not empty categoryList}">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Category</th>
                                                        <th>Status</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    </tr>
                                                    <c:forEach var="category" items="${categoryList}">
                                                        <tr>
                                                            <td>
                                                                ${category.categoryName}
                                                            </td>
                                                            <td>
                                                                <c:if test="${category.status eq 1}">
                                                                    Available
                                                                </c:if>
                                                                <c:if test="${category.status eq 0}">
                                                                    Unavailable
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${category.status eq 1}">
                                                                    <button class="updateButton" onclick="removeCategory(${category.categoryID}, 0)">
                                                                        Unavailable
                                                                    </button>
                                                                </c:if>
                                                                <c:if test="${category.status eq 0}">
                                                                    <button class="updateButton" onclick="removeCategory(${category.categoryID}, 1)">
                                                                        Available
                                                                    </button>
                                                                </c:if>

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

                        <div class="container-fluid col-md-6" id="content">
                            <button class="btn-add" data-toggle="modal" data-target="#AddNewBirdModal">+</button>
                            <div class="card shadow mb-4" id="bird">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Bird</h6>
                                </div>
                                <c:set var="listBird" value="${requestScope.ListBird}"/>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <c:if test="${not empty listBird}">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Bird</th>
                                                        <th>Status</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="bird" items="${listBird}">
                                                        <tr>
                                                            <td>
                                                                ${bird.birdName}
                                                            </td>
                                                            <td>
                                                                <c:if test="${bird.status eq 1}">
                                                                    Available
                                                                </c:if>
                                                                <c:if test="${bird.status eq 0}">
                                                                    Unavailable
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${bird.status eq 1}">
                                                                    <button class="updateButton" onclick="removeBird(${bird.birdID}, 0)">
                                                                        Unavailable
                                                                    </button>
                                                                </c:if>
                                                                <c:if test="${bird.status eq 0}">
                                                                    <button class="updateButton" onclick="removeBird(${bird.birdID}, 1)">
                                                                        Available
                                                                    </button>
                                                                </c:if>

                                                            </td>
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

        <!-- New Bird Modal-->
        <div class="modal fade" id="AddNewBirdModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Create New Bird</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Enter bird name</div>
                    <input id="birdName" class="tetx-box" type="text" name="birdName" value="" placeholder="Enter bird name"/>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" onclick="addNewBird(${birdName})">Create</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- New Category Modal-->
        <div class="modal fade" id="AddNewCategoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Create New Category</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Enter category name</div>
                    <input id="cateName" class="tetx-box" type="text" name="cateName" value="" placeholder="Enter category name"/>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a id="createButton" class="btn btn-primary" onclick="addNewCategory()">Create</a>
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
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>

                           
                            function removeBird(birdID, status) {
                                $.ajax({
                                    type: "POST",
                                    url: "RemoveBird",
                                    data: {
                                        birdID: birdID,
                                        STATUS: status
                                    },
                                    success: function (data) {
                                        if (data === "true") {
                                            Swal.fire({

                                                icon: 'success',
                                                title: 'Successful!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                            $('#bird').load(window.location.href + ' #bird');

                                        } else {
                                            Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                        }
                                    },
                                    error: function () {
                                        Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                    }
                                });
                            }
                            function addNewBird() {
                                $.ajax({
                                    type: "POST",
                                    url: "AddNewBird",
                                    data: {
                                        BIRDNAME: $('#birdName').val()
                                    },
                                    success: function (data) {
                                        $('#birdName').val('');
                                        if (data === "true") {
                                            Swal.fire({

                                                icon: 'success',
                                                title: 'Successful!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                            $('#bird').load(window.location.href + ' #bird');
                                        } else {
                                            Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                        }
                                    },
                                    error: function () {
                                        Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                    }
                                });
                            }
                            function removeCategory(cateID, status) {
                                $.ajax({
                                    type: "POST",
                                    url: "RemoveCategory",
                                    data: {
                                        categoryID: cateID,
                                        STATUS: status
                                    },
                                    success: function (data) {
                                        if (data === "true") {
                                            Swal.fire({

                                                icon: 'success',
                                                title: 'Successful!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                            $('#category').load(window.location.href + ' #category');
                                        } else {
                                            Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                        }
                                        ;
                                    },
                                    error: function () {
                                        Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                    }
                                });
                            }

                            function addNewCategory() {
                                $.ajax({
                                    type: "POST",
                                    url: "AddNewCategory",
                                    data: {
                                        CATEGORYNAME: $('#cateName').val()
                                    },
                                    success: function (data) {
                                        $('#cateName').val('');
                                        if (data === "true") {
                                            Swal.fire({

                                                icon: 'success',
                                                title: 'Successful!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                            $('#category').load(window.location.href + ' #category');
                                            

                                        } else {
                                            Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                        }
                                    },
                                    error: function () {
                                        Swal.fire({

                                                icon: 'error',
                                                title: 'Error!',
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                    }
                                });
                            }
    </script>
</html>
