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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import sample.dto.OrderDetailDTO;
import sample.dto.ProductDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDetailsDAO {
    public static LinkedHashMap<String, Integer> getProductFromOrderDetail(int getOrderID) {
        LinkedHashMap<String, Integer> cart = new LinkedHashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT ProductID, Quantity\n"
                        + "FROM OrderDetail \n"
                        + "WHERE OrderID = ?;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getOrderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int ProductID = rs.getInt("ProductID");
                        int Quantity = rs.getInt("Quantity");
                        cart.put(Integer.toString(ProductID), Quantity);

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
        return cart;
    }

    public LinkedHashMap<ProductDTO, Integer> getProductDTOFromOrderDetail(int getOrderID) {
        LinkedHashMap<ProductDTO, Integer> productList = new LinkedHashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM (SELECT * from OrderDetail WHERE OrderID = ?) as productlist JOIN [Product] ON productlist.ProductID = Product.ProductID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getOrderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
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
                        int Quantity = rs.getInt("Quantity");
                        ProductDTO productDTO = new ProductDTO(productID, productName, price, Quantity, categoryID, productDetail, size, ageRecommendation, date, status, country, imgPath);
                        productList.put(productDTO, Quantity);

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
        return productList;
    }

    public static boolean createOrderDetailsForCustomer(int orderID, LinkedHashMap<String, Integer> checkedItems)
            throws SQLException, NamingException, ClassNotFoundException {
        try (Connection con = DBUtils.getConnection(); PreparedStatement stm = con.prepareStatement("INSERT INTO OrderDetail(OrderID, ProductID, Quantity, Price) VALUES (?, ?, ?, ?)")) {

            con.setAutoCommit(false);

            int affectedRows = 0;

            for (Map.Entry<String, Integer> entry : checkedItems.entrySet()) {
                String productID = entry.getKey();
                int quantity = entry.getValue();
                ProductDTO product = ProductDAO.getProductByID(Integer.parseInt(productID)); // Retrieve the price based on the product ID

                stm.setInt(1, orderID);
                stm.setString(2, productID);
                stm.setInt(3, quantity);
                stm.setDouble(4, product.getPrice());

                stm.addBatch();

                affectedRows++;
            }

            int[] batchResults = stm.executeBatch();
            con.commit();

            if (batchResults.length == affectedRows) {
                return true;
            }
        } catch (SQLException ex) {
            // Handle the exception or rethrow it
        }

        return false;
    }

    public List<OrderDetailDTO> getProductByUserID(int userID, int amount, int onPageProduct) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDetailDTO> listOrder = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderDetail] .[ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[OrderDetail] .[Price]\n"
                        + "      ,[OrderDetail] .[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,OrderStatus.[Status]\n"
                        + "      ,[imgPath]\n"
                        + "	  ,[OrderStatus].StatusDate\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Product] \n"
                        + "  INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[OrderDetail] \n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].[OrderDetail].ProductID = [ProjectBirdMealOrderSystem].[dbo].[Product].ProductID\n"
                        + "  INNER JOiN [ProjectBirdMealOrderSystem].[dbo].[Order]\n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].[Order].OrderID = [ProjectBirdMealOrderSystem].[dbo].[OrderDetail].OrderID\n"
                        + "    INNER JOiN [ProjectBirdMealOrderSystem].[dbo].OrderStatus\n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].OrderStatus.OrderID = [ProjectBirdMealOrderSystem].[dbo].[Order].OrderID\n"
                        + "	WHERE [Order].UserID = ? "
                        + "ORDER BY ProductID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setInt(2, amount);
                stm.setInt(3, onPageProduct);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    int status = rs.getInt("Status");
                    String imgPath = rs.getString("imgPath");
                    String statusDate = rs.getString("StatusDate");

                    OrderDetailDTO dto = new OrderDetailDTO(status, statusDate, productID, productName, price, quantity, imgPath, categoryID);
                    listOrder.add(dto);
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
        return listOrder;
    }

    public List<OrderDetailDTO> getProductByUserID(int userID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDetailDTO> listOrder = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderDetail] .[ProductID]\n"
                        + "      ,[ProductName]\n"
                        + "      ,[OrderDetail] .[Price]\n"
                        + "      ,[OrderDetail] .[Quantity]\n"
                        + "      ,[CategoryID]\n"
                        + "      ,OrderStatus.[Status]\n"
                        + "      ,[imgPath]\n"
                        + "	  ,[OrderStatus].StatusDate\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Product] \n"
                        + "  INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[OrderDetail] \n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].[OrderDetail].ProductID = [ProjectBirdMealOrderSystem].[dbo].[Product].ProductID\n"
                        + "  INNER JOiN [ProjectBirdMealOrderSystem].[dbo].[Order]\n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].[Order].OrderID = [ProjectBirdMealOrderSystem].[dbo].[OrderDetail].OrderID\n"
                        + "    INNER JOiN [ProjectBirdMealOrderSystem].[dbo].OrderStatus\n"
                        + "  ON [ProjectBirdMealOrderSystem].[dbo].OrderStatus.OrderID = [ProjectBirdMealOrderSystem].[dbo].[Order].OrderID\n"
                        + "	WHERE [Order].UserID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    int categoryID = rs.getInt("CategoryID");
                    int status = rs.getInt("Status");
                    String imgPath = rs.getString("imgPath");
                    String statusDate = rs.getString("StatusDate");

                    OrderDetailDTO dto = new OrderDetailDTO(status, statusDate, productID, productName, price, quantity, imgPath, categoryID);
                    listOrder.add(dto);
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
        return listOrder;
    }

    public List<OrderDetailDTO> getProductInListByName(List<OrderDetailDTO> list, String name) throws SQLException, ClassNotFoundException {
        List<OrderDetailDTO> listOrder = new ArrayList<>();
        list.stream().filter((product) -> (product.getProductName().toLowerCase().contains(name.toLowerCase()))).forEachOrdered((product) -> {
            listOrder.add(product);
        });
        return listOrder;
    }

    public int getAmount(List<OrderDetailDTO> list) throws SQLException, ClassNotFoundException {
        int amount = list.size();
        return amount;
    }

}
