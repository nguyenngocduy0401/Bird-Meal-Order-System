
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sample.dto.UserDTO" %>
<%@ page import="sample.dao.UserDAO" %>
<%@ page import="sample.dto.FeedbackDTO" %>
<%@ page import="sample.dao.FeedbackDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Admin</title>

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
    <script src="js/demo/datatables-demo.js"></script>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <script src="js/demo/datatables-demo.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .btn-decline{
            border: none;
            margin-bottom: 20px;
            border-radius: 16px;
            color: white;
            background: #EF3159;
        }

        .btn-decline:hover{
            background: #E08221;
        }

        .btn-accept{
            background: #007bff;
            border: none;
            margin-bottom: 20px;
            border-radius: 16px;
            color: white;
        }

        .btn-accept:hover{
            background: #25cff2;
        }
    </style>

    <body id="page-top">
        <div class="container-fluid">
            <!-- DataTales Example -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4 topbar">
                <h1 class="h3 mb-0 text-gray-800">Report</h1>
            </div>
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Report Table</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <c:set var="result" value="${requestScope.LIST_REPORT}"/>
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Staff</th>
                                    <th>User</th>
                                    <th>Report Detail</th>
                                    <th>Report Date</th>
                                    <th>Rate</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}">
                                    <c:if test="${dto.status eq 0}">
                                        <tr>
                                            <td>
                                                ${UserDAO.getUserByID(dto.userID).userName}
                                            </td>
                                            <td>
                                                <c:set var="fbuser" value="${FeedbackDAO.getFeedbackByID(dto.feedbackID).userID}"/>
                                                ${UserDAO.getUserByID(fbuser).userName}
                                            </td>
                                            <td>
                                                ${dto.reportDetail}
                                            </td>
                                            <td>
                                                ${dto.reportDate}
                                            </td>
                                            <td>
                                                ${dto.rate}
                                            </td>
                                            <td>
                                                <button class="btn-accept" onclick="acceptReport(${dto.reportID}, 1)">
                                                    <i class="fa">Accept</i>
                                                </button>
                                                <button class="btn-decline" onclick="declineReport(${dto.reportID}, 2)">
                                                    <i class="fa">Decline</i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>

                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
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
    <script>
        function acceptReport(reportID, status) {
            $.ajax({
                type: "post",
                url: "AcceptReport",
                data: {
                    reportID: reportID,
                    status: status
                },
                success: function (data) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Successful!',
                        showConfirmButton: false,
                        timer: 1000
                    });
                    var row = document.getElementById("content");
                    row.innerHTML = data;
                },
                error: function () {
                    alert("Something went wrong!");
                    location.reload();
                }
            });
        }
        function declineReport(reportID, status) {
            $.ajax({
                type: "post",
                url: "AcceptReport",
                data: {
                    reportID: reportID,
                    status: status
                },
                success: function (data) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Successful!',
                        showConfirmButton: false,
                        timer: 1000
                    });
                    var row = document.getElementById("content");
                    row.innerHTML = data;
                },
                error: function () {
                    alert("Something went wrong!");
                    location.reload();
                }
            });
        }
    </script>
</html>
