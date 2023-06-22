<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <style>  
        table, th, td {  
            border: 1px solid black;  
            border-collapse: collapse;  
            margin-top: 30px;
            margin-left: 50px;
        }  
        th, td {  
            padding: 10px;  
            text-align: center;
        }  
        table#alter tr:nth-child(even) {  
            background-color: #eee;  
        }  
        table#alter tr:nth-child(odd) {  
            background-color: #fff;  
        }  
        table#alter th {  
            color: white;  
            background-color: gray;  
        }  

        #class{
            margin-left: 50px;
            display: inline;
        }

        #class button{
            margin-left: 10px;
        }

        .h123{
            margin-top: 30px;
            margin-left: 50px;
        }
        .topbar{
            margin-top: 20px;
        }
        .button{
            color: green;
        }
        .form-control{
            margin-bottom: 5px;
            margin-top: 5px;
        }
    </style>

    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.js"></script>

    <c:if test="${sessionScope.user==null||sessionScope.user.role ne 0}">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
    <head>
        <meta charset="utf-8">
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

        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />
    </head>
    <body>
        <c:set var="user" value="${sessionScope.user.fullName}"/>
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->


                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="admin.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Manager
                </div>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" onclick="accountView()" href="#">
                        <span>Account</span></a>
                </li>

                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <span>Products</span></a>
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
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                 aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                               placeholder="Search for..." aria-label="Search"
                                               aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                             alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                             alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                             alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                             alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user}</span>
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

                <!-- Main Content -->
                <div id="content">
                    <div class="container-fluid">
      
                            <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                                <h1 class="display-5 text-uppercase mb-0">Create new Staff</h1>
                            </div>
                            <div class="row g-5">
                                <div class="col-lg-7">
                                    <form action="CreateStaffServlet" class="form-floating">
                                        <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
                                        <div class="row g-3">
                                            <div class="col-12">
                                                <c:if test="${not empty errors.usernameLengthError}">
                                                    <input type="text" class="form-control bg px-4 is-invalid" placeholder="Username*(6 - 30 characters)"
                                                           style="height: 55px;" name="txtUsername" value="${param.txtUsername}">
                                                    <font color ="red">
                                                    ${errors.usernameLengthError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.usernameLengthError and empty errors.usernameIsExisted}">
                                                    <input type="text" class="form-control bg  px-4" placeholder="Username*(6 - 30 characters)"
                                                           style="height: 55px;" name="txtUsername" value="${sessionScope.REGISTRATION.email.substring(0,REGISTRATION.email.indexOf("@"))}">
                                                </c:if>


                                                <c:if test="${not empty errors.usernameIsExisted}">
                                                    <input type="text" class="form-control  px-4 is-invalid" id="floatingInput" placeholder="Username*(6 - 30 characters)"
                                                           style="height: 55px;" name="txtUsername" value="${param.txtUsername}">
                                                    <font color ="red">
                                                    ${errors.usernameIsExisted}
                                                    </font>
                                                </c:if>
                                            </div>


                                            <div class="col-12">
                                                <c:if test="${not empty errors.passwordLengthError}">
                                                    <input type="password" class="form-control bg px-4 is-invalid"  placeholder="Password*(8 - 30 characters)"
                                                           style="height: 55px;" name="txtPassword">
                                                    <font color ="red">
                                                    ${errors.passwordLengthError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.passwordLengthError}">
                                                    <input type="password" class="form-control bg px-4"  placeholder="Password*(8 - 30 characters)"
                                                           style="height: 55px;" name="txtPassword">
                                                </c:if>    
                                            </div>

                                            <div class="col-12">
                                                <c:if test="${not empty errors.confirmNotMatched}">
                                                    <input type="password" class="form-control bg px-4 is-invalid" placeholder="Confirm password*"
                                                           style="height: 55px;" name="txtConfirmPassword">
                                                    <font color ="red">
                                                    ${errors.confirmNotMatched}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.confirmNotMatched}">
                                                    <input type="password" class="form-control bg px-4" placeholder="Confirm password*"
                                                           style="height: 55px;" name="txtConfirmPassword">
                                                </c:if>
                                            </div>


                                            <div class="col-12">
                                                <input type="text" class="form-control bg px-4" placeholder="Email*"
                                                       style="height: 55px;" name="txtEmail" value="${sessionScope.REGISTRATION.email}">
                                                <c:if test="${not empty errors.emailFormatError}">
                                                    <font color ="red">
                                                    ${errors.emailFormatError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${not empty errors.emailIsExisted}">
                                                    <font color ="red">
                                                    ${errors.emailIsExisted}
                                                    </font>
                                                </c:if>    
                                            </div>

                                            <div class="col-12">
                                                <c:if test="${not empty errors.fullnameLengthError}">
                                                    <input type="text" class="form-control bg px-4 is-invalid" placeholder="Full name*(2 - 50 characters)"
                                                           style="height: 55px;" name="txtFullName" value="${param.txtFullName}">
                                                    <font color ="red">
                                                    ${errors.fullnameLengthError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.fullnameLengthError}">
                                                    <input type="text" class="form-control bg px-4 " placeholder="Full name*(2 - 50 characters)"
                                                           style="height: 55px;" name="txtFullName" value="${param.txtFullName}">

                                                </c:if>
                                            </div>
                                            <div class="col-12">
                                                <c:if test="${not empty errors.phoneNumberFormatError}">
                                                    <input type="text" class="form-control bg px-4 is-invalid" placeholder="Phone number*(10 number)"
                                                           style="height: 55px;" name="txtPhoneNumber" value="${param.txtPhoneNumber}"
                                                           onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                                    <font color ="red">
                                                    ${errors.phoneNumberFormatError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.phoneNumberFormatError}">
                                                    <input type="text" class="form-control bg px-4" placeholder="Phone number*(10 number)"
                                                           style="height: 55px;" name="txtPhoneNumber" value="${param.txtPhoneNumber}"
                                                           onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">

                                                </c:if>
                                            </div>
                                            <!--                            <div class="col-12">
                                                                            <input type="text" class="form-control bg border-0 px-4"
                                                                                   placeholder="Address" style="height: 55px;" name="txtAddress">
                                                                        </div>-->
                                            <div class="col-12">
                                                <label class="col-12">Address</label>
                                                <div>
                                                    <input type="text" class="form-control bg border-0 px-4 mt-3 mb-2" placeholder="Address details"
                                                           style="height: 55px;" name="txtAddressDetails" value="${param.txtAddressDetails}">
                                                </div>
                                                <div class="col-12">
                                                    <label class="col-12">Gender*</label>
                                                    <select name="ddlGender" class="form-control form-select  bg-white border-0 px-4 mb-2" style="height: 55px;">
                                                        <option value="male">Male</option>
                                                        <option value="feMale">Female</option>
                                                    </select>
                                                </div>
                                                <!--                                    <div class="col-12">
                                                                                        <label class="col-12">Birthday*</label>
                                                                                        <input type="date" class="form-control bg border-0 px-4" 
                                                                                               style="height: 55px;" value="" name="txtbirthday">
                                                                                    </div>-->
                                                <div class="col-12">
                                                    <button class="btn btn-primary w-100 py-3 mt-3" type="submit">Create</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
 
                </div>


                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2021</span>
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
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

        <script>
                                                           function accountView() {
                                                               $.ajax({
                                                                   type: "get",
                                                                   url: "AdminAccountView",
                                                                   data: {
                                                                   },
                                                                   success: function (data) {
                                                                       var row = document.getElementById("content");
                                                                       row.innerHTML = data;
                                                                   }
                                                               });
                                                           }

                                                           function searchAccount() {
                                                               $.ajax({
                                                                   type: "post",
                                                                   url: "searchAccount",
                                                                   data: {
                                                                       txtSearchValue: $('#search-box').val()
                                                                   },
                                                                   success: function (data) {
                                                                       var row = document.getElementById("content");
                                                                       row.innerHTML = data;
                                                                   }
                                                               });
                                                           }
        </script>
    </body>
</html>
