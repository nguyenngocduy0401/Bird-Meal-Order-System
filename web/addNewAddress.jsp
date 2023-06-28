<%-- 
    Document   : addresses
    Created on : Jun 15, 2023, 10:39:14 AM
    Author     : haong
--%>
<style></style>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<html lang="en">
    <style>
        /*edit link color*/
        .title a:visited {
            color: #333333;
        }
        .title a:hover{
            color: #7AB730;
        }
        /*edit tilte position*/
        .title a>div{
            padding-left: 26px;
        }
        .title div{
            margin-top: 7px;
        }
        .content{
            margin-top: 60px;
        }
        .dropdown:hover>.dropdown-menu {
            display: block;
        }
        .dropdown>.nav-link:active {
            pointer-events: none;
        }
        .dropdown-menu {
            right: 0;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <head>
        <meta charset="utf-8">
        <title>Bird Meal Order System</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

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

        <!-- Jquery libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>


        <!--         Libraries sweetalert2 
                <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>-->
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid border-bottom d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-4 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Địa Chỉ</h6>
                            <span>Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 text-center border-start border-end py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-envelope-open fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Email Us</h6>

                            <span>fpt@example.com</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-phone-vibrate fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Call Us</h6>
                            <span>+123454654</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="MainController?btAction=Home" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store
                </h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <div class="col-md-7 container-fluid">

                    <div class="search">

                    </div>
                </div>
                <div class="navbar-nav ms-auto py-0">
                    <a href="MainController?btAction=Home" class="nav-item nav-link active">Home</a>
                    <a href="blog.html" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i
                            class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown"><i
                                class="bi bi-person fs-1 text-primary me-1"></i>
                        </a>
                        <div class="dropdown-menu m-0 dropdown-menu-end">
                            <a href="details.jsp" class="dropdown-item">My profile</a>
                            <a href="MainController?btAction=Purchase" class="dropdown-item">My purchase</a>
                            <a href="LogoutController" class="dropdown-item">Logout</a>

                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <section>
            <div class="container content" id="reloadPage">

                <div class="row ">
                    <div class="title col-md-3"   >
                        <div class="" style="width:100% auto; color: black; font-weight: bold;">
                            <div class="">My account</div>
                        </div>
                        <div>
                            <div>
                                <div class="">
                                    <div><a class="" aria-current="page" href="details.jsp">
                                            <div class="">Profile</div>
                                        </a></div>
                                </div>
                            </div>
                            <div>
                                <div class="">
                                    <div><a class="" href="MainController?btAction=Purchase">
                                            <div class="">Purchase</div>
                                        </a></div>
                                </div>
                            </div>
                        </div>
                        <div class="" style="width: auto; color: black; font-weight: bold;">
                            <div class="">Profile settings</div>
                        </div>
                        <div>
                            <div>
                                <div class=""><a class="" href="editInformation.jsp">
                                        <div class="">Edit profile</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="ShowListAddressController">
                                        <div class="" style="color: #7AB730" >Address book</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="editPassword.jsp">
                                        <div class="">Change my password</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="removeAccount.jsp">
                                        <div class="">Remove account</div>
                                    </a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header">Register new address</div>
                            <div class="card-body">
                                <form action = "AddNewAddressController"> 
                                    <c:set var="errors" value="${requestScope.EDIT_INFORMATION_ERROR}"/>

                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Full name</label>
                                        <c:choose>
                                            <c:when test="${not empty errors.fullnameLengthError}">
                                                <input type="text" class="form-control bg is-invalid" placeholder="Full name*(2 - 50 characters)"
                                                       pattern=".{2,50}" title="Your name must be between 2 and 50 characters." required=""   name="txtFullName" value="${param.txtFullName}">
                                                <font color ="red">
                                                ${errors.fullnameLengthError}
                                                </font>
                                            </c:when>
                                            <c:when test="${empty errors.fullnameLengthError && empty errors.phoneNumberFormatError}">
                                                <input class="form-control bg" id="inputUsername" type="text" name="txtFullName"
                                                       pattern=".{2,50}"  title="Your name must be between 2 and 50 characters."  required="" placeholder="Enter your username" >
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" class="form-control bg" placeholder="Full name*(2 - 50 characters)"
                                                       pattern=".{2,50}"  title="Your name must be between 2 and 50 characters." required=""  name="txtFullName" value="${param.txtFullName}">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>


                                    <div class="mb-3">
                                        <label class="small mb-1">Address</label>
                                        <div>
                                            <select  name="ddlProvince" required=""  class="form-control bg-white form-select mt-1 " id="city" aria-label=".form-select-sm">
                                                <option value="" selected>Select your province</option>           
                                            </select>

                                            <select name="ddlDistrict" required="" class="form-control bg-white form-select mt-3 " id="district" aria-label=".form-select-sm">
                                                <option value="" selected>Select your district</option> 
                                            </select>

                                            <select name="ddlWard" required="" class="form-control bg-white form-select mt-3" id="ward" aria-label=".form-select-sm">
                                                <option value="" selected>Select your ward</option>
                                            </select>
                                        </div>
                                        <div>
                                            <input type="text" class="form-control bg  mt-3 mb-2" placeholder="Address details"
                                                   name="txtAddressDetails" >
                                        </div>
                                    </div>

                                    <div class="row gx-3 mb-3">
                                        <!-- Form Group (phone number)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputPhone">Phone number</label>
                                            <c:choose>
                                                <c:when test="${not empty errors.phoneNumberFormatError}">
                                                    <input class="form-control is-invalid" id="inputPhone" type="tel" 
                                                           placeholder="Enter your phone number" value="${param.txtPhoneNumber}" name="txtPhoneNumber" required="" pattern="[0-9]{10}"
                                                           onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                                    <font color ="red">
                                                    ${errors.phoneNumberFormatError}
                                                    </font>
                                                </c:when>
                                                <c:when test="${empty errors.phoneNumberFormatError && empty errors.fullnameLengthError}">
                                                    <input class="form-control" id="inputPhone" type="tel" 
                                                           placeholder="Enter your phone number" s name="txtPhoneNumber" required="" pattern="[0-9]{10}"
                                                           onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="form-control" id="inputPhone" type="tel" 
                                                           placeholder="Enter your phone number" value="${param.txtPhoneNumber}" name="txtPhoneNumber" required="" pattern="[0-9]{10}"
                                                           onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))">
                                                </c:otherwise>
                                            </c:choose>


                                        </div>

                                    </div>
                                    <div>

                                        <c:if test="${requestScope.EDIT_INFORMATION_STATUS == false}">
                                            <font color ="Red">
                                            Edit information failed. Please try again.
                                            </font>
                                        </c:if>
                                    </div>

                                    <!-- Save changes button-->
                                    <button class="btn btn-primary" type="submit">Register</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
        <script type="text/javascript">
                                                               //check session user
                                                               if (${empty sessionScope.user}) {
                                                                   swal.fire({
                                                                       icon: 'warning',
                                                                       title: "Warning!",
                                                                       text: "Your need to login",
                                                                       confirmButtonText: 'Click to login'
                                                                   }).then(function () {
                                                                       window.location = "login.jsp";
                                                                   });
                                                               }
                                                               //filter address function
                                                               var citis = document.getElementById("city");
                                                               var districts = document.getElementById("district");
                                                               var wards = document.getElementById("ward");
                                                               var Parameter = {
                                                                   url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
                                                                   method: "GET",
                                                                   responseType: "application/json",
                                                               };
                                                               var promise = axios(Parameter);
                                                               promise.then(function (result) {
                                                                   renderCity(result.data);
                                                               });
                                                               function renderCity(data) {
                                                                   for (const x of data) {
                                                                       var opt = document.createElement('option');
                                                                       opt.value = x.Name;
                                                                       opt.text = x.Name;
                                                                       opt.setAttribute('data-id', x.Id);
                                                                       citis.options.add(opt);
                                                                   }
                                                                   citis.onchange = function () {
                                                                       district.length = 1;
                                                                       ward.length = 1;
                                                                       if (this.options[this.selectedIndex].dataset.id != "") {
                                                                           const result = data.filter(n => n.Id === this.options[this.selectedIndex].dataset.id);

                                                                           for (const k of result[0].Districts) {
                                                                               var opt = document.createElement('option');
                                                                               opt.value = k.Name;
                                                                               opt.text = k.Name;
                                                                               opt.setAttribute('data-id', k.Id);
                                                                               district.options.add(opt);
                                                                           }
                                                                       }
                                                                   };
                                                                   district.onchange = function () {
                                                                       ward.length = 1;
                                                                       const dataCity = data.filter((n) => n.Id === citis.options[citis.selectedIndex].dataset.id);
                                                                       if (this.options[this.selectedIndex].dataset.id != "") {
                                                                           const dataWards = dataCity[0].Districts.filter(n => n.Id === this.options[this.selectedIndex].dataset.id)[0].Wards;

                                                                           for (const w of dataWards) {
                                                                               var opt = document.createElement('option');
                                                                               opt.value = w.Name;
                                                                               opt.text = w.Name;
                                                                               opt.setAttribute('data-id', w.Id);
                                                                               wards.options.add(opt);
                                                                           }
                                                                       }
                                                                   };
                                                               }

                                                               var city1 = document.getElementById("city1");
                                                               var district1 = document.getElementById("district1");
                                                               var ward1 = document.getElementById("ward1");
                                                               var Parameter1 = {
                                                                   url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
                                                                   method: "GET",
                                                                   responseType: "application/json",
                                                               };
                                                               var promise1 = axios(Parameter1);
                                                               promise1.then(function (result) {
                                                                   renderCity1(result.data);
                                                               });

                                                               function renderCity1(data) {
                                                                   for (const x of data) {
                                                                       var opt = document.createElement('option');
                                                                       opt.value = x.Name;
                                                                       opt.text = x.Name;
                                                                       opt.setAttribute('data-id', x.Id);
                                                                       city1.options.add(opt);
                                                                   }

                                                                   city1.onchange = function () {
                                                                       district1.length = 1;
                                                                       ward1.length = 1;
                                                                       if (this.options[this.selectedIndex].dataset.id != "") {
                                                                           const result = data.filter(n => n.Id === this.options[this.selectedIndex].dataset.id);

                                                                           for (const k of result[0].Districts) {
                                                                               var opt = document.createElement('option');
                                                                               opt.value = k.Name;
                                                                               opt.text = k.Name;
                                                                               opt.setAttribute('data-id', k.Id);
                                                                               district1.options.add(opt);
                                                                           }
                                                                       }
                                                                   };

                                                                   district1.onchange = function () {
                                                                       ward1.length = 1;
                                                                       const dataCity = data.filter((n) => n.Id === city1.options[city1.selectedIndex].dataset.id);
                                                                       if (this.options[this.selectedIndex].dataset.id != "") {
                                                                           const dataWards = dataCity[0].Districts.filter(n => n.Id === this.options[this.selectedIndex].dataset.id)[0].Wards;

                                                                           for (const w of dataWards) {
                                                                               var opt = document.createElement('option');
                                                                               opt.value = w.Name;
                                                                               opt.text = w.Name;
                                                                               opt.setAttribute('data-id', w.Id);
                                                                               ward1.options.add(opt);
                                                                           }
                                                                       }
                                                                   };
                                                               }
        </script>
        <!-- JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>