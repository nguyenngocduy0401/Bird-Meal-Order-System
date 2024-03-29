
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sample.dto.UserDTO" %>
<%@ page import="sample.dao.UserDAO" %>
<%@ page import="sample.dao.CategoryDAO" %>
<!DOCTYPE html>
<c:if test="${sessionScope.user==null||sessionScope.user.role ne 1}">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Staff - List Orders</title>
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

        <!--sweetalert2--> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .sweet_loader {
                width: 140px;
                height: 140px;
                margin: 0 auto;
                animation-duration: 0.5s;
                animation-timing-function: linear;
                animation-iteration-count: infinite;
                animation-name: ro;
                transform-origin: 50% 50%;
                transform: rotate(0) translate(0,0);
            }
            @keyframes ro {
                100% {
                    transform: rotate(-360deg) translate(0,0);
                }
            }
        </style>


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
                        <!-- DataTales -->
                        <div class="card shadow mb-4">
                            <!--                            <div class="card-header py-3">
                                                        </div>-->
                            <div class="card-body">
                                <div class="table-responsive">
                                    <c:set var="result" value="${sessionScope.ORDERS_LIST_CUSTOMER}"/>
                                    <c:if test="${empty result}">
                                        No order yet!    
                                    </c:if>
                                    <c:if test="${not empty result}">
                                        <table class="table table-bordered" data-order='[[ 0, "desc" ]]' id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>OrderID</th>
                                                    <th>Username</th>
                                                    <th>Name</th>
                                                    <th>Quantity</th>
                                                    <th>Total Price(₫)</th>
                                                    <th>Order Date</th>
                                                    <th width = 13%>Status</th>
                                                    <th width = 9%  data-sortable="false">Action</th> 
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>OrderID</th>
                                                    <th>Username</th>
                                                    <th>Name</th>
                                                    <th>Quantity</th>
                                                    <th>Total Price(₫)</th>
                                                    <th>Order Date</th>
                                                    <th width = 13%>Status</th>
                                                    <th width = 9%>Action</th> 
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <c:forEach var="dto" items="${result}">
                                                    <tr>
                                                        <c:set var="subTotal" value = "0"/>
                                                        <c:set var="totalQuantity" value="0"/>
                                                        <c:forEach var="product" items="${dto.productsList}" >
                                                            <c:set var="totalQuantity" value="${totalQuantity + product.quantity}"/>
                                                            <c:set var="subTotal" value = "${subTotal + (product.quantity * product.price)}"/>
                                                        </c:forEach>
                                                        <c:set var="userDTO" value="${UserDAO.getUserByID(dto.userID).userName}"/>
                                                        <td>
                                                            ${dto.orderID}
                                                        </td>
                                                        <td>
                                                            <c:if test="${dto.userID == 0}">
                                                                Guest
                                                            </c:if>
                                                            <c:if test="${dto.userID != 0}">
                                                                ${UserDAO.getUserByID(dto.userID).userName}
                                                            </c:if>
                                                        </td>
                                                        <td>${dto.fullName}</td>
                                                        <td>
                                                            ${totalQuantity}
                                                        </td>

                                                        <td class="total">
                                                            ${subTotal + dto.shippingFee}
                                                        </td>

                                                        <td>${dto.date}</td>

                                                        <td style="text-align: center; vertical-align: middle" id="status">
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
                                                                <c:when test="${dto.status == 5}">
                                                                    <span class="p-2 rounded " style="background-color: #E1D1D1; color: #803535; font-weight: bold">
                                                                        Incomplete
                                                                    </span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="p-2 rounded " style="background-color: #D0F1C0; color: #45B20F; font-weight: bold">
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
                                                                                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                                                                        <img src="${products.imgPath}"
                                                                                                             class="img-fluid" alt="CategoryID:${products.categoryID}">
                                                                                                    </div>
                                                                                                    <div class="col-md-3 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0">${products.productName}</p>
                                                                                                    </div>
                                                                                                    <div class="col-md-1 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0 small">
                                                                                                            <!-- 
                                                                                                            <c:if test="${products.categoryID == 1}">
                                                                                                                Food
                                                                                                            </c:if>
                                                                                                            <c:if test="${products.categoryID == 2}">
                                                                                                                Combo
                                                                                                            </c:if>-->
                                                                                                            ${CategoryDAO.getCategoryByID(products.categoryID).categoryName}
                                                                                                        </p>
                                                                                                    </div>
                                                                                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0 small">Weight: ${products.size}g</p>
                                                                                                    </div>
                                                                                                    <div class="col-md-1 text-center d-flex justify-content-center align-items-center">
                                                                                                        <p class="text-muted mb-0 small">Qty:&nbsp;${products.quantity}</p>
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
                                                                                        <p class="text-muted mb-0">Username: 
                                                                                            <c:if test="${dto.userID == 0}">
                                                                                                Guest
                                                                                            </c:if>
                                                                                            <c:if test="${dto.userID != 0}">
                                                                                                ${UserDAO.getUserByID(dto.userID).userName}
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
                                                                                    <c:if test="${dto.email != null}">
                                                                                        <div class="d-flex justify-content-between">
                                                                                            <p class="text-muted mb-0">Email: ${dto.email}</p>
                                                                                        </div>
                                                                                    </c:if>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Note: ${dto.note}</p>
                                                                                    </div>
                                                                                    <div class="d-flex justify-content-between">
                                                                                        <p class="text-muted mb-0">Status: 
                                                                                            <c:choose>
                                                                                                <c:when test="${dto.status == '1'}">
                                                                                                    <select name="ddlStatus" id="status">
                                                                                                        <option value="1" hidden="hidden"}>Waiting</option>
                                                                                                        <option value="0">Cancelled</option>
                                                                                                        <option value="2">Confirmed</option>
                                                                                                    </select>
                                                                                                </c:when>
                                                                                                <c:when test="${dto.status == '2'}">
                                                                                                    <select name="ddlStatus" id="status">
                                                                                                        <option value="2" hidden="hidden"}>Confirmed</option>
                                                                                                        <option value="3">Shipping</option>
                                                                                                    </select>
                                                                                                </c:when>
                                                                                                <c:when test="${dto.status == '3'}">
                                                                                                    <select name="ddlStatus" id="status">
                                                                                                        <option value="3" hidden="hidden"}>Shipping</option>
                                                                                                        <option value="4" >Completed</option>
                                                                                                        <option value="5" >Incompleted</option>
                                                                                                    </select>
                                                                                                </c:when>
                                                                                                <c:when test="${dto.status == '4'}">
                                                                                                    Completed
                                                                                                </c:when>
                                                                                                <c:when test="${dto.status == '4'}">
                                                                                                    Completed
                                                                                                </c:when>
                                                                                                <c:when test="${dto.status == '0'}">
                                                                                                    Cancelled
                                                                                                </c:when>    
                                                                                            </c:choose>
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
                                                                                <c:if test="${dto.status != '4' and dto.status != '5'}">
                                                                                    <button type="button" class="btn btn-primary" onclick="updateStatus(${dto.orderID}, ddlStatus.value)">Update</button>
                                                                                </c:if>
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
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->
                    <script>
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

                        var total = document.getElementsByClassName("total");
                        formatTotalArrayClassName(total);
                        function formatTotalArrayClassName(array) {
                            for (var i = 0; i < array.length; i++) {
                                array[i].textContent = numberWithCommas(array[i].textContent);
                            }
                            ;
                        }

                        function numberWithCommas(x) {
                            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        }
                        //alert when update status success
//                        if (${requestScope.ORDER_UPDATE_STATUS == true}) {
//                            Swal.fire({
//                                icon: 'success',
//                                title: 'Success!',
//                                text: 'Update status order!',
//                                showConfirmButton: false,
//                                timer: 2000,
//                                timerProgressBar: true
//                            })
//                        }
                        //update status function
                        function updateStatus(orderID, statusID) {
                            swal.fire({
                                title: "Checking...",
                                text: "Please wait",
                                imageUrl: "images/ajaxloader.gif",
                                showConfirmButton: false,
                                allowOutsideClick: false
                            });
                            $.ajax({
                                type: "post",
                                url: "UpdateOrderStatusServlet",
                                data: {
                                    txtOrderID: orderID,
                                    ddlStatus: statusID
                                },
                                success: function () {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Success!',
                                        text: 'Update status order!',
                                        showConfirmButton: false,
                                        timer: 2000,
                                        timerProgressBar: true
                                    }).then(function () {
                                        window.location = "GetOrdersListServlet";
                                    });
                                },
                                error: function () {
                                    window.location = "error.html";
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

        <!-- Bootstrap  5.0-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
