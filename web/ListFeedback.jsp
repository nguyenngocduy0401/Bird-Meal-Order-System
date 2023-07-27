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
<%@ page import="sample.dao.ReportDAO" %>
<%@ page import="sample.dto.ReportDTO" %>
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
        <style>
            .updateButton {
                background-color: #51a351;
                color: #fff;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .updateButton:hover {
                background-color: #387038;
            }
            .details{
                padding-left: 50px;
                padding-top: 30px;
                padding-right: 30px;
                margin-left: 30px;
                margin-right: 30px;
                margin-top: 30px;
            }
            #content h1{
                margin-left: 30px;
                margin-right: 30px;
            }
            .btn-reply{
                background: #51a351;
                border: none;
                border-radius: 16px;
                width: 100px;
                height: 50px;
                margin-top: 20px;
                margin-left: 10px;
                margin-bottom: 20px;
                color: white;
            }
            .btn-back{
                background: none;
                border: none;
                border-radius: 16px;
                width: 70px;
                height: 30px;
                margin-left: 50px;
                margin-top: 30px;
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
                    <div class="container-fluid" id="content">
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <c:set var="result" value="${requestScope.ListFeedback}"/>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <c:if test="${empty result}">
                                        <h5>Không có feed back được tìm thấy</h5>
                                        </c:if>
                                    <c:if test="${not empty result}">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>User</th>
                                                    <th>Product</th>
                                                    <th>FeedBack Detail</th>
                                                    <th>Feedback Date</th>
                                                    <th>Rate</th>
                                                    <th>Action</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="dto" items="${result}">
                                                    <c:if test="${empty dto.replyDetails}">
                                                        <tr>
                                                            <td>
                                                                ${UserDAO.getUserByID(dto.userID).userName}
                                                            </td>
                                                            <td>
                                                                ${ProductDAO.getProductByID(dto.productID).productName}
                                                            </td>
                                                            <td>
                                                                ${dto.feedbackDetails}
                                                            </td>
                                                            <td>
                                                                ${dto.feedbackDate}
                                                            </td>
                                                            <td>
                                                                ${dto.rate}
                                                            </td>
                                                            <td>
                                                                <c:set var="report" value="${ReportDAO.getReportByFeedbackID(dto.feedbackID)}"/>
                                                                <button class="updateButton" data-feedbackid="${dto.feedbackID}" onclick="feedbackDetails(${dto.feedbackID})">
                                                                    <i class="fa">Details</i>
                                                                </button>
                                                                <c:if test="${report eq 1}">
                                                                    <button class="updateButton" onclick="reportFeedback(${dto.feedbackID})">
                                                                        <i class="fa">Report</i>
                                                                    </button>
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:if>
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

        <!-- Report Modal-->
        <div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Give report to Admin</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Make sure you want to give report to Admin</div>
                    <div class="modal-body">Click "Yes" to give report to Admin</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" onclick="reportFeedback()">Yes</a>
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
                            $(document).ready(function () {
                                // Xử lý sự kiện khi nhấn nút cập nhật
                                $('.updateButton').click(function () {
                                    var feedbackID = $(this).data('feedbackid');
                                    var details = $(this).closest('tr').find('.detailsInput').val();

                                    updateFeedback(feedbackID, details);
                                });
                            });

                            function updateFeedback(feedbackID, details) {
                                $.ajax({
                                    url: 'ReplyFeedbackStaff',
                                    type: 'GET',
                                    data: {
                                        feedbackID: feedbackID,
                                        details: details
                                    },
                                    dataType: 'json',
                                    success: function (response) {
                                        // Cập nhật danh sách phản hồi trên trang
                                        if (response) {
                                            // Thực hiện các hành động sau khi cập nhật thành công
                                            alert('Cập nhật thành công!');
                                        } else {
                                            // Thông báo lỗi khi cập nhật không thành công
                                            alert('Cập nhật không thành công!');
                                        }
                                    },
                                    error: function (xhr, status, error) {
                                        console.log(error);
                                    }
                                });
                            }
                            function feedbackDetails(param) {
                                $.ajax({
                                    type: "post",
                                    url: "FeedbackDetails",
                                    data: {
                                        feedbackID: param
                                    },
                                    success: function (data) {
                                        var row = document.getElementById("content");
                                        row.innerHTML = data;
                                        $('#details').load(window.location.href + ' #details');
                                    }
                                });
                            }
                            function reportFeedback(fbID) {
                                $.ajax({
                                    type: "get",
                                    url: "ReportFeedback",
                                    data: {
                                        feedbackID: fbID
                                    },
                                    success: function (data) {
                                        var row = document.getElementById("content");
                                        row.innerHTML = data;
                                    }
                                });
                            }
                            function replyFeedback(fbID) {

                                $.ajax({
                                    type: "post",
                                    url: "FeedbackReply",
                                    data: {
                                        feedbackID: fbID,
                                        details: $('#details').val()
                                    },
                                    success: function () {
                                        alert("Reply thanh cong!");
                                        location.reload();
                                    },
                                    error: function () {
                                        alert("Reply that bai!");
                                        location.reload();
                                    },

                                });
                            }
                            function giveReportFeedback(fbID) {
                                let elements = document.getElementsByName('rating');
                                let len = elements.length;
                                let checkValue = '';

                                for (let i = 0; i < len; i++) {
                                    if (elements.item(i).checked) {
                                        checkValue = elements.item(i).value;
                                    }
                                }
                                $.ajax({
                                    type: "post",
                                    url: "GiveReportFeedback",
                                    data: {
                                        feedbackID: fbID,
                                        details: $('#details').val(),
                                        rating: checkValue
                                    },
                                    success: function () {
                                        alert("Your report have been send to Admin");
                                        location.reload();
                                    },
                                    error: function () {
                                        alert("Something went wrong!");
                                        location.reload();
                                    }
                                });
                            }
    </script>
</html>
