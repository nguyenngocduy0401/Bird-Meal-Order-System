<%-- 
    Document   : shopping
    Created on : May 25, 2023, 10:23:15 AM
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
        <div>
            <h1>Welcome to BIRD FOOD SHOP</h1>
        </div>


        <div>
            <c:set var="result" value="${requestScope.FOOD_LIST}"/>
            <c:if test="${not empty result}">
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
                            <th>Add to Cart</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
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
                                    <c:set var="quantity" value="${dto.quantity}" />
                                    <c:set var="cart" value="${sessionScope.CART}" />
                                    <c:set var="remainQuantity" value="${quantity - cart.getQuantityBySKU(dto.productID)}" /> 
                                    ${remainQuantity}
                                </td>
                                
                                <td style="text-align: center">
                                    <c:if test="${dto.quantity eq 0}">
                                        <font color="red">This food is sold out</font>
                                    </c:if>

                                    <c:if test="${dto.quantity ne 0}">
                                        <form action="AddItemToCartServlet">
                                            <input type="submit" value="Add" class="btn"
                                                   <c:if test="${remainQuantity eq 0}">
                                                       style="visibility: hidden"
                                                   </c:if>
                                                   />
                                            <input type="hidden" name="pk" value="${dto.productID}" />
                                        </form>
                                    </c:if>

                                </td>


                            </tr>
                        </c:forEach>
                    </tbody>
                </table>                
            </c:if>
            <form action="viewcart.jsp">
                <input type="submit" value="View Your Cart" class="btn"/>
            </form>

        </div>

        <div class="row">
            <c:if test="${empty result}">
                <h2>No FOOD is in store!</h2>
            </c:if>
        </div>
    </div>
</body>
</html>
