<%-- 
    Document   : createNewProduct
    Created on : Jun 8, 2023, 2:37:58 AM
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
        <title>Staff - Create new product</title>
        <style>
            .content {
                margin-top: 60px;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }

            <!-- CSS THEM -->
            h1 {
                font-size: 48px;
                font-weight: bold;
                color: #333;
                margin-bottom: 30px;
            }

            body {
                background-color: #f3f3f3;
            }

            .container {
                padding-top: 50px;
                padding-bottom: 50px;
            }

            .card-header {
                background-color: #f8f9fa;
                font-weight: bold;
            }
            .product-image {
                max-width: 100%;
                height: auto;
            }

            label {
                font-weight: bold;
            }
        </style>
        <!-- Favicon -->
        <link href="img/icon.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&family=Roboto:wght@700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <!-- Template new -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!--        multi-select scripts css
        --><link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/css/multi-select-tag.css">

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

                <hr class="sidebar-divider my-0">


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
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Search -->
                        <div class="navbar-nav ms-left py-0">
                            <a href="staff.jsp" class="nav-item nav-link active">STAFF HOME</a>
                        </div>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">

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

                        <!-- Page Heading -->
                        <h1 class="h3 mb-4 text-gray-800">Create a new product</h1>

                    </div>
                    <!-- /.container-fluid -->
                    <c:set var="category" value="${categoryname}"/>
                    <div class="card mb-4">
                        <div class="card-body">
                            <form action="CreateNewProductServlet" method="post" enctype="multipart/form-data">
                                <c:set var="errors" value="${requestScope.CREATE_PRODUCT_ERROR}"/>
                                <div class="form-group row">
                                    <label for="txtProductName" class="col-sm-3 col-form-label">Product Name</label>

                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productNameLengthError}">
                                            <input type="text" name="txtProductName" id="txtProductName" class="form-control" required=""
                                                   placeholder="Enter product name from 3 to 500 characters" value="${param.txtProductName}">
                                        </c:if>

                                        <c:if test="${not empty errors.productNameLengthError}">
                                            <input type="text" name="txtProductName" id="txtProductName" class="form-control is-invalid" required=""
                                                   placeholder="Enter product name from 3 to 500 characters" value="${param.txtProductName}">
                                            <font color ="red">
                                            ${errors.productNameLengthError}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtPrice" class="col-sm-3 col-form-label">Price(VND)</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productPriceFormatError}">
                                            <input type="text" name="txtPrice" id="txtPrice" class="form-control" required=""
                                                   placeholder="Enter the price of product (EX: 500,000)"
                                                   data-type="currency" id="currency-field" value="${param.txtPrice}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                        </c:if>

                                        <c:if test="${not empty errors.productPriceFormatError}">
                                            <input type="text" name="txtPrice" id="txtPrice" class="form-control is-invalid" required=""
                                                   placeholder="Enter the price of product (EX: 500,000)"
                                                   data-type="currency" id="currency-field" value="${param.txtPrice}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                            <font color ="red">
                                            ${errors.productPriceFormatError}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtQuantity" class="col-sm-3 col-form-label">Quantity</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productQuantityFormatError}">
                                            <input type="text" name="txtQuantity" id="txtQuantity" class="form-control" required=""
                                                   placeholder="Enter quantity of product (EX: 100)" value="${param.txtQuantity}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                        </c:if>
                                        <c:if test="${not empty errors.productQuantityFormatError}">
                                            <input type="text" name="txtQuantity" id="txtQuantity" class="form-control is-invalid" required=""
                                                   placeholder="Enter quantity of product (EX: 100)" value="${param.txtQuantity}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                            <font color ="red">
                                            ${errors.productQuantityFormatError}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtCatory" class="col-sm-3 col-form-label">Category ID</label>
                                    <div class="col-sm-9">
                                        <select class="form-control form-select" name="txtCatory">
                                            <c:forEach var="cate" items="${category}">
                                                <option value="${cate.categoryID}">${cate.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="txtProductDetail" class="col-sm-3 col-form-label">Product Detail</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productDetailLengthError}">
                                            <input type="text" name="txtProductDetail" id="txtProductDetail" class="form-control"
                                                   required="" value="${param.txtProductDetail}"
                                                   placeholder="Enter product detail from 5 to 2000 characters">
                                        </c:if>
                                        <c:if test="${not empty errors.productDetailLengthError}">
                                            <input type="text" name="txtProductDetail" id="txtProductDetail" class="form-control is-invalid"
                                                   required="" value="${param.txtProductDetail}"
                                                   placeholder="Enter product detail from 5 to 2000 characters">
                                            <font color ="red">
                                            ${errors.productDetailLengthError}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="txtSize" class="col-sm-3 col-form-label">Size(gram)</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productSizeFormatError}">
                                            <input type="text" name="txtSize" id="txtSize" class="form-control" required=""
                                                   placeholder="Enter the number grams of product (Ex: 500)" value="${param.txtSize}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                        </c:if>

                                        <c:if test="${not empty errors.productSizeFormatError}">
                                            <input type="text" name="txtSize" id="txtSize" class="form-control is-invalid" required=""
                                                   placeholder="Enter the number grams of product (Ex: 500)" value="${param.txtSize}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                            <font color ="red">
                                            ${errors.productSizeFormatError}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <c:set var="birds" value="${sessionScope.LIST_BIRD}"/>
                                    <label for="txtBird" class="col-sm-3 col-form-label">Bird</label>
                                    <div class="col-sm-9">  
                                        <c:if test="${empty errors.productCategoriesBirdNotSelect}">
                                            <select name="txtBirds" id="birds" class="form-control" multiple>
                                                <c:forEach var="bird" items="${birds}">
                                                    <option value="${bird.birdID}"
                                                            >${bird.birdName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>

                                        <c:if test="${not empty errors.productCategoriesBirdNotSelect}">
                                            <select name="txtBirds" id="birds" class="form-control" multiple>
                                                <c:forEach var="bird" items="${birds}">
                                                    <option value="${bird.birdID}"
                                                            >${bird.birdName}</option>
                                                </c:forEach>
                                            </select>
                                            <font color ="red">
                                            ${errors.productCategoriesBirdNotSelect}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtAgeRecommendation" class="col-sm-3 col-form-label">Age Recommendation(months)</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productAgeRecommendationLengthError}">
                                            <input type="text" name="txtAgeRecommendation" id="txtAgeRecommendation" class="form-control"
                                                   required="" value="${param.txtAgeRecommendation}"
                                                   placeholder="Enter the number of months suitable for the bird(EX: 12)"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                        </c:if>
                                        <c:if test="${not empty errors.productAgeRecommendationLengthError}">
                                            <input type="text" name="txtAgeRecommendation" id="txtAgeRecommendation" class="form-control is-invalid"
                                                   required="" value="${param.txtAgeRecommendation}"
                                                   placeholder="Enter the number of months suitable for the bird(EX: 12)"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                            <font color ="red">
                                            ${errors.productAgeRecommendationLengthError}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtDate" class="col-sm-3 col-form-label">Date expire</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productDateExpireValueError}">
                                            <input type="text" name="txtDate" id="txtDate" class="form-control" required=""
                                                   placeholder="Enter number of months expire from the date manufacture (Ex: 12)" value="${param.txtDate}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                        </c:if>
                                        <c:if test="${not empty errors.productDateExpireValueError}">
                                            <input type="text" name="txtDate" id="txtDate" class="form-control is-invalid" required=""
                                                   placeholder="Enter number of months expire from the date manufacture (Ex: 12)" value="${param.txtDate}"
                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                            <font color ="red">
                                            ${errors.productDateExpireValueError}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtDateManufacture" class="col-sm-3 col-form-label">Date Manufacture</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productDateManuNotSelect}">
                                            <input type="date" name="txtDateManufacture" id="txtDateManufacture" class="form-control"
                                                   required="" value="${param.txtDateManufacture}">
                                        </c:if>
                                        <c:if test="${not empty errors.productDateManuNotSelect}">
                                            <input type="date" name="txtDateManufacture" id="txtDateManufacture" class="form-control is-invalid"
                                                   required="" value="${param.txtDateManufacture}">
                                            <font color ="red">
                                            ${errors.productDateManuNotSelect}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="txtStatus" class="col-sm-3 col-form-label">Status</label>
                                    <div class="col-sm-9">
                                        <select name="txtStatus" class="form-control form-select">
                                            <option value="1">Available</option>
                                            <option value="0">Not Available</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="txtCountry" class="col-sm-3 col-form-label ">Country</label>
                                    <div class="col-sm-9">
                                        <c:if test="${empty errors.productCountryNotSelect}">
                                            <input type="text" name="txtCountry" id="txtCountry" class="form-control" required=""
                                                   placeholder="Enter the country made this product (EX: Viet Nam)" value="${param.txtCountry}">
                                        </c:if>
                                        <c:if test="${not empty errors.productCountryNotSelect}">
                                            <input type="text" name="txtCountry" id="txtCountry" class="form-control" required=""
                                                   placeholder="Enter the country made this product (EX: Viet Nam)" value="${param.txtCountry}">
                                            <font color ="red">
                                            ${errors.productCountryNotSelect}
                                            </font>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="txtPicture" class="col-sm-3 col-form-label">Product Image</label>
                                    <div class="col-sm-9">
                                        <!-- input type="file" với id là "txtPicture" -->
                                        <input type="file" name="txtPicture" id="txtPicture" class="form-control-file" required="">
                                        <!-- thẻ img để hiển thị ảnh đã chọn -->
                                        <img id="preview" src="" alt="Preview Image" style="max-width: 100%; height: auto;">
                                    </div>
                                </div>

                                <script>
                                    // Lắng nghe sự kiện chọn tệp hình ảnh
                                    document.getElementById('txtPicture').addEventListener('change', function () {
                                        if (this.files && this.files[0]) {
                                            // Tạo ra một đường dẫn URL từ tệp hình ảnh được chọn
                                            var imageURL = URL.createObjectURL(this.files[0]);
                                            // Gán đường dẫn URL cho thuộc tính src của thẻ <img> tương ứng
                                            document.getElementById('preview').setAttribute('src', imageURL);
                                        }
                                    });
                                </script>
                                <div class="form-group row">
                                    <div class="col-sm-12 text-right">
                                        <button type="submit" name="btAction" value="Create New Product servlet" class="btn btn-primary">Create</button>
                                    </div>
                                </div>
                            </form>
                        </div>
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

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!--multi-select scripts js-->
        <script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>
        <script>
                                    //multi-select function\
                                    new MultiSelectTag('birds');

                                    //auto comma in textbox price function
                                    // Jquery Dependency

                                    $("input[data-type='currency']").on({
                                        keyup: function () {
                                            formatCurrency($(this));
                                        },
                                        blur: function () {
                                            formatCurrency($(this), "blur");
                                        }
                                    });


                                    function formatNumber(n) {
                                        // format number 1000000 to 1,234,567
                                        return n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                                    }


                                    function formatCurrency(input, blur) {
                                        // appends $ to value, validates decimal side
                                        // and puts cursor back in right position.

                                        // get input value
                                        var input_val = input.val();

                                        // don't validate empty input
                                        if (input_val === "") {
                                            return;
                                        }

                                        // original length
                                        var original_len = input_val.length;

                                        // initial caret position 
                                        var caret_pos = input.prop("selectionStart");

                                        // check for decimal
                                        if (input_val.indexOf(".") >= 0) {

                                            // get position of first decimal
                                            // this prevents multiple decimals from
                                            // being entered
                                            var decimal_pos = input_val.indexOf(".");

                                            // split number by decimal point
                                            var left_side = input_val.substring(0, decimal_pos);
                                            var right_side = input_val.substring(decimal_pos);

                                            // add commas to left side of number
                                            left_side = formatNumber(left_side);

                                            // validate right side
                                            right_side = formatNumber(right_side);

                                            // On blur make sure 2 numbers after decimal
                                            if (blur === "blur") {
                                                right_side += "00";
                                            }

                                            // Limit decimal to only 2 digits
                                            right_side = right_side.substring(0, 2);

                                            // join number by .
                                            input_val = left_side + "." + right_side;

                                        } else {
                                            // no decimal entered
                                            // add commas to number
                                            // remove all non-digits
                                            input_val = formatNumber(input_val);

                                        }

                                        // send updated string to input
                                        input.val(input_val);

                                        // put caret back in the right position
                                        var updated_len = input_val.length;
                                        caret_pos = updated_len - original_len + caret_pos;
                                        input[0].setSelectionRange(caret_pos, caret_pos);
                                    }

        </script>
    </body>
</html>

