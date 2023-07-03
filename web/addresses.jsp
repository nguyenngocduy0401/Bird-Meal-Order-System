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
<c:if test="${param.check ne 1}">
    <c:redirect url="ShowListAddressController" />
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
    <script type="text/javascript">
        function createNewAddress(txtFullName, ddlProvince, ddlDistrict, txtAddressDetails, txtPhoneNumber, ddlWard) {
            var fullName = txtFullName;
            var province = ddlProvince;
            var district = ddlDistrict;
            var addressDetails = txtAddressDetails;
            var phoneNumber = txtPhoneNumber;
            var ward = ddlWard;
            if (fullName && province && district && addressDetails && phoneNumber) {
                $.ajax({
                    type: "post",
                    url: "AddNewAddressController",
                    data: {
                        txtFullName: fullName,
                        ddlProvince: province,
                        ddlDistrict: district,
                        txtAddressDetails: addressDetails,
                        txtPhoneNumber: phoneNumber,
                        ddlWard: ward
                    },
                    success: function () {
                        location.reload();
                    }
                });
            } else {
                // Display an error message or perform any other desired action
                alert('Please fill in all required fields');
            }

        }
        function deleteAddress(addressId) {
            $.ajax({
                type: "post",
                url: "DeleteAddressController",

                data: {
                    txtAddressID: addressId

                },
                success: function () {
                    window.location.href = "addresses.jsp";
                }
            });


        }

    </script>
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
                    <a href="https://birdfoodswp.blogspot.com/" class="nav-item nav-link">Blog</a>
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

                    <div class="col-md-9" >
                        <form action="MainController">
                            <c:forEach var="address" items="${addressList}" >
                                <c:set var="count" value="${count = count + 1}"/>
                            </c:forEach>
                            <div class="bg-white"
                                 style="border: 1px solid rgb(224, 224, 224); padding: 28px 20px; box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%)">
                                <div class="fr-contents-card full">
                                    <c:if test="${not empty sessionScope.addressList}">
                                        <div class="fr-wrapper mb-l mt-m" style="width: auto;">
                                            <h3><span class="title">SHIPPING ADDRESS
                                                    (${count})</span></h3>
                                            <div class="mt-m">Up to 5 addresses can be stored.</div>
                                        </div>
                                    </c:if>

                                    <c:if test="${empty sessionScope.addressList}">
                                        <div class="fr-wrapper mt-m" style="width: auto;">
                                            <h3><span class="title">SHIPPING ADDRESS
                                                    (0)</span></h3>
                                            <div class="fr-text">There is no registered shipping address.</div>
                                        </div>
                                    </c:if>

                                    <c:if test="${not empty sessionScope.addressList}">
                                        <c:forEach var="address" items="${addressList}">
                                            <c:set var="fullName" value="${address.fullName}" />
                                            <c:set var="addressDetail" value="${address.addressDetail}" />
                                            <c:set var="phoneNumber" value="${address.phoneNumber}" />
                                            <c:set var="addressID" value="${address.addressID}" />
                                            <div class="row g-3 container border border-secondary rounded border-dark m-2" style="border-radius: 5px;">
                                                <div class="col-md-1">
                                                </div>
                                                <div class="col-md-9">
                                                    <div class="row">
                                                        <div class="col-md-7 py-1">${fullName}</div>
                                                        <div class="col-md-5 border-start py-1">${phoneNumber}</div>
                                                    </div>
                                                    <div class="">${addressDetail}</div>
                                                    <input class="form-control bg" type="hidden" name="addressID" value="${addressID}">
                                                </div>
                                                <div class="col-md-2">
                                                    <button type="submit" class="btn btn-link" name="btAction" value="editListAddress">Edit</button>
                                                    <button class="btn btn-link" style="color: red" type="button" name="action" value="delete" 
                                                            onclick="deleteAddress(${addressID})">Delete</button>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>


                                    <c:set var="count1" value="0" />
                                    <c:forEach var="address" items="${addressList}">
                                        <c:set var="count1" value="${count + 1}" />
                                    </c:forEach>
                                    <c:if test="${count1 <= 5 }">
                                        <a href="addNewAddress.jsp"><button type="button" class="btn btn-primary mt-2" name="btAction" value="addNewAddress">
                                                Register a new address
                                            </button></a>
                                        </c:if>


                                    <!-- Button trigger modal -->
                                    <!--                                    <button type="fr-wrapper button" class="btn btn-primary mt-2" data-bs-toggle="modal" data-bs-target="#createAddressModal">
                                                                            Register a new address
                                                                        </button>-->


                                </div>
                        </form>
                        <!-- Modal -->
                        <!--                        <form action="createNewAddressServlet"  id="createAddress-form">
                                                    <div  class="modal fade" id="createAddressModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
                                                        <div class="modal-dialog modal-dialog-centered">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">Create new address</h5>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="mb-3">
                                                                        <label class="small mb-1" for="inputUsername">Full name</label>
                        
                                                                        <input class="form-control bg"  required="" id="inputUsername" type="text" name="txtFullName"
                                                                               placeholder="Enter your Fullname" >
                        
                        
                                                                    </div>
                                                                    <div class="form-group mb-3">
                                                                        <label class="small mb-1">Address</label>
                                                                        <div>
                                                                            <select  name="ddlProvince" required="" class="form-control bg-white form-select mt-1 " id="city" aria-label=".form-select-sm">
                                                                                <option  selected>Select your province</option>           
                                                                            </select>
                                                                            <select name="ddlDistrict" required="" class="form-control bg-white form-select mt-3 " id="district" aria-label=".form-select-sm">
                                                                                <option  selected>Select your district</option> 
                                                                            </select>
                        
                                                                            <select name="ddlWard" required="" class="form-control bg-white form-select mt-3" id="ward" aria-label=".form-select-sm">
                                                                                <option  selected>Select your ward</option>
                                                                            </select>
                                                                        </div>
                                                                        <div>
                                                                            <input type="text" class="form-control bg  mt-3 mb-2" placeholder="Address details"
                                                                                   name="txtAddressDetails" required="" id="addressDetails"  >
                                                                        </div>
                                                                    </div>
                        
                                                                    <div class="form-group">
                                                                        <label class="small mb-1" for="phoneNumber">Phone number</label>
                                                                        <input class="form-control" id="phoneNumber" type="text" 
                                                                               placeholder="Enter your phone number"  name="txtPhoneNumber" 
                                                                               pattern="[0-9]{10}"
                                                                               title="Please enter a 10-digit phone number"
                                                                               required="">
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn" data-bs-dismiss="modal"
                                                                            style="color: black; background-color: lightgray; border-color: lightgray">Close</button>
                                                                    <button type="button" onclick="createNewAddress(
                                            document.getElementById('inputUsername').value,
                                            document.getElementById('city').value,
                                            document.getElementById('district').value,
                                            document.getElementById('addressDetails').value,
                                            document.getElementById('phoneNumber').value,
                                            document.getElementById('ward').value
                                            )" class="btn btn-primary">Create</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>-->
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
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>