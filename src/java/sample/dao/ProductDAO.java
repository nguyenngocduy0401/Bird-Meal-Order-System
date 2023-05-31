/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

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
 * @author Admin
 */
public class ProductDAO {

    public List<ProductDTO> loadProducts()
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ProductID, ProductName, Price"
                        + ", Quantity, CategoryID, ProductDetail"
                        + ", Size, AgeRecommendation, Date, Status, Country "
                        + "FROM Product ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    String categoryID = rs.getString("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    String date = rs.getString("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country);
                    listProduct.add(dto);
                }//end while rs not null
            }//end if con is not null
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
        return listProduct;
    }

    public List<ProductDTO> searchListProduct(String searchValue, int index, int onPageProduct)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ProductID, ProductName, Price"
                        + ", Quantity, CategoryID, ProductDetail"
                        + ", Size, AgeRecommendation, Date, Status, Country "
                        + "FROM Product "
                        + "WHERE ProductName like ? "
                        + "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, (index - 1) * 6);
                stm.setInt(3, onPageProduct);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    String categoryID = rs.getString("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    String date = rs.getString("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country);
                    listProduct.add(dto);
                }//end while rs not null
            }//end if con is not null
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
        return listProduct;
    }

    public int getAmountSearchProduct(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT (*) "
                        + "FROM Product "
                        + "WHERE ProductName like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public int getAmountProduct() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT (*) "
                        + "FROM Product ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public List<ProductDTO> pagingProduct(int index, int onPageProduct)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ProductID, ProductName, Price"
                        + ", Quantity, CategoryID, ProductDetail"
                        + ", Size, AgeRecommendation, Date, Status, Country "
                        + "FROM Product "
                        + "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, (index - 1) * onPageProduct);
                stm.setInt(2, onPageProduct);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    String categoryID = rs.getString("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    String date = rs.getString("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country);
                    listProduct.add(dto);
                }//end while rs not null
            }//end if con is not null
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
        return listProduct;
    }

    public static void main(String[] args) throws SQLException, NamingException, ClassNotFoundException {
        ProductDAO dao = new ProductDAO();
        List<ProductDTO> listProduct = dao.searchListProduct("a", 1, 6);
        int ep = dao.getAmountSearchProduct("a");
        for (ProductDTO o : listProduct) {
            System.out.println(o);
        }
        System.out.println(ep);
    }

    public static ProductDTO getProductByID(int getProductID) {
        ProductDTO product = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String s = "SELECT ProductID, ProductName, Price, Quantity, CategoryID, ProductDetail, Size, AgeRecommendation, Date , [Status], Country\n"
                        + "FROM Product\n"
                        + "WHERE ProductID = ?;";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setInt(1, getProductID);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        int productID = kq.getInt("ProductID");
                        String productName = kq.getString("ProductName");
                        double price = kq.getDouble("Price");
                        int quantity = kq.getInt("Quantity");
                        String categoryID = kq.getString("CategoryID");
                        String productDetail = kq.getString("ProductDetail");
                        String size = kq.getString("Size");
                        int ageRecommendation = kq.getInt("AgeRecommendation");
                        String date = kq.getString("Date");
                        int status = kq.getInt("Status");
                        String country = kq.getString("Country");
                        product = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country);

                    }
                    cn.close();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }

}
