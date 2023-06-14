<%-- 
    Document   : newProduct
    Created on : Jun 8, 2023, 1:05:18 PM
    Author     : DucAnh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Product Information</h2>
        <a href="HomeController">Home</a>
        <table>
            <tr>
                <th>Product ID</th>
                <th>productName</th>
                <th>price</th>
                <th>quantity</th>
                <th>categoryID</th>
                <th>productDetail</th>
                <th>ageRecommendation</th>
                <th>date</th>
                <th>status</th>
                <th>size ID</th>
                <th>country</th>
                <th>Image</th>
            </tr>
            <tr>
                <td>${createdProduct.productID}</td>
                <td>${createdProduct.productName}</td>
                <td>${createdProduct.price}</td>
                <td>${createdProduct.quantity}</td>
                <td>${createdProduct.categoryID}</td>
                <td>${createdProduct.productDetail}</td>
                <td>${createdProduct.size}</td>
                <td>${createdProduct.ageRecommendation}</td>
                <td>${createdProduct.date}</td>
                <td>${createdProduct.status}</td>
                <td>${createdProduct.country}</td>
                <td><img src="${createdProduct.imgPath}" alt="Product Image"></td>
            </tr>
        </table>  
    </body>
</html>
