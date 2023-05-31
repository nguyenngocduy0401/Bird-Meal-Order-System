<%-- 
    Document   : productDetail
    Created on : Jun 1, 2023, 3:21:49 AM
    Author     : Duy
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
                <tr><td rowspan="8"><img src="${plantObj.imgpath}"width="300" height="300"> </td></tr>
           
            <tr><td>product:${productDTO.productName}</td></tr>
            <tr><td>price:${productDTO.price}</td></tr>
            <tr><td>description:${productDTO.quantity}</td></tr>
            <tr><td>categoryName:${categoryDTO.categoryName}</td></tr>
            <tr><td>listBird:<c:forEach items="${listBird}" var="bird">
    <p>${bird.birdName}</p>
</c:forEach></td></tr>
            
        </table>
    </body>
</html>
