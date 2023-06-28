<%-- 
    Document   : register
    Created on : May 29, 2023, 11:39:19 PM
    Author     : haong
--%>
<style></style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Bird Meal Order System</title>
        <meta charset="UTF-8">
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
    </head>


    <body>
        <!-- Topbar Start -->
        <div class="container-fluid border-bottom d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-3 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Our Address</h6>
                            <span>Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-center border-start border-end py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-envelope-open fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Email Us</h6>

                            <span>fpt@example.com</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-phone-vibrate fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Call Us</h6>
                            <span>+123454654</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-facebook fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Facebook</h6>
                            <span><a href="https://www.facebook.com/" class="link">https://www.facebook.com/</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
            <a href="MainController?btAction=Home" class="navbar-brand ms-lg-5">
                <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <div class="col-md-7 container-fluid">


                </div>
                <div class="navbar-nav ms-auto py-0">
                    <a href="MainController?btAction=Home" class="nav-item nav-link active">Home</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                    <a href="" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <div class="container-fluid pt-5">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Register a new account</h6>
                    <h1 class="display-5 text-uppercase mb-0">Register a new account</h1>
                </div>
                <div class="row g-5">
                    <div class="col-lg-7">
                        <form action="RegisterAccountServlet" class="form-floating">
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
                                    <input type="text" class="form-control bg border-0 px-4" placeholder="Email*"
                                           style="height: 55px;" name="txtEmail" value="${sessionScope.REGISTRATION.email}" readonly="">
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
                                        <select  name="ddlProvince"  class="form-control bg-white border-0 px-4 form-select mt-1 " id="city" aria-label=".form-select-sm" style="height: 55px;">
                                            <option value="" selected>Select your province</option>           
                                        </select>

                                        <select name="ddlDistrict"  class="form-control bg-white border-0 px-4 form-select mt-3 " id="district" aria-label=".form-select-sm" style="height: 55px;">
                                            <option value="" selected>Select your district</option>
                                        </select>

                                        <select name="ddlWard"  class="form-control bg-white border-0 px-4 form-select mt-3" id="ward" aria-label=".form-select-sm" style="height: 55px;">
                                            <option value="" selected>Select your ward</option>
                                        </select>
                                    </div>
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
                                        <button class="btn btn-primary w-100 py-3 mt-3" type="submit">Register</button>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
            <script>
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
                                                   }
            </script>
    </body>
</html>

};
