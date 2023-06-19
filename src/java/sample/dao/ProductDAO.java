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
                String sql = "SELECT [ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[Price]\n"
                        + "      ,[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,[ProductDetail]\n"
                        + "      ,[Size]\n"
                        + "      ,[AgeRecommendation]\n"
                        + "      ,[Date]\n"
                        + "      ,[Status]\n"
                        + "      ,[Country]\n"
                        + "      ,[imgPath] "
                        + "FROM Product ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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
                String sql = "SELECT [Product].[ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[Price]\n"
                        + "      ,[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,[ProductDetail]\n"
                        + "      ,[Size]\n"
                        + "      ,[AgeRecommendation]\n"
                        + "      ,[Date]\n"
                        + "      ,[Status]\n"
                        + "      ,[Country]\n"
                        + "      ,[imgPath]\n"
                        + "	  ,[Bird].BirdName\n"
                        + "  FROM [Product] INNER JOIN [CategoriesBird]\n"
                        + "  ON [Product].ProductID = [CategoriesBird].ProductID \n"
                        + "  INNER JOIN [Bird] ON [CategoriesBird].BirdID = [Bird].BirdID "
                        + "WHERE ProductName like ? ";
                String paging = "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                sql = sql + paging;
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, (index - 1) * onPageProduct);
                stm.setInt(3, onPageProduct);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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

    public List<ProductDTO> searchListProductUser(String searchValue, int index, int onPageProduct, int categoryFilter, String sizeFilter, double priceMinFilter, double priceMaxFilter, String birdFilter)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        String and = "AND ";
        String sql = "";
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (!birdFilter.trim().equals("")) {
                    sql = "SELECT [Product].[ProductID]\n"
                            + "      ,[ProductName]\n"
                            + "      ,[Price]\n"
                            + "      ,[Quantity]\n"
                            + "      ,[CategoryID]\n"
                            + "      ,[ProductDetail]\n"
                            + "      ,[Size]\n"
                            + "      ,[AgeRecommendation]\n"
                            + "      ,[Date]\n"
                            + "      ,[Status]\n"
                            + "      ,[Country]\n"
                            + "      ,[imgPath]\n"
                            + "	  ,[Bird].BirdName\n"
                            + "  FROM [Product] INNER JOIN [CategoriesBird]\n"
                            + "  ON [Product].ProductID = [CategoriesBird].ProductID \n"
                            + "  INNER JOIN [Bird] ON [CategoriesBird].BirdID = [Bird].BirdID "
                            + "WHERE ProductName like ? ";
                    sql = sql + and + "BirdName like '" + birdFilter + "'";
                } else {
                    sql = "SELECT [Product].[ProductID]\n"
                            + "      ,[ProductName]\n"
                            + "      ,[Price]\n"
                            + "      ,[Quantity]\n"
                            + "      ,[CategoryID]\n"
                            + "      ,[ProductDetail]\n"
                            + "      ,[Size]\n"
                            + "      ,[AgeRecommendation]\n"
                            + "      ,[Date]\n"
                            + "      ,[Status]\n"
                            + "      ,[Country]\n"
                            + "      ,[imgPath]\n"
                            + "  FROM [Product] "
                            + "WHERE ProductName like ? AND Status = 1 ";
                }

                if (categoryFilter != -1) {
                    sql = sql + and + "[CategoryID] = " + categoryFilter + " ";
                }
                if (!sizeFilter.trim().equals("")) {
                    sql = sql + and + "Size like '" + sizeFilter + "'";
                }
                if (priceMinFilter <= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price < " + priceMaxFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter <= 0) {
                    sql = sql + and + "Price > " + priceMinFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price BETWEEN " + priceMinFilter + " AND " + priceMaxFilter + " ";
                }

                String paging = "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                sql = sql + paging;
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, (index - 1) * onPageProduct);
                stm.setInt(3, onPageProduct);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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

    public int getAmountSearchProductUser(String searchValue, int categoryFilter, String sizeFilter, double priceMinFilter, double priceMaxFilter, String birdFilter) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String and = "AND ";
        String sql = "";

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (!birdFilter.trim().equals("")) {
                    sql = "SELECT COUNT (*) "
                            + "FROM [Product] INNER JOIN [CategoriesBird]\n"
                            + "  ON [Product].ProductID = [CategoriesBird].ProductID \n"
                            + "  INNER JOIN [Bird] ON [CategoriesBird].BirdID = [Bird].BirdID "
                            + "WHERE ProductName like ? AND Status = 1 ";
                    sql = sql + and + "BirdName like '" + birdFilter + "'";
                } else {
                    sql = "SELECT COUNT (*) "
                            + "FROM [Product] "
                            + "WHERE ProductName like ? AND Status = 1 ";
                }
                if (categoryFilter != -1) {
                    sql = sql + and + "[CategoryID] = " + categoryFilter + " ";
                }
                if (!sizeFilter.trim().equals("")) {
                    sql = sql + and + "Size like '" + sizeFilter + "'";
                }
                if (priceMinFilter <= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price < " + priceMaxFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter <= 0) {
                    sql = sql + and + "Price > " + priceMinFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price BETWEEN " + priceMinFilter + " AND " + priceMaxFilter + " ";
                }
//                if (!birdFilter.trim().equals("")) {
//                    sql = sql + and + "BirdName like '" + birdFilter + "'";
//                }
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

    public int getAmountProductUser(int categoryFilter, String sizeFilter, double priceMinFilter, double priceMaxFilter, String birdFilter) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String and = "AND ";
        String sql = "";

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (!birdFilter.trim().equals("")) {
                    sql = "SELECT COUNT (*) "
                            + "FROM [Product] INNER JOIN [CategoriesBird]\n"
                            + "  ON [Product].ProductID = [CategoriesBird].ProductID \n"
                            + "  INNER JOIN [Bird] ON [CategoriesBird].BirdID = [Bird].BirdID "
                            + "WHERE Status = 1 ";
                    sql = sql + and + "BirdName like '" + birdFilter + "'";
                } else {
                    sql = "SELECT COUNT (*) "
                            + "FROM [Product] "
                            + "WHERE Status = 1 ";
                }
                if (categoryFilter != -1) {
                    sql = sql + and + "[CategoryID] = " + categoryFilter + " ";
                }
                if (!sizeFilter.trim().equals("")) {
                    sql = sql + and + "Size like '" + sizeFilter + "'";
                }
                if (priceMinFilter <= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price < " + priceMaxFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter <= 0) {
                    sql = sql + and + "Price > " + priceMinFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price BETWEEN " + priceMinFilter + " AND " + priceMaxFilter + " ";
                }
//                if (!birdFilter.trim().equals("")) {
//                    sql = sql + and + "BirdName like '" + birdFilter + "'";
//                }
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

    public List<ProductDTO> pagingProductUser(int index, int onPageProduct, int categoryFilter, String sizeFilter, double priceMinFilter, double priceMaxFilter, String birdFilter)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        String and = "AND ";
        String sql;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (!birdFilter.trim().equals("")) {
                    sql = "SELECT [Product].[ProductID]\n"
                            + "      ,[ProductName]\n"
                            + "      ,[Price]\n"
                            + "      ,[Quantity]\n"
                            + "      ,[CategoryID]\n"
                            + "      ,[ProductDetail]\n"
                            + "      ,[Size]\n"
                            + "      ,[AgeRecommendation]\n"
                            + "      ,[Date]\n"
                            + "      ,[Status]\n"
                            + "      ,[Country]\n"
                            + "      ,[imgPath]\n"
                            + "	  ,[Bird].BirdName\n"
                            + "  FROM [Product] INNER JOIN [CategoriesBird]\n"
                            + "  ON [Product].ProductID = [CategoriesBird].ProductID \n"
                            + "  INNER JOIN [Bird] ON [CategoriesBird].BirdID = [Bird].BirdID "
                            + "WHERE Status = 1 ";
                    sql = sql + and + "BirdName like '" + birdFilter + "'";
                } else {
                    sql = "SELECT [Product].[ProductID]\n"
                            + "      ,[ProductName]\n"
                            + "      ,[Price]\n"
                            + "      ,[Quantity]\n"
                            + "      ,[CategoryID]\n"
                            + "      ,[ProductDetail]\n"
                            + "      ,[Size]\n"
                            + "      ,[AgeRecommendation]\n"
                            + "      ,[Date]\n"
                            + "      ,[Status]\n"
                            + "      ,[Country]\n"
                            + "      ,[imgPath]\n"
                            + "  FROM [Product] "
                            + "WHERE Status = 1 ";
                }
                if (categoryFilter != -1) {
                    sql = sql + and + "[CategoryID] = " + categoryFilter + " ";
                }
                if (!sizeFilter.trim().equals("")) {
                    sql = sql + and + "Size like '" + sizeFilter + "'";
                }
                if (priceMinFilter <= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price < " + priceMaxFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter <= 0) {
                    sql = sql + and + "Price > " + priceMinFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price BETWEEN " + priceMinFilter + " AND " + priceMaxFilter + " ";
                }
//                if (!birdFilter.trim().equals("")) {
//                    sql = sql + and + "[BirdName] like '" + birdFilter + "'";
//                }
                String paging = "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                sql = sql + paging;
                stm = con.prepareStatement(sql);
                stm.setInt(1, (index - 1) * onPageProduct);
                stm.setInt(2, onPageProduct);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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

    public List<ProductDTO> pagingProduct(int index, int onPageProduct)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[Price]\n"
                        + "      ,[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,[ProductDetail]\n"
                        + "      ,[Size]\n"
                        + "      ,[AgeRecommendation]\n"
                        + "      ,[Date]\n"
                        + "      ,[Status]\n"
                        + "      ,[Country]\n"
                        + "      ,[imgPath] "
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
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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

    public List<ProductDTO> pagingProductFilterUser(int index, int onPageProduct, int categoryFilter, String sizeFilter, double priceMinFilter, double priceMaxFilter, String birdFilter)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        String and = "AND ";
        String sql = "";
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (!birdFilter.trim().equals("")) {
                    sql = "SELECT [Product].[ProductID]\n"
                            + "      ,[ProductName]\n"
                            + "      ,[Price]\n"
                            + "      ,[Quantity]\n"
                            + "      ,[CategoryID]\n"
                            + "      ,[ProductDetail]\n"
                            + "      ,[Size]\n"
                            + "      ,[AgeRecommendation]\n"
                            + "      ,[Date]\n"
                            + "      ,[Status]\n"
                            + "      ,[Country]\n"
                            + "      ,[imgPath]\n"
                            + "	  ,[Bird].BirdName\n"
                            + "  FROM [Product] INNER JOIN [CategoriesBird]\n"
                            + "  ON [Product].ProductID = [CategoriesBird].ProductID \n"
                            + "  INNER JOIN [Bird] ON [CategoriesBird].BirdID = [Bird].BirdID "
                            + "WHERE Status = 1 ";
                    sql = sql + and + "BirdName like '" + birdFilter + "'";
                } else {
                    sql = "SELECT [Product].[ProductID]\n"
                            + "      ,[ProductName]\n"
                            + "      ,[Price]\n"
                            + "      ,[Quantity]\n"
                            + "      ,[CategoryID]\n"
                            + "      ,[ProductDetail]\n"
                            + "      ,[Size]\n"
                            + "      ,[AgeRecommendation]\n"
                            + "      ,[Date]\n"
                            + "      ,[Status]\n"
                            + "      ,[Country]\n"
                            + "      ,[imgPath]\n"
                            + "  FROM [Product] "
                            + "WHERE Status = 1 ";
                }

                if (categoryFilter != -1) {
                    sql = sql + and + "[CategoryID] = " + categoryFilter + " ";
                }
                if (!sizeFilter.trim().equals("")) {
                    sql = sql + and + "Size like '" + sizeFilter + "'";
                }
                if (priceMinFilter <= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price < " + priceMaxFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter <= 0) {
                    sql = sql + and + "Price > " + priceMinFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price BETWEEN " + priceMinFilter + " AND " + priceMaxFilter + " ";
                }
//                if (!birdFilter.trim().equals("")) {
//                    sql = sql + and + "BirdName like '" + birdFilter + "'";
//                }
                String paging = "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                sql = sql + paging;
                stm = con.prepareStatement(sql);

                stm.setInt(1, (index - 1) * onPageProduct);
                stm.setInt(2, onPageProduct);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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

    public int getAmountProductFilter(int categoryFilter, String sizeFilter, double priceMinFilter, double priceMaxFilter, String birdFilter)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String and = "AND ";
        String sql = "";
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (!birdFilter.trim().equals("")) {
                    sql = "SELECT COUNT (*) "
                            + "FROM [Product] INNER JOIN [CategoriesBird]\n"
                            + "  ON [Product].ProductID = [CategoriesBird].ProductID \n"
                            + "  INNER JOIN [Bird] ON [CategoriesBird].BirdID = [Bird].BirdID "
                            + "WHERE Status = 1 ";
                    sql = sql + and + "BirdName like '" + birdFilter + "'";
                } else {
                    sql = "SELECT COUNT (*) "
                            + "FROM [Product] "
                            + "WHERE Status = 1 ";
                }

                if (categoryFilter != -1) {
                    sql = sql + and + "[CategoryID] = " + categoryFilter + " ";
                }
                if (!sizeFilter.trim().equals("")) {
                    sql = sql + and + "Size like '" + sizeFilter + "'";
                }
                if (priceMinFilter <= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price < " + priceMaxFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter <= 0) {
                    sql = sql + and + "Price > " + priceMinFilter + " ";
                } else if (priceMinFilter >= 0 && priceMaxFilter >= 0) {
                    sql = sql + and + "Price BETWEEN " + priceMinFilter + " AND " + priceMaxFilter + " ";
                }
//                if (!birdFilter.trim().equals("")) {
//                    sql = sql + and + "BirdName like '" + birdFilter + "'";
//                }
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
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
                    int date = rs.getInt("Date");
                    int status = rs.getInt("status");
                    String Country = rs.getString("country");
                    String ImgPath = rs.getString("imgPath");
                    ProductDTO result = new ProductDTO(productID, productName, price,
                            quantity, categoryID, productDetail, size, ageRecommendation,
                            date, status, Country, ImgPath);
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

    public static ProductDTO getProductByID(int getProductID) {
        ProductDTO product = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String s = "SELECT ProductID, ProductName, Price, Quantity, CategoryID, ProductDetail, Size, AgeRecommendation, Date , [Status], Country, imgPath\n"
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
                        int categoryID = kq.getInt("CategoryID");
                        String productDetail = kq.getString("ProductDetail");
                        String size = kq.getString("Size");
                        int ageRecommendation = kq.getInt("AgeRecommendation");
                        int date = kq.getInt("Date");
                        int status = kq.getInt("Status");
                        String country = kq.getString("Country");
                        String imgPath = kq.getString("imgPath");
                        product = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);

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

    public List<String> getSizeList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<String> listSize = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [ProjectBirdMealOrderSystem].[dbo].Product.Size\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Product]\n"
                        + "  GROUP BY [ProjectBirdMealOrderSystem].[dbo].Product.Size";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    listSize.add(rs.getString("Size"));
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
        return listSize;
    }

    public List<ProductDTO> getProductByOrderID(int orderID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderDetail] .[ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[OrderDetail] .[Price]\n"
                        + "      ,[OrderDetail] .[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,[ProductDetail]\n"
                        + "      ,[Size]\n"
                        + "      ,[AgeRecommendation]\n"
                        + "      ,[Date]\n"
                        + "      ,[Status]\n"
                        + "      ,[Country]\n"
                        + "      ,[imgPath]\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Product] \n"
                        + "  INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[OrderDetail] \n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].[OrderDetail].ProductID = [ProjectBirdMealOrderSystem].[dbo].[Product].ProductID\n"
                        + "	WHERE [OrderDetail].OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
                    listProduct.add(dto);
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
        return listProduct;
    }

    public List<ProductDTO> getProductByUserID(int orderID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderDetail] .[ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[OrderDetail] .[Price]\n"
                        + "      ,[OrderDetail] .[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,[ProductDetail]\n"
                        + "      ,[Size]\n"
                        + "      ,[AgeRecommendation]\n"
                        + "      ,[Date]\n"
                        + "      ,[Status]\n"
                        + "      ,[Country]\n"
                        + "      ,[imgPath]\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Product] \n"
                        + "  INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[OrderDetail] \n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].[OrderDetail].ProductID = [ProjectBirdMealOrderSystem].[dbo].[Product].ProductID\n"
                        + "  WHERE [OrderDetail].OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
                    listProduct.add(dto);
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
        return listProduct;
    }

    public List<ProductDTO> getProductInListByName(List<ProductDTO> list, String name) throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        list.stream().filter((product) -> (product.getProductName().toLowerCase().contains(name.toLowerCase()))).forEachOrdered((product) -> {
            listProduct.add(product);
        });
        return listProduct;
    }

    //lay cac san pham trong 1 oder thong qua orderid la thong tin san pham trong order khong phai o db product
    public List<ProductDTO> getProductInOrder(int OrderID)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT OrderDetail.[ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,OrderDetail.[Price]\n"
                        + "      ,OrderDetail.[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,[ProductDetail]\n"
                        + "      ,[Size]\n"
                        + "      ,[AgeRecommendation]\n"
                        + "      ,[Date]\n"
                        + "      ,[Status]\n"
                        + "      ,[Country]\n"
                        + "      ,[imgPath] "
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[OrderDetail]\n"
                        + "  INNER JOIN Product ON Product.ProductID = OrderDetail.ProductID\n"
                        + "  WHERE OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, OrderID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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

    public boolean createProduct(ProductDTO dto)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Product "
                        + "(ProductName, Price, Quantity, CategoryID, ProductDetail, "
                        + "Size, AgeRecommendation, Date, [Status], Country, imgPath) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getProductName());
                stm.setDouble(2, dto.getPrice());
                stm.setInt(3, dto.getQuantity());
                stm.setInt(4, dto.getCategoryID());
                stm.setString(5, dto.getProductDetail());
                stm.setString(6, dto.getSize());
                stm.setInt(7, dto.getAgeRecommendation());
                stm.setInt(8, dto.getDate());
                stm.setInt(9, dto.getStatus());
                stm.setString(10, dto.getCountry());
                stm.setString(11, dto.getImgPath());
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateProduct(ProductDTO dto)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Product set ProductName = ?, Price = ?, Quantity = ?, CategoryID = ?, \n"
                        + "ProductDetail = ?, Size = ?, AgeRecommendation = ?, Date = ?, \n"
                        + "[Status] = ?, Country = ?, imgPath = ? WHERE ProductID = ?";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getProductName());
                stm.setDouble(2, dto.getPrice());
                stm.setInt(3, dto.getQuantity());
                stm.setInt(4, dto.getCategoryID());
                stm.setString(5, dto.getProductDetail());
                stm.setString(6, dto.getSize());
                stm.setInt(7, dto.getAgeRecommendation());
                stm.setInt(8, dto.getDate());
                stm.setInt(9, dto.getStatus());
                stm.setString(10, dto.getCountry());
                stm.setString(11, dto.getImgPath());
                stm.setInt(12, dto.getProductID());
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    public List<ProductDTO> searchListProductOfStaff(String searchValue, int index, int onPageProduct)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [Product].[ProductID],[ProductName],[Price],[Quantity],\n"
                        + " [CategoryID],[ProductDetail],[Size],\n"
                        + " [AgeRecommendation],[Date],[Status],\n"
                        + " [Country],[imgPath] FROM [Product] \n"
                        + " WHERE ProductName like ? ";
                String paging = "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                sql = sql + paging;
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, (index - 1) * onPageProduct);
                stm.setInt(3, onPageProduct);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");

                    ProductDTO dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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

//    public static void main(String[] args) throws SQLException, NamingException, ClassNotFoundException {
//        ProductDAO dao = new ProductDAO();
//        List<ProductDTO> listProduct = new ArrayList<>();
//        listProduct = dao.pagingProductUser(1, 6, -1, "", -1, -1, "");
//        listProduct.forEach((dto)->{
//            System.out.println(dto);
//        });   
//    }
}
