/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

<<<<<<< Updated upstream
=======
import java.io.Serializable;
>>>>>>> Stashed changes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.dto.ProductDTO;
import sample.utils.DBUtils;
<<<<<<< Updated upstream

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
=======
//import ultil.DBHelper;

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
>>>>>>> Stashed changes

        try {
            con = DBUtils.getConnection();
            if (con != null) {
<<<<<<< Updated upstream
                String sql = "SELECT ProductID, ProductName, Price, Quantity, CategoryID, ProductDetail, Size, AgeRecommendation, Date , [Status], Country, imgPath\n"
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
                String sql = "SELECT ProductID, ProductName, Price, Quantity, CategoryID, ProductDetail, Size, AgeRecommendation, Date , [Status], Country, imgPath\n"
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
                String sql = "SELECT ProductID, ProductName, Price, Quantity, CategoryID, ProductDetail, Size, AgeRecommendation, Date , [Status], Country, imgPath\n"
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

    public static void main(String[] args) throws SQLException, NamingException, ClassNotFoundException {
        ProductDAO dao = new ProductDAO();
        List<ProductDTO> listProduct = dao.loadProducts();
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
                        String categoryID = kq.getString("CategoryID");
                        String productDetail = kq.getString("ProductDetail");
                        String size = kq.getString("Size");
                        int ageRecommendation = kq.getInt("AgeRecommendation");
                        String date = kq.getString("Date");
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

=======
                String sql = "  select ProductID, ProductName, price, quantity, CategoryID, \n"
                        + "  ProductDetail, size, AgeRecommendation, Date, Status, Country  \n"
                        + "  from Product";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("CategoryID");
                    String productDetail = rs.getString("ProductDetail");
                    String size = rs.getString("size");
                    int ageRecommendation = rs.getInt("AgeRecommendation");
                    String Date = rs.getString("Date");
                    boolean status = rs.getBoolean("Status");
                    String Country = rs.getString("Country");
                    ProductDTO dto = new ProductDTO(productID, productName,
                            price, quantity, categoryID, productDetail, size,
                            ageRecommendation, Date, status, Country);
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

    public ProductDTO getProductByProductID(String productID)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select * from Product where ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    //String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String productDetail = rs.getString("productDetail");
                    String size = rs.getString("size");
                    int ageRecommendation = rs.getInt("ageRecommendation");
                    String Date = rs.getString("date");
                    boolean status = rs.getBoolean("status");
                    String Country = rs.getString("country");

                    ProductDTO result = new ProductDTO(productID, productName,
                            price, quantity, categoryID, productDetail, size,
                            ageRecommendation, Date, status, Country);
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
>>>>>>> Stashed changes
}
