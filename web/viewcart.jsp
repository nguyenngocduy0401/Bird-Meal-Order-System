<%-- 
    Document   : viewcart
    Created on : May 26, 2023, 3:46:31 PM
    Author     : DucAnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container grid">
            <c:set var="cart" value="${sessionScope.CART}" />

            <!-- NOT EMPTY CART -->
            <c:if test="${not empty cart}">
                <div class="row">
                    <h1>Your Cart</h1>
                </div>


                <c:set var="items" value="${cart.items}" />
                <!-- NOT EMPTY ITEMS -->
                <c:if test="${not empty items}">
                    <div class="result row">
                        <form action="CartServlet">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>ProductID</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Detail</th>
                                        <th>Size</th>
                                        <th>Age Recommendation</th>
                                        <th>Date</th>
                                        <th>country</th>
                                        <th>Quantity</th>
                                        <th>Remove</th>
                                        <th>Check out</th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <c:forEach var="item" items="${items}" varStatus="counter">
                                        <c:set var="dto" value="${item.key}" />
                                        <c:set var="quantity" value="${item.value}" />
                                        <tr>
                                            <td style="text-align: center">
                                                ${counter.count}
                                            </td>
                                            <td style="text-align: center">
                                                ${dto.productID}
                                            </td>
                                            <td>
                                                ${dto.productName}
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${dto.price}" maxFractionDigits="0"/>
                                            </td>


                                            <td>
                                                ${dto.productDetail}
                                            </td>
                                            
                                            <td style="text-align: center">
                                                ${dto.size}
                                            </td>
                                            <td style="text-align: center">
                                                ${dto.ageRecommendation}
                                            </td>
                                            <td>
                                                ${dto.date}
                                            </td>

                                            <td>
                                                ${dto.country}
                                            </td>
                                            <td style="text-align: center">
                                                ${quantity}
                                            </td>
                                            
                                            <td style="text-align: center">
                                                <input type="checkbox" name="chkItem" 
                                                       value="${dto.productID}" />
                                            </td>
                                            <td style="text-align: center">
                                                <input type="checkbox" name="chkCheckOut" 
                                                       value="${dto.productID}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="10">
                                            <a href="ListProductServlet">
                                                Add More Books to Your Cart
                                            </a>
                                        </td>
                                        <td>
                                            <input type="submit" class="btn"
                                                   value="Remove Selected Foods" 
                                                   name="btAction" />
                                        </td>
                                        <td>
                                            <input type="submit" class="btn"
                                                   value="Check Out Selected Foods"
                                                   name="btAction" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </c:if>

                <!-- EMPTY ITEMS -->
                <c:if test="${empty items}">
                    <div class="no-item row">
                        <h2>
                            No item exited in your cart!
                        </h2>
                        <a href="ListProductServlet">Add More Books to Your Cart</a>
                    </div>
                </c:if>
            </c:if>

            <!-- EMPTY CART -->
            <c:if test="${empty cart}">
                <div class="no-cart row">
                    <h1>No cart is existed!</h1>
                    <a href="ListProductServlet">Click Here To Go Shopping</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
