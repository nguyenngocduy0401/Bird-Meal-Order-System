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
import sample.dto.OrderDetailDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDetailsDAO {

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
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OrderDetailsDAO dao= new OrderDetailsDAO();
        List<OrderDetailDTO> listOrder = dao.getProductByUserID(3);
        for (OrderDetailDTO string : listOrder) {
            System.out.println(string.getStatus());
        }
    }
}
