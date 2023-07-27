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
        <link href="img/icon.png" rel="icon">
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
                <!-- Libraries sweetalert2--> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"></script>
        <style>
            /*reset css*/
            /****** Style Star Rating Widget *****/
            #rating{border:none;float:left;}
            #rating>input{display:none;}/*ẩn input radio - vì chúng ta đã có label là GUI*/
            #rating>label:before{margin:5px;font-size:1.25em;font-family:FontAwesome;display:inline-block;content:"\f005";}/*1 ngôi sao*/
            #rating>label{color:#ddd;float:right;}/*float:right để lật ngược các ngôi sao lại đúng theo thứ tự trong thực tế*/
            /*thêm màu cho sao đã chọn và các ngôi sao phía trước*/
            #rating>input:checked~label,
            #rating:not(:checked)>label:hover, 
            #rating:not(:checked)>label:hover~label{color:#FFD700;}
            /* Hover vào các sao phía trước ngôi sao đã chọn*/
            #rating>input:checked+label:hover,
            #rating>input:checked~label:hover,
            #rating>label:hover~input:checked~label,
            #rating>input:checked~label:hover~label{color:#FFED85;}


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


        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div>

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
                <c:set var="dto" value="${requestScope.FEEDBACK}"/>
                <div class="container-fluid" id="content">
                    <form action="ListFeedBack">
                        <button type="submit" class="nav-link" style="border: none; background: none;">
                            <span><< Back</span>
                        </button>
                    </form>
                    <h1 class="text-primary">Report</h1>"
                    <div class="card details">
                        <p class="text-primary">Username: ${UserDAO.getUserByID(dto.userID).userName}</p>
                        <p class="text-primary">Product: ${ProductDAO.getProductByID(dto.productID).productName}</p>
                        <p class="text-primary">Rate: ${dto.rate}</p>
                        <p class="text-primary">Feedback Date: ${dto.feedbackDate}</p>
                        <p class="text-primary">Details:  </p><p>${dto.feedbackDetails}</p>
                        <p class="text-primary">Rate</p>
                        <div id="rating">
                            <input type="radio" id="star10" name="rating" value="10" />
                            <label class = "full " for="star10" title="10 stars"></label>

                            <input type="radio" id="star9" name="rating" value="9" />
                            <label class = "full" for="star9" title="9 stars"></label>

                            <input type="radio" id="star8" name="rating" value="8" />
                            <label class = "full" for="star8" title="8 stars"></label>

                            <input type="radio" id="star7" name="rating" value="7" />
                            <label class = "full" for="star7" title="7 stars"></label>

                            <input type="radio" id="star6" name="rating" value="6" />
                            <label class = "full" for="star6" title="6 star"></label>

                            <input type="radio" id="star5" name="rating" value="5" />
                            <label class = "full " for="star5" title="5 stars"></label>

                            <input type="radio" id="star4" name="rating" value="4" />
                            <label class = "full" for="star4" title="4 stars"></label>

                            <input type="radio" id="star3" name="rating" value="3" />
                            <label class = "full" for="star3" title="3 stars"></label>

                            <input type="radio" id="star2" name="rating" value="2" />
                            <label class = "full" for="star2" title="2 stars"></label>

                            <input type="radio" id="star1" name="rating" value="1" />
                            <label class = "full" for="star1" title="1 star"></label>
                        </div>
                        <textarea name="detailsReply" id="details" class="card form-control bg-light border-0 px-4 py-3 reply-details" placeholder="Report details"></textarea>
                        <button class="btn-reply" onclick="giveReportFeedback(${dto.feedbackID})">Report</button>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

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
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>

</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>
<script>
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
