/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.dto.ProductDTO;
import sample.utils.DBUtils;



/**
 *
 * @author DucAnh
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void showBookList()
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  select ProductID, ProductName, price, quantity, CategoryID, \n"
                        + "  ProductDetail, size, AgeRecommendation, Date, Status, Country, imgPath \n"
                        + "  from Product";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    String Date = rs.getString("Date");
                    int status = rs.getInt("Status");
                    String Country = rs.getString("Country");
                    String ImgPath = rs.getString("imgPath");
                    ProductDTO dto = new ProductDTO(productID, productName, price, 
                            quantity, categoryID, productDetail, size, ageRecommendation, 
                            Date, status, Country, ImgPath);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ProductDTO getProductByProductID(int productID)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select * from Product where ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    //String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    int categoryID = rs.getInt("categoryID");
                    String productDetail = rs.getString("productDetail");
                    String size = rs.getString("size");
                    int ageRecommendation = rs.getInt("ageRecommendation");
                    String Date = rs.getString("date");
                    int status = rs.getInt("status");
                    String Country = rs.getString("country");
                    String ImgPath = rs.getString("imgPath");
                    ProductDTO result = new ProductDTO(productID, productName, price,
                            quantity, categoryID, productDetail, size, ageRecommendation, 
                            Date, status, Country, ImgPath);
                    return result;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
    
//    public static void main(String[] args) throws SQLException, NamingException, ClassNotFoundException {
//    String productID = "B02"; // ID sản phẩm muốn tìm kiếm
//    
//    try {
//        ProductDAO dao = new ProductDAO();
//        ProductDTO result = dao.getProductByProductID(productID);
//        if (result != null) {
//            System.out.println("Product found:");
//            System.out.println("ID: " + result.getProductID());
//            System.out.println("Name: " + result.getProductName());
//            System.out.println("Price: " + result.getPrice());
//            System.out.println("Quantity: " + result.getQuantity());
//            System.out.println("Category: " + result.getCategoryID());
//            System.out.println("Detail: " + result.getProductDetail());
//            System.out.println("Size: " + result.getSize());
//            System.out.println("Age Recommendation: " + result.getAgeRecommendation());
//            System.out.println("Country: " + result.getCountry());
//        } else {
//            System.out.println("Product not found.");
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    } catch (NamingException e) {
//        e.printStackTrace();
//    } catch (ClassNotFoundException e) {
//        e.printStackTrace();
//    }
//}
}
