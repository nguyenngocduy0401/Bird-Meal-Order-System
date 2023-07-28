
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .card{
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
        }
        .p-4{
            box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
        }
        .container{
            margin-top: 20px;
        }

        /*reset css*/
        /****** Style Star Rating Widget *****/
        #rating{border:none;float:left;}
        #rating>input{display:none;}/*ẩn input radio - vì chúng ta đã có label là GUI*/
        #rating>label:before{margin:5px;font-size:1.25em;font-family:FontAwesome;display:inline-block;content:"\f005";}/*1 ngôi sao*/
        #rating>label{color:#ddd;float:right;}/*float:right để lật ngược các ngôi sao lại đúng theo thứ tự trong thực tế*/
        /*thêm màu cho sao đã chọn và các ngôi sao phía trước*/
        #rating>input:checked~label,
        #rating:not(:checked)>label:hover, 
        #rating:not(:checked)>label:hover~label{color:#FFD700;}
        /* Hover vào các sao phía trước ngôi sao đã chọn*/
        #rating>input:checked+label:hover,
        #rating>input:checked~label:hover,
        #rating>label:hover~input:checked~label,
        #rating>input:checked~label:hover~label{color:#FFED85;}
    </style>
    <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>

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

        <!-- Libraries sweetalert2--> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"></script>
    </head>

    <body>
        <%@include file="header.jsp" %>
        <c:set var="product" value="${requestScope.PRODUCT}" />
        <c:set var="orderID" value="${requestScope.ORDERID}" />
        <c:set var="fb" value="${requestScope.FEEDBACK}" />
        <c:if test="${empty fb}">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Feedback</h6>
                    <h1 class="display-5 text-uppercase mb-0">Give feedback</h1>
                </div>
                <div class="row g-0 bg-light overflow-hidden">
                    <div id="rating">
                        <input type="radio" id="star5" name="rating" value="5" />
                        <label class = "full " for="star5" title="Awesome - 5 stars"></label>

                        <input type="radio" id="star4" name="rating" value="4" />
                        <label class = "full" for="star4" title="Pretty good - 4 stars"></label>

                        <input type="radio" id="star3" name="rating" value="3" />
                        <label class = "full" for="star3" title="Meh - 3 stars"></label>

                        <input type="radio" id="star2" name="rating" value="2" />
                        <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>

                        <input type="radio" id="star1" name="rating" value="1" />
                        <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                    </div>
                    <div class="h-50 d-flex flex-column justify-content-center p-4">
                        <div class="row border product-inrow">
                            <div class="col-md-2 img-fluid">
                                <img src=${product.imgPath} alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                            </div>
                            <div class="col-md-8 text-left mt-sm-2">
                                <h4>${product.productName}</h4>
                                <p class="font-weight-light">${product.productDetail}</p>
                            </div>
                            <div class="col-md-2 text-right mt-sm-2">
                                <p > Price: ${product.price} VND</p>
                                <p class="font-weight-light text-right">x ${product.quantity}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div>
                    <form>
                        <div class="row g-3">
                            <div class="col-12">
                                <textarea name="details" id="details" class="card form-control bg-light border-0 px-4 py-3" rows="8" placeholder="Details"></textarea>
                            </div>
                            <div class="col-12">
                                <button class="btn btn-primary w-100 py-3" type="submit" onclick="giveFeedback()">Send Feedback</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty fb}">
            <div class="container">
                <div class="border-start border-5 border-primary ps-5 mb-5" style="max-width: 600px;">
                    <h6 class="text-primary text-uppercase">Feedback</h6>
                    <h1 class="display-5 text-uppercase mb-0">You have given product feedback</h1>
                </div>
                <form action="MainController">
                    <button class="btn btn-primary w-100 py-3" type="submit">Back to home</button>
                    <input type="hidden" name="btAction" value="Purchase" />
                </form>
            </div>
        </c:if>

    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
                                    function giveFeedback() {

                                        let elements = document.getElementsByName('rating');
                                        let len = elements.length;
                                        let checkValue = '';

                                        for (let i = 0; i < len; i++) {
                                            if (elements.item(i).checked) {
                                                checkValue = elements.item(i).value;
                                            }
                                        }
                                        $.ajax({
                                            type: "get",
                                            url: "GiveFeedback",
                                            data: {
                                                details: $('#details').val(),
                                                orderID: '${orderID}',
                                                productID: '${product.productID}',
                                                rating: checkValue
                                            },
                                            success: function () {
                                                alert("feedback successful");
                                                window.location.replace("MainController?btAction=Purchase");
                                            }
                                        });
                                    }

    </script>
</html>
