
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    .img{
        margin-top: 20px;
    }
</style>
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

    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.js"></script>


</head>



<body id="page-top">
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
                <a class="nav-link" href="AdminPageController">
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
                <a class="nav-link" onclick="productView()" href="#">
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
                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 topbar">
                        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Earnings (Monthly)</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${MONTHEARN} VND</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Earnings (Annual)</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${YEAREARN} VND</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">User
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${AMOUNTUSER} User</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-12 col-lg-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Area Chart</h6>
                                </div>
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="myAreaChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Content Column -->
                        <div class="col-lg-6 mb-4">
                            <!-- Project Card Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Top 5 Products</h6>
                                </div>
                                <c:forEach var="product" items="${requestScope.LISTTOP5}" >
                                    <div class="row product-inrow">
                                        <div class="col-md-2 container-fluid img">
                                            <img src="${product.imgPath}" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                        </div>
                                        <div class="col-md-8 text-left mt-sm-2">
                                            <h4>${product.productName}</h4>
                                        </div>
                                        <div class="col-md-2 text-left mt-sm-2">
                                            <p class="font-weight-light text-left">Sold: ${product.quantity}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>

                        <div class="col-lg-6 mb-4">


                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Bar Chart</h6>
                                </div>
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="myBarChart"></canvas>
                                    </div>
                                </div>
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
    <!-- ChartJS -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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

                    function banAccount(param, bool) {
                        var username = param;
                        var boolean = bool;
                        $.ajax({
                            type: "post",
                            url: "BanAccountServlet",
                            data: {
                                banUsername: username,
                                bool: boolean
                            },
                            success: function (data) {
                                var row = document.getElementById("content");
                                row.innerHTML = data;
                            }
                        });
                    }

                    function productView() {
                        $.ajax({
                            type: "get",
                            url: "AdminProductView",
                            data: {
                            },
                            success: function (data) {
                                var row = document.getElementById("content");
                                row.innerHTML = data;
                            }
                        });
                    }

                    function searchProduct() {
                        $.ajax({
                            type: "post",
                            url: "SearchProductAdmin",
                            data: {
                                txtSearchValue: $('#search-box').val()
                            },
                            success: function (data) {
                                var row = document.getElementById("content");
                                row.innerHTML = data;
                            }
                        });
                    }

                    function number_format(number, decimals, dec_point, thousands_sep) {
                        // *     example: number_format(1234.56, 2, ',', ' ');
                        // *     return: '1 234,56'
                        number = (number + '').replace(',', '').replace(' ', '');
                        var n = !isFinite(+number) ? 0 : +number,
                                prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
                                sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                                dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                                s = '',
                                toFixedFix = function (n, prec) {
                                    var k = Math.pow(10, prec);
                                    return '' + Math.round(n * k) / k;
                                };
                        // Fix for IE parseFloat(0.55).toFixed(0) = 0;
                        s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
                        if (s[0].length > 3) {
                            s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
                        }
                        if ((s[1] || '').length < prec) {
                            s[1] = s[1] || '';
                            s[1] += new Array(prec - s[1].length + 1).join('0');
                        }
                        return s.join(dec);
                    }

// Area Chart Example
                    var ctx = document.getElementById("myAreaChart");
                    var myLineChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                            datasets: [{
                                    label: "Earnings",
                                    lineTension: 0.3,
                                    backgroundColor: "rgba(78, 115, 223, 0.05)",
                                    borderColor: "rgba(78, 115, 223, 1)",
                                    pointRadius: 3,
                                    pointBackgroundColor: "rgba(78, 115, 223, 1)",
                                    pointBorderColor: "rgba(78, 115, 223, 1)",
                                    pointHoverRadius: 3,
                                    pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                                    pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                                    pointHitRadius: 10,
                                    pointBorderWidth: 2,
                                    data: [${MONTH1}, ${MONTH2}, ${MONTH3}, ${MONTH4}, ${MONTH5}, ${MONTH6}, ${MONTH7}, ${MONTH8}, ${MONTH9}, ${MONTH10}, ${MONTH11}, ${MONTH12}],
                                }],
                        },
                        options: {
                            maintainAspectRatio: false,
                            layout: {
                                padding: {
                                    left: 10,
                                    right: 25,
                                    top: 25,
                                    bottom: 0
                                }
                            },
                            scales: {
                                xAxes: [{
                                        time: {
                                            unit: 'date'
                                        },
                                        gridLines: {
                                            display: false,
                                            drawBorder: false
                                        },
                                        ticks: {
                                            maxTicksLimit: 7
                                        }
                                    }],
                                yAxes: [{
                                        ticks: {
                                            maxTicksLimit: 5,
                                            padding: 10,
                                            // Include a dollar sign in the ticks
                                            callback: function (value, index, values) {
                                                return '$' + number_format(value);
                                            }
                                        },
                                        gridLines: {
                                            color: "rgb(234, 236, 244)",
                                            zeroLineColor: "rgb(234, 236, 244)",
                                            drawBorder: false,
                                            borderDash: [2],
                                            zeroLineBorderDash: [2]
                                        }
                                    }],
                            },
                            legend: {
                                display: false
                            },
                            tooltips: {
                                backgroundColor: "rgb(255,255,255)",
                                bodyFontColor: "#858796",
                                titleMarginBottom: 10,
                                titleFontColor: '#6e707e',
                                titleFontSize: 14,
                                borderColor: '#dddfeb',
                                borderWidth: 1,
                                xPadding: 15,
                                yPadding: 15,
                                displayColors: false,
                                intersect: false,
                                mode: 'index',
                                caretPadding: 10,
                                callbacks: {
                                    label: function (tooltipItem, chart) {
                                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                                        return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
                                    }
                                }
                            }
                        }
                    });

                    var ctx = document.getElementById("myBarChart");
                    var myLineChart = new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                            datasets: [{
                                    label: "Earnings",
                                    lineTension: 0.3,
                                    backgroundColor: "#2653d4",
                                    borderColor: "rgba(78, 115, 223, 1)",
                                    pointRadius: 3,
                                    pointBackgroundColor: "rgba(78, 115, 223, 1)",
                                    pointBorderColor: "rgba(78, 115, 223, 1)",
                                    pointHoverRadius: 3,
                                    pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                                    pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                                    pointHitRadius: 10,
                                    pointBorderWidth: 2,
                                    data: [${MONTH1}, ${MONTH2}, ${MONTH3}, ${MONTH4}, ${MONTH5}, ${MONTH6}, ${MONTH7}, ${MONTH8}, ${MONTH9}, ${MONTH10}, ${MONTH11}, ${MONTH12}],
                                }],
                        },
                        options: {
                            maintainAspectRatio: false,
                            layout: {
                                padding: {
                                    left: 10,
                                    right: 25,
                                    top: 25,
                                    bottom: 0
                                }
                            },
                            scales: {
                                xAxes: [{
                                        time: {
                                            unit: 'date'
                                        },
                                        gridLines: {
                                            display: false,
                                            drawBorder: false
                                        },
                                        ticks: {
                                            maxTicksLimit: 7
                                        }
                                    }],
                                yAxes: [{
                                        ticks: {
                                            maxTicksLimit: 5,
                                            padding: 10,
                                            // Include a dollar sign in the ticks
                                            callback: function (value, index, values) {
                                                return '$' + number_format(value);
                                            }
                                        },
                                        gridLines: {
                                            color: "rgb(234, 236, 244)",
                                            zeroLineColor: "rgb(234, 236, 244)",
                                            drawBorder: false,
                                            borderDash: [2],
                                            zeroLineBorderDash: [2]
                                        }
                                    }],
                            },
                            legend: {
                                display: false
                            },
                            tooltips: {
                                backgroundColor: "rgb(255,255,255)",
                                bodyFontColor: "#2653d4",
                                titleMarginBottom: 10,
                                titleFontColor: '#6e707e',
                                titleFontSize: 14,
                                borderColor: '#dddfeb',
                                borderWidth: 1,
                                xPadding: 15,
                                yPadding: 15,
                                displayColors: false,
                                intersect: false,
                                mode: 'index',
                                caretPadding: 10,
                                callbacks: {
                                    label: function (tooltipItem, chart) {
                                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                                        return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
                                    }
                                }
                            }
                        }
                    });

// Pie Chart Example
                    var ctx = document.getElementById("myPieChart");
                    var myPieChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: {
                            labels: ["Direct", "Referral", "Social"],
                            datasets: [{
                                    data: [55, 30, 15],
                                    backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                                    hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                                }],
                        },
                        options: {
                            maintainAspectRatio: false,
                            tooltips: {
                                backgroundColor: "rgb(255,255,255)",
                                bodyFontColor: "#858796",
                                borderColor: '#dddfeb',
                                borderWidth: 1,
                                xPadding: 15,
                                yPadding: 15,
                                displayColors: false,
                                caretPadding: 10,
                            },
                            legend: {
                                display: false
                            },
                            cutoutPercentage: 80,
                        },
                    });

    </script>
</body>

</html>
