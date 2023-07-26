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

    public static List<ProductDTO> loadProducts()
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

    public static boolean minusProductQuantity(Integer value, int productID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    String DateManufacture = rs.getString("DateManufacture");
                    int status = rs.getInt("status");
                    String Country = rs.getString("country");
                    String ImgPath = rs.getString("imgPath");
                    ProductDTO result = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, Country, ImgPath, DateManufacture);
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

    public static ProductDTO getProductByIDFix(int getProductID) {
        ProductDTO product = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String s = "SELECT ProductID, ProductName, Price, Quantity, CategoryID, ProductDetail, Size, AgeRecommendation, Date , [Status], Country, imgPath,dateManufacture\n"
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
                        String dateManufacture = kq.getString("dateManufacture");
                        product = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath, dateManufacture);

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

    public ArrayList<ProductDTO> getProductByOrderID(int orderID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> listProduct = new ArrayList<>();
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

    public static List<ProductDTO> getProductInListByName(List<ProductDTO> list, String name) throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        list.stream().filter((product) -> (product.getProductName().toLowerCase().contains(name.toLowerCase()))).forEachOrdered((product) -> {
            listProduct.add(product);
        });
        return listProduct;
    }

    //lay cac san pham trong 1 oder thong qua orderid la thong tin san pham trong order khong phai o db product
    public ArrayList<ProductDTO> getProductInOrder(int OrderID)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> listProduct = new ArrayList<>();

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

    public int createProduct(ProductDTO dto)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int productID = -1;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Product "
                        + "(ProductName, Price, Quantity, CategoryID, ProductDetail, "
                        + "Size, AgeRecommendation, Date, DateManufacture, [Status], Country, imgPath) "
                        + "OUTPUT inserted.ProductID "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
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
                stm.setString(9, dto.getDateManufacture());
                stm.setInt(10, dto.getStatus());
                stm.setString(11, dto.getCountry());
                stm.setString(12, dto.getImgPath());
//                int effectRow = stm.executeUpdate();
//                if (effectRow > 0) {
//                    result = true;
//                }
                rs = stm.executeQuery();
                if(rs.next()) {
                    productID = rs.getInt("ProductID");
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
        return productID;
    }

    public boolean updateProduct(ProductDTO dto)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Product SET ProductName = ?, Price = ?, Quantity = ?, CategoryID = ?, \n"
                        + "ProductDetail = ?, Size = ?, AgeRecommendation = ?, Date = ?, DateManufacture = ?, \n"
                        + "[Status] = ?, Country = ?, imgPath = ? WHERE ProductID = ?";

                // Kiểm tra nếu không có hình mới, sử dụng lại hình hiện tại
                String imagePath = dto.getImgPath(); // Lấy đường dẫn hình mới
                boolean shouldUpdateImage = (imagePath != null && !imagePath.isEmpty());

                if (!shouldUpdateImage) {
                    sql = sql.replace("imgPath = ?", "imgPath = imgPath");
                }

                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getProductName());
                stm.setDouble(2, dto.getPrice());
                stm.setInt(3, dto.getQuantity());
                stm.setInt(4, dto.getCategoryID());
                stm.setString(5, dto.getProductDetail());
                stm.setString(6, dto.getSize());
                stm.setInt(7, dto.getAgeRecommendation());
                stm.setInt(8, dto.getDate());
                stm.setString(9, dto.getDateManufacture());
                stm.setInt(10, dto.getStatus());
                stm.setString(11, dto.getCountry());

                // Chỉ thêm tham số imgPath nếu cần
                if (shouldUpdateImage) {
                    stm.setString(12, imagePath);
                    stm.setInt(13, dto.getProductID());
                } else {
                    stm.setInt(12, dto.getProductID());
                }

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

    public static ProductDTO getProductByID(int productID, int quantity) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO dto = new ProductDTO();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ProductName, Price, CategoryID, ProductDetail, Size, AgeRecommendation, Date , [Status], Country, imgPath\n"
                        + "FROM Product\n"
                        + "WHERE ProductID = ?;";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int categoryID = rs.getInt("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("Size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    int date = rs.getInt("Date");
                    int status = rs.getInt("Status");
                    String country = rs.getString("Country");
                    String imgPath = rs.getString("imgPath");
                    dto = new ProductDTO(productID, productName, price, quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
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
        return dto;
    }

    public static List<ProductDTO> listTop5Product() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT TOP (5) OrderDetail.ProductID, SUM(OrderDetail.Quantity) AS TotalSold\n"
                        + "FROM OrderDetail INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID\n"
                        + "GROUP BY OrderDetail.ProductID\n"
                        + "ORDER BY TotalSold DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int quantity = rs.getInt("TotalSold");
                    ProductDTO dto = getProductByID(productID, quantity);
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

    public boolean deleteAcc(int productID)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        int pk = productID;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "DELETE FROM Product\n "
                        + "WHERE ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, pk);
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

    public static List<ProductDTO> list5NewProduct() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT TOP (5) [ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[Price]\n"
                        + "      ,[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,[ProductDetail]\n"
                        + "      ,[Size]\n"
                        + "      ,[AgeRecommendation]\n"
                        + "      ,[Date]\n"
                        + "      ,[DateManufacture]\n"
                        + "      ,[Status]\n"
                        + "      ,[Country]\n"
                        + "      ,[imgPath]\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Product]\n"
                        + "  WHERE [Status] = 1\n"
                        + "  ORDER BY ProductID DESC";
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

    public static boolean minusProductQuantity(int quantity, int productID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null; //mo cuoi cung đóng đầu tiên (finally close) neu ko tk kia dong trc se bi crash
        boolean result = false; //thong qua bien trung gian
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Update [Product] "
                        + "Set quantity = quantity - ? "
                        + "Where ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, productID);

                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            } //end connection has existed 
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

    public boolean HideProduct(int productId, int status) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [Product] SET [Status] = ? WHERE [ProductID] = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, productId);

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Product status updated successfully");
                    return true;
                } else {
                    System.out.println("Failed to update product status");
                    return false;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception as needed
            }
        }

        return false;
    }

    public static List<ProductDTO> loadProductsStatus()
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
                        + "FROM Product WHERE [Status] = 0";
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

}
