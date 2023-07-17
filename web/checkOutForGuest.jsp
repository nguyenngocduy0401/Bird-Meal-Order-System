<%-- 
    Document   : checkOutForGuest
    Created on : Jun 13, 2023, 11:06:21 PM
    Author     : Duy
--%>

<!DOCTYPE html>
<html lang="en">
    <style>
        .card {
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
        }

        /*dropdown hover*/
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
    <%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="sample.dto.ProductDTO" %>
    <%@ page import="sample.dao.ProductDAO" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>



    </script>
    <head>
        <meta charset="utf-8">
        <title>Bird Meal Order System</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport

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

        <!--Libraries boostrap 5.0.0-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
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
                <div class="col-lg-3 border-start text-center py-2">
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
                    <a href="MainController?btAction=Home" class="nav-item nav-link ">Home</a>
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
                    <a href="viewcart.jsp" class="nav-item nav-link pt-3 active "><i class="bi bi-cart  fs-1 text-primary me-1"></i></a>
                        <c:if test="${not empty sessionScope.user}">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link pt-3" data-bs-toggle="dropdown">
                                <i class="bi bi-person fs-1 text-primary me-1"></i>
                            </a>
                            <div class="dropdown-menu m-0 dropdown-menu-end">
                                <a href="details.jsp" class="dropdown-item">My profile</a>
                                <a href="MainController?btAction=Purchase" class="dropdown-item">My purchase</a>
                                <a href="LogoutController" class="dropdown-item">Logout</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <a href="login.jsp" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Login <i class="bi bi-arrow-right"></i></a>
                        </c:if>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <div class="container-fluid pt-5 " >
            <c:set var="list" value="${sessionScope.cartCheckOutForGuest}" />
            <!-- NOT EMPTY LIST OF SELECTED ITEMS FOR CHECK-OUT -->
            <c:if test="${not empty list}">
                <div class="container ">
                    <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                        <h6 class="text-primary text-uppercase">Check Out</h6>
                        <h1 class="display-5 text-uppercase mb-0">Check Out</h1>
                    </div>
                    <div class="card container px-5 ps-5 px-lg-5 my-5 ms-5 custom-card">
                        <form action="MainController">
                            <c:set var="errors" value="${requestScope.PLACE_ORDER_INFORMATION_ERROR}"/>
                            <div class="row px-5 ps-5 px-lg-5 my-5 ms-3">
                                <div class="col-md-6" id="listInfo">

                                    <div class="row g-3 " id="addressCustomer" >

                                        <c:if test="${not empty sessionScope.user}">
                                            <c:if test="${not empty sessionScope.addressList}">
                                                <c:forEach var="address" items="${addressList}" >
                                                    <c:set var="userID" value="${address.userID}"/>
                                                    <c:set var="addressID" value="${address.addressID}"/>
                                                    <c:set var="fullName" value="${address.fullName}"/>
                                                    <c:set var="addressDetail" value="${address.addressDetail}"/>
                                                    <c:set var="phoneNumber" value="${address.phoneNumber}"/>
                                                    <!--split address-->
                                                    <c:set var="addressDetails" value="${addressDetail.split(', ')[0]}" />
                                                    <c:set var="ward" value="${addressDetail.split(', ')[1]}" />
                                                    <c:set var="district" value="${addressDetail.split(', ')[2]}" />
                                                    <c:set var="province" value="${addressDetail.split(', ')[3]}" />

                                                    <div class="row g-3 container border border-opacity-25 border-secondary rounded border-dark pb-3 " style="border-radius: 5px; ">
                                                        <div class="col-md-1 ">
                                                            <div class=" text-center py-4">
                                                                <input type="radio" name="selectAddress" onclick="chooseAddress('${ward}', '${district}', '${province}')"  value="${addressID}" required="" />
                                                            </div>

                                                        </div>
                                                        <div class="col-md-11 ">
                                                            <div class="row">
                                                                <div class="col-md-5   py-1"  >${fullName}</div>
                                                                <div class="col-md-4  border-start  py-1">${phoneNumber}</div>
                                                                <div class="col-md-3 "></div>
                                                            </div>
                                                            <div class="">${addressDetail}</div>
                                                        </div>



                                                    </div>
                                                </c:forEach>

                                                <div>Notes </div>

                                                <textarea class="form-control row g-3 bg-white border-1 border-dark px-5 " name="txtNotes"
                                                          style="height: 100px; border-radius: 5px;"></textarea>
                                                <div class="row ">
                                                    <div class="col-md-6">

                                                    </div>
                                                    <div class="row mt-4 d-flex align-items-center">
                                                        <div class="col-sm-7   text-right">
                                                        </div>
                                                        <div class="col-sm-5   ">
                                                            <a href="ShowListAddressController"><button class="btn btn-primary mb-4 btn-lg pl-5 pr-5" style="font-size: 14px" type="button" name="btAction" value="submitCheckOutGuest">Manage my address</button></a>
                                                        </div>

                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty sessionScope.addressList}">
                                                <div class="mb-3">
                                                    <label class="small mb-1" for="inputUsername">Full name*</label>
                                                    <c:choose>
                                                        <c:when test="${not empty errors.fullnameLengthError}">
                                                            <input type="text" class="form-control bg is-invalid" placeholder="Full name*(2 - 50 characters)"
                                                                   name="txtFullName" required=""  value="${param.txtFullName}"> 
                                                            <font color ="red">
                                                            ${errors.fullnameLengthError}
                                                            </font>
                                                        </c:when>
                                                        <c:when test="${empty errors.fullnameLengthError && empty errors.phoneNumberFormatError
                                                                        && empty errors.provinceNotSelect && empty errors.districtNotSelect && empty errors.wardNotSelect && empty errors.addressLengthError}">
                                                                <input class="form-control bg" id="inputUsername" type="text" name="txtFullName"
                                                                       placeholder="Enter your username" required="" value="${user.fullName}">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="text" class="form-control bg" placeholder="Full name*(2 - 50 characters)"
                                                                   name="txtFullName" required=""  value="${param.txtFullName}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!--                                                    <input class="form-control bg" id="inputUsername" type="text" name="txtFullName"
                                                                                                               value="${user.fullName}" required=""  placeholder="Enter your Fullname" >-->


                                                </div>
                                                <div class="form-group mb-3">
                                                    <label class="small mb-1">Address*</label>
                                                    <div>
                                                        <!--check empty province-->
                                                        <c:if test="${not empty errors.provinceNotSelect}">
                                                            <select  name="ddlProvince" rules="required" required="" class="form-control bg-white form-select mt-1 is-invalid" id="city" aria-label=".form-select-sm">
                                                                <option value="" selected>Select your province</option>           
                                                            </select>
                                                            <font color ="red">
                                                            ${errors.provinceNotSelect}
                                                            </font>
                                                        </c:if>
                                                        <c:if test="${empty errors.provinceNotSelect}">
                                                            <select  name="ddlProvince" rules="required" required="" class="form-control bg-white form-select mt-1 " id="city" aria-label=".form-select-sm">
                                                                <option value="" selected>Select your province</option>           
                                                            </select>
                                                        </c:if>

                                                        <!--check empty district-->    
                                                        <c:if test="${not empty errors.districtNotSelect}">
                                                            <select  name="ddlDistrict" rules="required" required="" class="form-control bg-white form-select mt-3 is-invalid" id="district" aria-label=".form-select-sm">
                                                                <option value="" selected>Select your district</option> 
                                                            </select>
                                                            <font color ="red">
                                                            ${errors.districtNotSelect}
                                                            </font>
                                                        </c:if>
                                                        <c:if test="${empty errors.districtNotSelect}">
                                                            <select  name="ddlDistrict" rules="required" required="" class="form-control bg-white form-select mt-3" id="district" aria-label=".form-select-sm">
                                                                <option value="" selected>Select your district</option> 
                                                            </select>          
                                                        </c:if>
                                                        <!--check empty ward-->
                                                        <c:if test="${not empty errors.wardNotSelect}">
                                                            <select name="ddlWard" required="" class="form-control bg-white form-select mt-3 is-invalid" id="ward" aria-label=".form-select-sm">
                                                                <option value="" selected>Select your ward</option>
                                                            </select>
                                                            <font color ="red">
                                                            ${errors.wardNotSelect}
                                                            </font>
                                                        </c:if>
                                                        <c:if test="${empty errors.wardNotSelect}">
                                                            <select name="ddlWard" required="" class="form-control bg-white form-select mt-3" id="ward" aria-label=".form-select-sm">
                                                                <option value="" selected>Select your ward</option>
                                                            </select>
                                                        </c:if>
                                                        <!--                                                        <select  name="ddlProvince" required="" class="form-control bg-white form-select mt-1 " id="city" aria-label=".form-select-sm">
                                                                                                                    <option value="" selected>Select your province</option>           
                                                                                                                </select>
                                                                                                                <select  name="ddlDistrict" required="" class="form-control bg-white form-select mt-3 " id="district" aria-label=".form-select-sm">
                                                                                                                    <option value="" selected>Select your district</option> 
                                                                                                                </select>
                                                        
                                                                                                                <select name="ddlWard" required="" class="form-control bg-white form-select mt-3" id="ward" aria-label=".form-select-sm">
                                                                                                                    <option value="" selected>Select your ward</option>
                                                                                                                </select>-->
                                                    </div>
                                                    <!--check length address detail-->
                                                    <div>
                                                        <c:if test="${not empty errors.addressLengthError}">
                                                            <input type="text" class="form-control bg  mt-3 mb-2 is-invalid" placeholder="Address details"
                                                                   name="txtAddressDetails" required="" id="addressDetails"  >
                                                            </select>
                                                            <font color ="red">
                                                            ${errors.addressLengthError}
                                                            </font>
                                                        </c:if>
                                                        <c:if test="${empty errors.addressLengthError}">
                                                            <input type="text" class="form-control bg  mt-3 mb-2" placeholder="Address details"
                                                                   name="txtAddressDetails" required="" id="addressDetails"  >
                                                        </c:if>
                                                        <!--                                                    
                                                                                                                <input type="text" class="form-control bg  mt-3 mb-2" placeholder="Address details"
                                                                                                                       name="txtAddressDetails" required="" id="addressDetails"  >-->
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="small mb-1" for="phoneNumber">Phone number*</label>
                                                    <c:choose>
                                                        <c:when test="${not empty errors.phoneNumberFormatError}">
                                                            <input class="form-control is-invalid" id="phoneNumber" type="text" 
                                                                   placeholder="Enter your phone number" value="${param.txtPhoneNumber}" name="txtPhoneNumber"
                                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))" required="">
                                                            <font color ="red">
                                                            ${errors.phoneNumberFormatError}
                                                            </font>
                                                        </c:when>
                                                        <c:when test="${empty errors.phoneNumberFormatError && empty errors.fullnameLengthError
                                                                        && empty errors.provinceNotSelect && empty errors.districtNotSelect && empty errors.wardNotSelect && empty errors.addressLengthError}">
                                                                <input class="form-control" id="phoneNumber" type="text" 
                                                                       placeholder="Enter your phone number" value="${user.phoneNumber}" name="txtPhoneNumber" 
                                                                       onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))" required="">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-control" id="phoneNumber" type="text" 
                                                                   placeholder="Enter your phone number" value="${param.txtPhoneNumber}" name="txtPhoneNumber"
                                                                   onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))" required="">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <!--                                                    <input class="form-control" id="phoneNumber" type="text" 
                                                                                                               placeholder="Enter your phone number" value="${user.phoneNumber}" name="txtPhoneNumber" 
                                                                                                               onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))"
                                                                                                               title="Please enter a 10-digit phone number"
                                                                                                               required="">-->
                                                </div>
                                                <div class="col-12">
                                                    Notes 
                                                    <textarea class="form-control bg-white border-1 px-4" name="txtNotes"
                                                              style="height: 100px;"></textarea>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty sessionScope.user}">
                                            <div class="mb-3">
                                                <label class="small mb-1" for="inputUsername">Full name</label>
                                                <c:if test="${not empty errors.fullnameLengthError}">
                                                    <input class="form-control bg is-invalid" id="inputUsername" type="text" name="txtFullName"
                                                           placeholder="Enter your Fullname" value="${param.txtFullName}" required="" >
                                                    </select>
                                                    <font color ="red">
                                                    ${errors.fullnameLengthError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.fullnameLengthError}">
                                                    <input class="form-control bg" id="inputUsername" type="text" name="txtFullName"
                                                           placeholder="Enter your Fullname" required="" >
                                                </c:if>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label class="small mb-1">Address*</label>
                                                <div>
                                                    <!--check empty province-->
                                                    <c:if test="${not empty errors.provinceNotSelect}">
                                                        <select  name="ddlProvince" rules="required" required="" class="form-control bg-white form-select mt-1 is-invalid" id="city" aria-label=".form-select-sm">
                                                            <option value="" selected>Select your province</option>           
                                                        </select>
                                                        <font color ="red">
                                                        ${errors.provinceNotSelect}
                                                        </font>
                                                    </c:if>
                                                    <c:if test="${empty errors.provinceNotSelect}">
                                                        <select  name="ddlProvince" rules="required" required="" class="form-control bg-white form-select mt-1 " id="city" aria-label=".form-select-sm">
                                                            <option value="" selected>Select your province</option>           
                                                        </select>
                                                    </c:if>

                                                    <!--check empty district-->    
                                                    <c:if test="${not empty errors.districtNotSelect}">
                                                        <select  name="ddlDistrict" rules="required" required="" class="form-control bg-white form-select mt-3 is-invalid" id="district" aria-label=".form-select-sm">
                                                            <option value="" selected>Select your district</option> 
                                                        </select>
                                                        <font color ="red">
                                                        ${errors.districtNotSelect}
                                                        </font>
                                                    </c:if>
                                                    <c:if test="${empty errors.districtNotSelect}">
                                                        <select  name="ddlDistrict" rules="required" required="" class="form-control bg-white form-select mt-3" id="district" aria-label=".form-select-sm">
                                                            <option value="" selected>Select your district</option> 
                                                        </select>          
                                                    </c:if>
                                                    <!--check empty ward-->
                                                    <c:if test="${not empty errors.wardNotSelect}">
                                                        <select name="ddlWard" required="" class="form-control bg-white form-select mt-3 is-invalid" id="ward" aria-label=".form-select-sm">
                                                            <option value="" selected>Select your ward</option>
                                                        </select>
                                                        <font color ="red">
                                                        ${errors.wardNotSelect}
                                                        </font>
                                                    </c:if>
                                                    <c:if test="${empty errors.wardNotSelect}">
                                                        <select name="ddlWard" required="" class="form-control bg-white form-select mt-3" id="ward" aria-label=".form-select-sm">
                                                            <option value="" selected>Select your ward</option>
                                                        </select>
                                                    </c:if>
                                                    <!--                                                        <select  name="ddlProvince" required="" class="form-control bg-white form-select mt-1 " id="city" aria-label=".form-select-sm">
                                                                                                                <option value="" selected>Select your province</option>           
                                                                                                            </select>
                                                                                                            <select  name="ddlDistrict" required="" class="form-control bg-white form-select mt-3 " id="district" aria-label=".form-select-sm">
                                                                                                                <option value="" selected>Select your district</option> 
                                                                                                            </select>
                                                    
                                                                                                            <select name="ddlWard" required="" class="form-control bg-white form-select mt-3" id="ward" aria-label=".form-select-sm">
                                                                                                                <option value="" selected>Select your ward</option>
                                                                                                            </select>-->
                                                </div>
                                                <!--check length address detail-->
                                                <div>
                                                    <c:if test="${not empty errors.addressLengthError}">
                                                        <input type="text" class="form-control bg  mt-3 mb-2 is-invalid" placeholder="Address details"
                                                               name="txtAddressDetails" required="" id="addressDetails"  >
                                                        </select>
                                                        <font color ="red">
                                                        ${errors.addressLengthError}
                                                        </font>
                                                    </c:if>
                                                    <c:if test="${empty errors.addressLengthError}">
                                                        <input type="text" class="form-control bg  mt-3 mb-2" placeholder="Address details"
                                                               name="txtAddressDetails" required="" id="addressDetails"  >
                                                    </c:if>
                                                </div>
                                            </div>
                                            <!--check phone-->
                                            <div class="form-group mb-3">
                                                <label class="small mb-1" for="phoneNumber">Phone number</label>
                                                <c:if test="${not empty errors.phoneNumberFormatError}">
                                                    <input class="form-control is-invalid" id="phoneNumber" type="tel" 
                                                           placeholder="Enter your phone number" value="${param.txtPhoneNumber}" name="txtPhoneNumber"
                                                           onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))"
                                                           title="Please enter a 10-digit phone number"
                                                           required="">
                                                    <font color ="red">
                                                    ${errors.phoneNumberFormatError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.phoneNumberFormatError}">
                                                    <input class="form-control" id="phoneNumber" type="tel" 
                                                           placeholder="Enter your phone number" value="${param.txtPhoneNumber}" name="txtPhoneNumber" 
                                                           onkeypress="return (event.charCode != 8 && event.charCode == 0 || (event.charCode >= 48 && event.charCode <= 57))"
                                                           title="Please enter a 10-digit phone number"
                                                           required="">
                                                </c:if>
                                            </div>
                                            <!--email-->
                                            <div class="form-check form-switch" style="margin-left: 8px;">
                                                <input class="form-check-input" type="checkbox"  value="ON"  onclick="switchCheck()" name="chkEmail" role="switch" id="flexSwitchCheckDefault"
                                                       <c:if test="${param.chkEmail eq 'ON'}">
                                                               checked="checked"
                                                       </c:if>
                                               >
                                                <label class="form-check-label" for="flexSwitchCheckDefault">Send status order to my email</label>
                                            </div>
                                            <div class="form-group mb-3" id="email" style="display: none;">
                                                <c:if test="${not empty errors.emailFormatError}">
                                                    <input type="text" class="form-control is-invalid" placeholder="Enter your email" 
                                                       name="txtEmail"value="${param.txtEmail}">
                                                    <font color ="red">
                                                    ${errors.emailFormatError}
                                                    </font>
                                                </c:if>
                                                <c:if test="${empty errors.emailFormatError}">
                                                    <input type="text" class="form-control " placeholder="Enter your email" 
                                                       name="txtEmail"value="${param.txtEmail}">
                                                </c:if>
                                            </div>
                                            <div class="col-12">
                                                Notes 
                                                <textarea class="form-control bg-white border-1 px-4" name="txtNotes" 
                                                          style="height: 100px;" ></textarea>
                                            </div>
                                        </c:if>


                                    </div>
                                    <c:forEach var="item" items="${cartCheckOutForGuest}" varStatus="counter">
                                        <c:set var="pid" value="${item.key}" />
                                        <c:set var="product" value="${ProductDAO.getProductByID(pid)}" />
                                        <c:set var="quantity" value="${item.value}" />
                                        <c:set var="price" value="${product.price}" />
                                        <c:set var="total" value="${total + (quantity * product.price)}"/>
                                        <c:set var="subTotal" value="${subTotal + (quantity * product.price)}"/>
                                        <c:set var="weight" value="${product.size}"/>
                                        <c:set var="totalWeight" value="${totalWeight + (quantity * product.size)}"/>

                                    </c:forEach>

                                    <div class="float-right text-right">
                                        <input type="hidden" id="totalWeight" value="${totalWeight}" name="totalWeight"/>
                                        <!--                                        <h4>Subtotal:</h4>
                                                                                <div class="row mt-4 d-flex align-items-center">
                                                                                    <div class="col-sm-6 order-md-2">
                                                                                        <h1><span id="subtotalAmount"></span></h1>
                                                                                    </div>
                                                                                </div>-->
                                        <div class="row mt-4 d-flex align-items-center">
                                            <div class="col-sm-8   text-right">
                                                <!--<button class="btn btn-primary mb-4 btn-lg pl-5 pr-5" type="button" onclick="previewOrder()">previewOrder</button>-->
                                            </div>
                                            <div class="col-sm-4   ">
                                                <button class="btn btn-primary mb-4 btn-lg pl-5 pr-5" type="submit" id="button" name="btAction" value="submitCheckOutGuest">Place Order</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div id="listproduct" class="card col-md-6 text-right border" style="border-radius: 10px;"> 
                                    <table id="shoppingCart" class="table table-condensed table-responsive" style="margin-bottom: 7px">
                                        <thead>
                                            <tr>    
                                                <th style="width:70%">Name</th>
                                                <th style="width:8%">Price</th>
                                                <th style="width:8%">Quantity</th>
                                                <th style="width: 8%">

                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="item" items="${cartCheckOutForGuest}" varStatus="counter">
                                                <c:set var="pid" value="${item.key}" />
                                                <c:set var="product" value="${ProductDAO.getProductByID(pid)}" />
                                                <c:set var="quantity" value="${item.value}" />
                                                <c:set var="price" value="${product.price}" />
                                                <c:set var="total" value="${total + (quantity * product.price)}"/>
                                                <tr>
                                                    <td data-th="Product">
                                                        <div class="row">
                                                            <div class="col-md-3 text-left">
                                                                <img src=${product.imgPath} alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                                            </div>
                                                            <div class="col-md-9 text-left mt-sm-2">
                                                                <h4>${product.productName}</h4>
                                                                <p class="font-weight-light">Size: ${product.size}g</p>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td data-th="Price"><div style="font-weight: bold;">${price}₫</div></td>
                                                    <td data-th="Quantity">
                                                        <div class="form-control bg-white form-control-lg text-center">${item.value}</div> 
                                                    </td>
                                                <tr>
                                                </c:forEach>

                                        </tbody>

                                    </table>
                                    <table width="100%" style="border-collapse: separate; border-spacing: 10px 8px; border-bottom: 1px solid black; padding-bottom: 16px;">
                                        <tbody>
                                            <tr class="" style="padding-top: 0">
                                                <td class="" width="50%">Subtotal</td>
                                                <td class="" style="text-align: right">
                                                    <span class="" id="subtotalAmount">
                                                    </span>
                                                </td>
                                            </tr>


                                            <tr class=""">
                                                <td class="">Shipping fee</td>
                                                <td class="" style="text-align: right">
                                                    <input type="hidden" value="" id="shippingFeeValue" name="shippingFeeValue"/>
                                                    <span class="" id="shippingFee" value="">—
                                                    </span>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <table width="100%" style="border-collapse: separate; border-spacing: 10px 8px; padding-top: 5px">
                                        <tbody class="" >
                                            <tr class="">
                                                <td class=""  width="50%">
                                                    <span style="font-weight: 600; font-size: large;">Total</span>
                                                </td>
                                                <td class="" style="text-align: right">
                                                    <span class="" style="font-size: 0.85714em;
                                                          vertical-align: 0.2em;
                                                          margin-right: 0.5em;
                                                          color: #969696;">VND</span>

                                                    <span class="" id="total" name="total" value="" style="
                                                          font-size: 1.71429em;
                                                          font-weight: 500;
                                                          letter-spacing: -0.04em;
                                                          color: #4b4b4b;
                                                          line-height: 1em;">
                                                    </span>
                                                </td>
                                            </tr>   
                                            </tfoot>
                                    </table>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="no-list row">
            <c:if test="${empty list}">
                <h2>No Selected Item for CheckOut!</h2>
                <a class="btn btn-primary" href="viewcart.jsp">Go Back To Your Cart</a> 

            </c:if>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
    <script src="scripts/validator.js"></script>
    <script>
                                                               //filter location
                                                               var myHeaders = new Headers();
                                                               myHeaders.append("token", "8971acfc-10b0-11ee-bb28-f6a6bf301a4e");
                                                               var requestOptions = {
                                                                   method: 'GET',
                                                                   responseType: "application/json",
                                                                   headers: myHeaders
                                                               };
                                                               var districID = null;
                                                               var wardCode = "";
                                                               if (${empty sessionScope.addressList}) {
                                                                   var citis = document.getElementById("city");
                                                                   var districts = document.getElementById("district");
                                                                   var wards = document.getElementById("ward");



                                                                   fetch("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province", requestOptions)
                                                                           .then(response => response.json())
                                                                           .then(result => {
                                                                               renderCity(result.data);
                                                                           });
                                                                   function renderCity(data) {
                                                                       for (const x of data) {
                                                                           if(x.ProvinceID != '284') {
                                                                           var opt = document.createElement('option');
                                                                           opt.value = x.ProvinceName;
                                                                           opt.text = x.ProvinceName;
                                                                           opt.setAttribute('province_id', x.ProvinceID);
                                                                           citis.options.add(opt);
                                                                       }
                                                                       }
                                                                       citis.onchange = function () {
                                                                           resetShippingFee();
                                                                           var provinceID = this.options[this.selectedIndex].getAttribute('province_id');
                                                                           district.length = 1;
                                                                           ward.length = 1;
                                                                           if (this.options[this.selectedIndex].dataset.id != "") {
                                                                               fetch("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=" + provinceID, requestOptions)
                                                                                       .then(response => response.json())
                                                                                       .then(result => {
                                                                                           for (const k of result.data) {
                                                                                               if (k.DistrictID != '3715' && k.DistrictID != '3713' && k.DistrictID != '3451') {
                                                                                                   var opt = document.createElement('option');
//                                                                                opt.value = k.DistrictID;
                                                                                                   opt.value = k.DistrictName;
                                                                                                   opt.text = k.DistrictName;
                                                                                                   opt.setAttribute('district_id', k.DistrictID);
                                                                                                   district.options.add(opt);
                                                                                               }
                                                                                           }
                                                                                       });
                                                                           }
                                                                       };
                                                                       district.onchange = function () {
                                                                           districtID = this.options[this.selectedIndex].getAttribute('district_id');
                                                                           resetShippingFee();
                                                                           ward.length = 1;
//                const dataCity = data.filter((n) => n.Id === citis.options[citis.selectedIndex].dataset.id);

                                                                           if (this.options[this.selectedIndex].dataset.id != "") {
//                    const result = data.filter(n => n.Id === this.options[this.selectedIndex].dataset.id);
                                                                               fetch("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + districtID, requestOptions)
                                                                                       .then(response => response.json())
                                                                                       .then(result => {
//                                console.log(result.data);
                                                                                           for (const k of result.data) {
                                                                                               var opt = document.createElement('option');
//                                                                                opt.value = k.WardCode;
                                                                                               opt.value = k.WardName;
                                                                                               opt.text = k.WardName;
                                                                                               opt.setAttribute('wardcode', k.WardCode);
                                                                                               wards.options.add(opt);
                                                                                           }
                                                                                       });
                                                                           }
                                                                           ;
                                                                       };
                                                                       ward.onchange = function () {
                                                                           wardCode = this.options[this.selectedIndex].getAttribute('wardcode');
                                                                           calculateShippingFee();
                                                                       };
                                                                   }
                                                               }//end if empty sessionScope.addressList

                                                               //calculate shipping fee with book address
                                                               if (${not empty sessionScope.addressList}) {
                                                                   function chooseAddress(ward, district, province) {
                                                                       document.getElementById("button").disabled = true;
                                                                       var provinceID = "";
                                                                       fetch("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province", requestOptions)
                                                                               .then(response => response.json())
                                                                               .then(result => {
                                                                                   for (const k of result.data) {
                                                                                       if (k.ProvinceName.toLowerCase() === province.toLowerCase()) {
                                                                                           provinceID = k.ProvinceID;
                                                                                           break;
                                                                                       }
                                                                                   }
                                                                               });
                                                                       fetch("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=" + provinceID, requestOptions)
                                                                               .then(response => response.json())
                                                                               .then(result => {
                                                                                   for (const k of result.data) {
                                                                                       if (k.DistrictName.toLowerCase() === district.toLowerCase()) {
                                                                                           districtID = k.DistrictID;
                                                                                           fetch("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + districtID, requestOptions)
                                                                                                   .then(response => response.json())
                                                                                                   .then(result => {
                                                                                                       for (const k of result.data) {
                                                                                                           if (k.WardName.toLowerCase() === ward.toLowerCase()) {
                                                                                                               console.log(k.WardCode);
                                                                                                               wardCode = k.WardCode;
                                                                                                               calculateShippingFee();
                                                                                                               break;
                                                                                                           }
                                                                                                       }
                                                                                                   });
                                                                                           break;
                                                                                       }
                                                                                   }
                                                                               });
                                                                   }
                                                               }//end if not empty sessionScope.addressList


                                                               //format money and calculate shipping fee
                                                               var subTotal = ${subTotal}; // Replace with the actual total value in VND
                                                               var subtotalElement = document.getElementById("subtotalAmount");
                                                               subtotalElement.textContent = formatCurrency(subTotal);
                                                               subtotalElement.value = Math.round(subTotal);

                                                               var shippingFee = document.getElementById("shippingFee");
                                                               var shippingFeeValue = document.getElementById("shippingFeeValue");
                                                               shippingFee.value = 0;

                                                               var total = document.getElementById("total");
                                                               total.value = calculateTotal();

                                                               function formatCurrency(amount) {
                                                                   var formatter = new Intl.NumberFormat("vi-VN", {
                                                                       style: "currency",
                                                                       currency: "VND"
                                                                   });

                                                                   return formatter.format(amount);
                                                               }
                                                               //total money function
                                                               function calculateTotal() {
                                                                   total.value = subtotalElement.value + shippingFee.value;
                                                                   total.textContent = formatCurrency(total.value);
                                                               }


                                                               //reset shiping fee
                                                               function resetShippingFee() {
                                                                   shippingFee.textContent = "—";
                                                                   shippingFee.value = 0;
                                                                   shippingFeeValue.value = 0;
                                                                   console.log("resetShippingFeeValue: " + shippingFeeValue);
                                                                   calculateTotal();
                                                                   console.log("total after change" + total.value);
                                                               }

                                                               //function calculateShippingFee()
                                                               function calculateShippingFee() {
                                                                   var totalWeight = Math.floor(document.getElementById("totalWeight").value);
                                                                   var myHeaders = new Headers();
                                                                   myHeaders.append("Content-Type", "application/json");
                                                                   myHeaders.append("Token", "8971acfc-10b0-11ee-bb28-f6a6bf301a4e");
                                                                   myHeaders.append("ShopId", "124806");
                                                                   myHeaders.append("Content-Type", "text/plain");
                                                                   var raw = JSON.stringify({
                                                                       "service_id": 53320,
                                                                       "service_type_id": 2,
                                                                       "to_district_id": Math.floor(districtID),
                                                                       "to_ward_code": wardCode,
                                                                       "weight": totalWeight,
                                                                       "insurance_value": null,
                                                                       "cod_failed_amount": null,
                                                                       "coupon": null
                                                                   });
                                                                   console.log("request: " + raw);
                                                                   var requestOptions = {
                                                                       method: 'POST',
                                                                       headers: myHeaders,
                                                                       body: raw,
                                                                       redirect: 'follow'
                                                                   };
                                                                   fetch("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee", requestOptions)
                                                                           .then(response => response.json())
                                                                           .then(result => {
                                                                               if (result.code >= 400 && result.code < 600) {
                                                                                   alert(result.message);
                                                                               } else {
                                                                                   let data = result.data;
                                                                                   shippingFee.textContent = formatCurrency(data.total);
                                                                                   shippingFee.value = Math.round(data.total);
                                                                                   shippingFeeValue.value = shippingFee.value;
                                                                                   console.log(shippingFeeValue.value);
                                                                                   calculateTotal();
                                                                                   document.getElementById("button").disabled = false;
//                                                                               console.log(total.value);
//                                                                               console.log(total.textContent);
                                                                                   console.log(JSON.stringify(data));
                                                                               }
                                                                           })
                                                                           .catch(error => console.log('error', error));
                                                               }
                                                               
                                                               //function on off input email box
                                                                function switchCheck() {
                                                                    if (document.getElementById("flexSwitchCheckDefault").checked) {
                                                                        document.getElementById("email").style.display = 'block';
                                                                    } else {
                                                                        document.getElementById("email").style.display = 'none';
                                                                    }
                                                                }
                                                                if (document.getElementById("flexSwitchCheckDefault").checked) {
                                                                        document.getElementById("email").style.display = 'block';
                                                                    } else {
                                                                        document.getElementById("email").style.display = 'none';
                                                                    }
    </script>
</body>
</html>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>