<%-- 
    Document   : updateProduct
    Created on : Jun 9, 2023, 12:52:09 PM
    Author     : DucAnh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sample.dao.ProductDAO"%>
<%@page import="sample.dto.ProductDTO"%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Product Update</h2>
        <c:set var="category" value="${categoryName}"/>
        <c:set var="INFOR" value="${INFOR}"/>
        <form action="UpdateProductServlet?action=${ACTION}" method="post" enctype="multipart/form-data">
            <table border="1">
                <tr>
                    <td>ProductID</td>
                    <td>
                        <input type="text" name="txtProductId" value="${INFOR.productID}" id="txtProductId"  readonly=""/>
                    </td>
                </tr>
                <tr>
                    <td>productName</td>
                    <td>
                        <input type="text" name="txtProductName" value="${INFOR.productName}" id="txtProductName" required/>
                    </td>
                </tr>
                <tr>
                    <td>price</td>
                    <td>
                        <input type="text" name="txtPrice" value="${INFOR.price}" id="txtPrice" required/>
                    </td>
                </tr>
                <tr>
                    <td>quantity</td>
                    <td>
                        <input type="number" name="txtQuantity" value="${INFOR.quantity}" id="txtQuantity" required/>
                    </td>
                </tr>
                <tr>
                    <td>categoryID</td>
                    <td>
                        <select name="txtCategory">
                            <c:forEach var="cate" items="${category}">
                                <option value="${cate.categoryID}" ${cate.categoryID == selectedCategoryId ? 'selected' : ''}>${cate.categoryName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>productDetail</td>
                    <td>
                        <input type="text" name="txtProductDetail" value="${INFOR.productDetail}" id="txtProductDetail" required/>
                    </td>
                </tr>
                <tr>
                    <td>size</td>
                    <td>
                        <input type="text" name="txtSize" value="${INFOR.size}" id="txtSize" required/>
                    </td>
                </tr>
                <tr>
                    <td>ageRecommendation</td>
                    <td>
                        <input type="number" name="txtAgeRecommendation" value="${INFOR.ageRecommendation}" id="txtAgeRecommendation" required/>
                    </td>
                </tr>
                <tr>
                    <td>date</td>
                    <td>
                        <input type="date" name="txtDate" value="${INFOR.date}" id="txtDate" required/>
                    </td>
                </tr>
                <tr>
                    <td>status</td>
                    <td>
                        <select name="txtStatus">
                            <option value="1" ${INFOR.status == 1 ? 'selected' : ''}>ON</option>
                            <option value="0" ${INFOR.status == 0 ? 'selected' : ''}>OFF</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>country</td>
                    <td>
                        <input type="text" name="txtCountry" value="${INFOR.country}" id="txtCountry" required/>
                    </td>
                </tr>
                <tr>
                    <td>imgPath</td>
                    <td>
                        <img <img src="${INFOR.imgPath}" width="200px"/> </br></br></br>
                        <input type="file" name="txtPicture" id="txtPicture" required/>
                    </td>
                </tr>
                <tr>

                    <td colspan="2">
                        <input type="submit" value="save" />
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
