<%-- 
    Document   : staffOrdersList
    Created on : Jun 27, 2023, 1:40:19 PM
    Author     : haong
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
        <title>Staff orders controller</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Staff - Orders</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

        <!--sweetalert2--> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
        </style>

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

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item">
                    <a class="nav-link" href="index.html">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Interface
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                       aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Components</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Components:</h6>
                            <a class="collapse-item" href="buttons.html">Buttons</a>
                            <a class="collapse-item" href="cards.html">Cards</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                       aria-expanded="true" aria-controls="collapseUtilities">
                        <i class="fas fa-fw fa-wrench"></i>
                        <span>Utilities</span>
                    </a>
                    <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Utilities:</h6>
                            <a class="collapse-item" href="utilities-color.html">Colors</a>
                            <a class="collapse-item" href="utilities-border.html">Borders</a>
                            <a class="collapse-item" href="utilities-animation.html">Animations</a>
                            <a class="collapse-item" href="utilities-other.html">Other</a>
                        </div>
                    </div>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Addons
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                       aria-expanded="true" aria-controls="collapsePages">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>Pages</span>
                    </a>
                    <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Login Screens:</h6>
                            <a class="collapse-item" href="login.html">Login</a>
                            <a class="collapse-item" href="register.html">Register</a>
                            <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
                            <div class="collapse-divider"></div>
                            <h6 class="collapse-header">Other Pages:</h6>
                            <a class="collapse-item" href="404.html">404 Page</a>
                            <a class="collapse-item" href="blank.html">Blank Page</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="charts.html">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Charts</span></a>
                </li>

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


                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
<!--                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>-->

                    <!-- Topbar Search -->


                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
<!--                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                             Dropdown - Messages 
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                 aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search" action="MainController">
                                    <div class="input-group">
                                        <input type="text" name="txtSearchValue" class="form-control bg-light border-0 small"
                                               placeholder="Search for..." aria-label="Search"
                                               aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                        <input type="hidden" name="btAction" value="SearchOfStaff" />
                                    </div>
                                </form>
                            </div>
                        </li>-->

                        <!-- Nav Item - Alerts -->
<!--                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                 Counter - Alerts 
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                             Dropdown - Alerts 
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
                        </li>-->

                        <!-- Nav Item - Messages -->
<!--                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                 Counter - Messages 
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                             Dropdown - Messages 
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
                        </li>-->

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
                <!-- Page Heading -->
                <div class="container-fluid">
                    <h1 class="h3 mb-2 text-gray-800">ORDERS LIST</h1>
                    <!--<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                                                                                   href="https://datatables.net">official DataTables documentation
                        </a>.</p>-->
                    <div class="card shadow mb-4">

                        <div class="card-header py-3">
                            <div class="d-flex justify-content-between align-items-center">
<!--                                <form action="MainController">
                                    <button class="btn btn-outline-primary mr-auto" name="btAction" value="Create New Product">Add new product</button>
                                </form>-->
                                <form action="GetOrdersListServlet">
                                    <button class="btn btn-outline-primary mr-auto" type="submit">Show All</button>
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
                        <c:set var="result" value="${sessionScope.ORDERS_LIST_CUSTOMER}" />
                        <c:set var="resultGuest" value="${sessionScope.ORDERS_LIST_GUEST}"/>

                        <div class="card shadow mb-4">

                            <div class="card-body">
                                <div class="table-responsive">
                                    <c:if test="${empty result && empty resultGuest}">
                                        No orders yet!!
                                    </c:if>
                                    <c:if test="${not empty result || not empty resultGuest}">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>OrderID</th>
                                                    <th>UserId</th>
                                                    <th>Name</th>
                                                    <th>Quantity</th>
                                                    <th>Total Price</th>
                                                    <th>Order Date</th>
                                                    <th width = 13%>Status</th>
                                                    <th width = 9%>Action</th> 
                                                </tr>
                                            </thead>
                                            <!--                                            <tfoot>
                                                                                            <tr>
                                                                                                <th>OrderID</th>
                                                                                                <th>Order Date</th>
                                                                                                <th>Name</th>
                                                                                                <th>Quantity</th>
                                                                                                <th>Total Price</th>
                                                                                                <th>Status</th>
                                                                                                <th>Action</th> 
                                                                                            </tr>
                                                                                        </tfoot>-->
                                            <tbody>


                                                <c:forEach var="dto" items="${result}">
                                                    <tr>
                                                        <c:set var="subTotal" value = "0"/>
                                                        <c:set var="totalQuantity" value="0"/>
                                                        <c:forEach var="product" items="${dto.productsList}" >
                                                            <c:set var="totalQuantity" value="${totalQuantity + product.quantity}"/>
                                                            <c:set var="subTotal" value = "${subTotal + (product.quantity * product.price)}"/>
                                                        </c:forEach>
                                                        <td>
                                                            ${dto.orderID}
                                                        </td>
                                                        <td>
                                                            <c:if test="${dto.userID == 0}">
                                                                Guest
                                                            </c:if>
                                                            <c:if test="${dto.userID != 0}">
                                                                ${dto.userID}
                                                            </c:if>
                                                        </td>
                                                        <td>${dto.fullName}</td>
                                                        <td>
                                                            ${totalQuantity}
                                                        </td>

                                                        <td class="price">
                                                            ${subTotal + dto.shippingFee}
                                                        </td>

                                                        <td>${dto.date}</td>

                                                        <td style="text-align: center; vertical-align: middle">
                                                            <c:choose>
                                                                <c:when test="${dto.status == 0}">
                                                                    <span class="p-2 rounded " style="background-color: #F9E2E1; color: #E05151; font-weight: bold">
                                                                        Cancelled
                                                                    </span>
                                                                </c:when>
                                                                <c:when test="${dto.status == 1}">
                                                                    <span class="p-2 rounded " style="background-color: #FFF4E8; color: #E08221; font-weight: bold">
                                                                        Waiting
                                                                    </span>
                                                                </c:when>
                                                                <c:when test="${dto.status == 2}">
                                                                    <span class="p-2 rounded " style="background-color: #C4F1F4; color: #0AB9C4; font-weight: bold">
                                                                        Confirmed
                                                                    </span>
                                                                </c:when>
                                                                <c:when test="${dto.status == 3}">
                                                                    <span class="p-2 rounded " style="background-color: #ADC1EC; color: #2662E1; font-weight: bold">
                                                                        Shipping
                                                                    </span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="p-2 rounded " style="background-color: #B6F596; color: #45B20F; font-weight: bold">
                                                                        Complete
                                                                    </span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>    
                                                        <td style="text-align: center">
                                                            <!-- Button trigger modal -->
                                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#viewOrderID${dto.orderID}" data-keyboard="false" data-backdrop="static">
                                                                View
                                                            </button>
                                                            <!-- Modal For Edit Status-->
                                                            <form action="UpdateOrderStatusServlet" method="POST">
                                                                
                                                                <div  class="modal fade" id="viewOrderID${dto.orderID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >

                                                                    <div class="modal-dialog modal-lg modal-dialog-centered">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="exampleModalLabel">View Order Details</h5>
                                                                                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="card-body p-4">

                                                                                    <c:forEach var="products" items="${dto.productsList}" >
                                                                                        <div class="card shadow-0 border mb-4">
                                                                                            <div class="card-body">
                                                                                                <div class="row">
                                                                                                    <div class="col-md-1 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0">ID:${products.productID}</p>
                                                                                                    </div>
                                                                                                    <div class="col-md-2">
                                                                                                        <img src="${products.imgPath}"
                                                                                                             class="img-fluid" alt="CategoryID:${products.categoryID}">
                                                                                                    </div>
                                                                                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0">${products.productName}</p>
                                                                                                    </div>
                                                                                                    <div class="col-md-1 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0 small">
                                                                                                            <c:if test="${products.categoryID == 1}">
                                                                                                                Food
                                                                                                            </c:if>
                                                                                                            <c:if test="${products.categoryID == 2}">
                                                                                                                Combo
                                                                                                            </c:if>
                                                                                                        </p>
                                                                                                    </div>
                                                                                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0 small">Weight: ${products.size}g</p>
                                                                                                    </div>
                                                                                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0 small">Qty: ${products.quantity}</p>
                                                                                                    </div>
                                                                                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="price text-muted mb-0 small">${products.price}</p>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>  

                                                                                    </c:forEach>
                                                                                    <div class="d-flex justify-content-between pt-2">
                                                                                        <p class="mb-0" style="font-weight: bold">Order Details</p>
                                                                                    </div>

                                                                                    <div class="d-flex justify-content-between pt-2">
                                                                                        <p class="text-muted mb-0">OrderID: ${dto.orderID}</p>
                                                                                        <input type="hidden" name="txtOrderID" value="${dto.orderID}"/>
                                                                                        <p class="text-muted mb-0"><span class="" style="font-weight: bold; margin-right: 20px">Sub Total</span><span class="price">${subTotal}</span></p>
                                                                                    </div>

                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Order Date: ${dto.date}</p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">UserID: 
                                                                                            <c:if test="${dto.userID == 0}">
                                                                                                Guest
                                                                                            </c:if>
                                                                                            <c:if test="${dto.userID != 0}">
                                                                                                ${dto.userID}
                                                                                            </c:if>
                                                                                        </p>
                                                                                        <p class="text-muted mb-0"><span class="" style="font-weight: bold; margin-right: 20px">Shipping fee</span><span class="price">${dto.shippingFee}</span></p>
                                                                                    </div>  

                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Name: ${dto.fullName}</p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Phone: ${dto.phoneNumber}</p>
                                                                                        <p class="text-muted mb-0"><span class="" style="font-weight: bold; margin-right: 20px; font-size: large;">Total</span>
                                                                                            <span class="price"style="
                                                                                                  font-size: 1.71429em;
                                                                                                  font-weight: 500;
                                                                                                  letter-spacing: -0.04em;
                                                                                                  color: #4b4b4b;
                                                                                                  line-height: 1em;" >${subTotal + dto.shippingFee}</span>
                                                                                        </p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Shipping Date: ${dto.shippingDate}</p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Payment method: COD</p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Address: ${dto.orderAddress}</p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Note: ${dto.note}</p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Status: 
                                                                                            <select name="ddlStatus" id="status">
                                                                                                <option value="0" ${dto.status == '0' ? 'selected hidden' : ''}>Cancelled</option>
                                                                                                <option value="1" ${dto.status == '1' ? 'selected hidden' : ''}>Waiting</option>
                                                                                                <option value="2" ${dto.status == '2' ? 'selected hidden' : ''}>Confirmed</option>
                                                                                                <option value="3" ${dto.status == '3' ? 'selected hidden' : ''}>Shipping</option>
                                                                                                <option value="4" ${dto.status == '4' ? 'selected hidden' : ''}>Complete</option>
                                                                                            </select>
                                                                                        </p>
                                                                                    </div>
                                                                                </div>
                                                                                <!--                                                                                <div class="card-footer border-0 px-4 py-5"
                                                                                                                                                                     style="background-color: #a8729a; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px;">
                                                                                                                                                                    <h5 class="d-flex align-items-center justify-content-end text-white text-uppercase mb-0">Total
                                                                                                                                                                        paid: <span class="h2 mb-0 ms-2">$1040</span></h5>
                                                                                                                                                                </div>-->
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn" data-bs-dismiss="modal"
                                                                                        style="color: black; background-color: lightgray; border-color: lightgray">Close</button>
                                                                                <button type="submit" class="btn btn-primary">Update</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </td>
                                                    </tr>



                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                                <c:set var="prePage" value="${TAGS - 1}" />
                                <c:set var="nextPage" value="${TAGS + 1}" />
                                <!--                                <div class="col-12 mt-5">
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
                            </div>-->
                            </div>
                        </div>

                    </div>

                    <!-- /.container-fluid -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                    <script>
                        //                        function nextPage() {
                        //                            var amount = ${requestScope.TAGS};
                        //
                        //                            $.ajax({
                        //                                type: "get",
                        //                                url: "PagingStaffProduct",
                        //                                data: {
                        //                                    index: amount + 1,
                        //                                    txtSearchValue: '${requestScope.txtSearchValue}',
                        //                                },
                        //                                success: function (data) {
                        //                                    var row = document.getElementById("content");
                        //                                    row.innerHTML = data;
                        //                                }
                        //                            });
                        //                        }
                        //                        function previousPage() {
                        //                            var amount = ${requestScope.TAGS};
                        //                            $.ajax({
                        //                                type: "get",
                        //                                url: "PagingStaffProduct",
                        //                                data: {
                        //                                    index: amount - 1,
                        //                                    txtSearchValue: '${requestScope.txtSearchValue}',
                        //                                },
                        //                                success: function (data) {
                        //                                    var row = document.getElementById("content");
                        //                                    row.innerHTML = data;
                        //                                }
                        //                            });
                        //                        }
                        //                        function loadPage(param) {
                        //                            var amount = param;
                        //                            $.ajax({
                        //                                type: "get",
                        //                                url: "PagingStaffProduct",
                        //                                data: {
                        //                                    index: amount,
                        //                                    txtSearchValue: '${requestScope.txtSearchValue}',
                        //                                },
                        //                                success: function (data) {
                        //                                    var row = document.getElementById("content");
                        //                                    row.innerHTML = data;
                        //                                }
                        //                            });
                        //                        }

                        //format money
                        var price = document.getElementsByClassName("price");
                        formatCurrencyArrayClassName(price);
                        function formatCurrencyArrayClassName(array) {
                            for (var i = 0; i < array.length; i++) {
                                array[i].textContent = formatCurrency(array[i].textContent);
                            }
                            ;
                        }
                        function formatCurrency(amount) {
                            var formatter = new Intl.NumberFormat("vi-VN", {
                                style: "currency",
                                currency: "VND"
                            });

                            return formatter.format(amount);
                        }
                        //function remove select status is existing
//                        var select = document.getElementById('status')
//                        select.removeChild(getOptionByValue(select, ${dto.status})
//
//                        function getOptionByValue(select, value) {
//                            var options = select.options;
//                            for (var i = 0; i < options.length; i++) {
//                                if (options[i].value === value) {
//                                    return options[i]
//                                }
//                            }
//                            return null
//                        }
                        //alert when update status success
                        if (${requestScope.ORDER_UPDATE_STATUS == true}) {
                            Swal.fire({
                                icon: 'success',
                                title: 'Success!',
                                text: 'Update status order!',
                                showConfirmButton: false,
                                timer: 2000,
                                timerProgressBar: true
                            })
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
                        <a class="btn btn-primary" href="login.html">Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Bootstrap  5.0-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

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