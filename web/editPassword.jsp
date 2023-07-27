<%-- 
    Document   : editPassword
    Created on : Jun 1, 2023, 11:13:54 AM
    Author     : haong
--%>
<style></style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        .content {
            margin-top: 60px;
        }
        .card {
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
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

    <head>
        <meta charset="utf-8">
        <title>Bird Meal Order System</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

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

        <!--sweetalert2--> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>

    <body>
        <!-- Topbar Start -->
        <%@include file="header.jsp" %>
        <!-- Navbar End -->

        <c:set var="user" value="${sessionScope.user}"/>
        <c:if test="${not empty user}">


            <div class="container content">

                <div class="row ">
                    <div class="title col-md-3">
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
                                <div class=""><a class="" href="addresses.jsp">
                                        <div class="">Address book</div>
                                    </a></div>
                            </div>
                            <div>
                                <div class=""><a class="" href="editPassword.jsp">
                                        <div class="" style="color: #7AB730">Change my password</div>
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
                        <!-- Change password card-->
                        <div class="card mb-4">
                            <div class="card-header">Change Password</div>
                            <div class="card-body">
                                <form action = "EditPasswordServlet" method="POST">
                                    <c:set var="errors" value="${requestScope.UPDATE_PASSWORD_ERROR}"/>
                                    <!-- Form Group (current password)-->
                                    <div class="mb-3">
                                        <c:if test="${not empty errors.passwordOldWrong}">
                                            <label class="small mb-1" for="currentPassword">Current Password</label>
                                            <input class="form-control is-invalid" name="txtCurrentPassword" type="password"
                                                   placeholder="Enter current password">
                                            <font color ="red">
                                            ${errors.passwordOldWrong}
                                            </font>
                                        </c:if>
                                        <c:if test="${empty errors.passwordOldWrong}">
                                            <label class="small mb-1" for="currentPassword">Current Password</label>
                                            <input class="form-control" name="txtCurrentPassword" type="password"
                                                   placeholder="Enter current password" value="${param.txtCurrentPassword}">

                                        </c:if>
                                    </div>
                                    <!-- Form Group (new password)-->
                                    <div class="mb-3">
                                        <c:if test="${not empty errors.passwordLengthError}">
                                            <label class="small mb-1" for="newPassword">New Password</label>
                                            <input class="form-control is-invalid" name="txtNewPassword" type="password"
                                                   placeholder="Enter new password">
                                            <font color ="red">
                                            ${errors.passwordLengthError}
                                            </font>
                                        </c:if>
                                        <c:if test="${empty errors.passwordLengthError && empty errors.passwordOldWrong || not empty errors.passwordOldWrong}">
                                            <label class="small mb-1" for="newPassword">New Password</label>
                                            <input class="form-control" name="txtNewPassword" type="password"
                                                   placeholder="Enter new password" value="${param.txtNewPassword}">
                                        </c:if>
                                    </div>
                                    <!-- Form Group (confirm password)-->
                                    <div class="mb-3">
                                        <c:if test="${not empty errors.confirmNotMatched}">
                                            <label class="small mb-1" for="confirmPassword">Confirm Password</label>
                                            <input class="form-control is-invalid" name="txtConfirmPassword" type="password"
                                                   placeholder="Confirm new password">
                                            <font color ="red">
                                            ${errors.confirmNotMatched}
                                            </font>
                                        </c:if>
                                        <c:if test="${empty errors.confirmNotMatched}">
                                            <label class="small mb-1" for="confirmPassword">Confirm Password</label>
                                            <input class="form-control" name="txtConfirmPassword" type="password"
                                                   placeholder="Confirm new password" value="${param.txtConfirmPassword}">
                                        </c:if>
                                    </div>
                                    <div>
                                        <!--
                                        <c:if test="${not empty requestScope.UPDATE_PASSWORD_SUCCESS}">
                                            <font color ="Green">
                                            Edit password successful.
                                            </font>
                                        </c:if>
                                        
                                        <c:if test="${requestScope.UPDATE_PASSWORD_SUCCESS == 'false'}">
                                            <font color ="Red">
                                            Edit password failed. Please try again.
                                            </font>
                                        </c:if>
                                        -->
                                    </div>

                                    <button class="btn btn-primary" type="submit">Save</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <script>
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
                //alert sucsess
                if (${not empty requestScope.UPDATE_PASSWORD_SUCCESS}) {
                    swal.fire({
                        icon: 'success',
                        title: "Success!",
                        text: "Your password is changed",
                        confirmButtonText: 'Click to login again'
                    }).then(function () {
                        window.location = "LogoutController";
                    });
                }
            </script>


            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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