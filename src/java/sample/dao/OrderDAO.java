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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.dto.OrderDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    public static int createNewOrderForCustomer(int userID, String fullName, String phoneNumber, int status, String orderAddress, String notes, double shippingFee) throws Exception {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs;
        int orderID = -1;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("INSERT INTO [Order](UserID, FullName, PhoneNumber, OrderDate, Status, OrderAddress, Notes, shippingFee) \n"
                        + "OUTPUT inserted.OrderID\n"
                        + "VALUES(?,?,?,?,?,?,?,?);");
                pst.setInt(1, userID);
                pst.setString(2, fullName);
                pst.setString(3, phoneNumber);
                pst.setString(4, formattedDate);
                pst.setInt(5, status);
                pst.setNString(6, orderAddress);
                pst.setString(7, notes);
                pst.setDouble(8, shippingFee);
                // Execute the SQL statement
                rs = pst.executeQuery();
                if (rs.next()) {
                    orderID = rs.getInt("OrderID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderID;
    }
public static int createNewOrderForGuest(String fullName, String phoneNumber, int status, String orderAddress, String notes, double shippingFee) throws Exception {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs;
        int orderID = -1;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("INSERT INTO [Order](FullName, PhoneNumber, OrderDate, Status, OrderAddress, Notes, shippingFee) \n"
                        + "OUTPUT inserted.OrderID\n"
                        + "VALUES(?,?,?,?,?,?,?);");
                pst.setString(1, fullName);
                pst.setString(2, phoneNumber);
                pst.setString(3, formattedDate);
                pst.setInt(4, status);
                pst.setNString(5, orderAddress);
                pst.setString(6, notes);
                pst.setDouble(7, shippingFee);
                // Execute the SQL statement
                rs = pst.executeQuery();
                if (rs.next()) {
                    orderID = rs.getInt("OrderID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderID;
    }
public boolean updateStatusOrder(int orderID, int status)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null; 
        boolean result = false; 
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Update [Order] "
                        + "Set Status = ? "
                        + "Where OrderID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, orderID);

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

    public ArrayList<OrderDTO> loadOrder()
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrderDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + ",[Order].[UserID]\n"
                        + ",[Order].[FullName]\n"
                        + ",[Order].[PhoneNumber]\n"
                        + ",[Order].[OrderDate]\n"
                        + ",[Order].[ShippingDate]\n"
                        + ",[Order].[ShippingFee]\n"
                        + ",[Order].[Status]\n"
                        + ",[OrderAddress]\n"
                        + ",[Notes] "
                        + "FROM [Order] ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String fullName = rs.getString("FullName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String date = rs.getString("OrderDate");
                    String shippingDate = rs.getString("ShippingDate");
                    double shippingFee = rs.getDouble("ShippingFee");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getString("OrderAddress");
                    int userID = rs.getInt("UserID");
                    String note = rs.getString("Notes");
                    OrderDTO dto = new OrderDTO(orderID, userID, fullName, phoneNumber, shippingDate, date, status, orderAddress, note, shippingFee);
                    list.add(dto);
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
        return list;
    }

    public List<OrderDTO> loadOrderUser(String userName)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + ",[Order].[UserID]\n"
                        + ",[Order].[FullName]\n"
                        + ",[Order].[PhoneNumber]\n"
                        + ",[Order].[OrderDate]\n"
                        + ",[Order].[ShippingDate]\n"
                        + ",[Order].[Status]\n"
                        + ",[OrderAddress]\n"
                        + ",[Notes] "
                        + "FROM [ProjectBirdMealOrderSystem].[dbo].[Order]\n"
                        + "INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[User]\n"
                        + "ON [ProjectBirdMealOrderSystem].[dbo].[Order].UserID = [ProjectBirdMealOrderSystem].[dbo].[User].UserID\n"
                        + "WHERE UserName  like ? "
                        + " ORDER BY [OrderID] DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String fullName = rs.getString("FullName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String date = rs.getString("OrderDate");
                    String shippingDate = rs.getString("ShippingDate");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getString("OrderAddress");
                    int userID = rs.getInt("UserID");
                    String note = rs.getString("Notes");
                    OrderDTO dto = new OrderDTO(orderID, userID, fullName, phoneNumber, shippingDate, date, status, orderAddress, note);
                    list.add(dto);
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
        return list;
    }

    public int getAmountOrder() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT (*) "
                        + "FROM [Order] ";
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

    public List<OrderDTO> loadPagingOrder(int index, int onPageOrder)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + ",[Order].[UserID]\n"
                        + ",[Order].[FullName]\n"
                        + ",[Order].[PhoneNumber]\n"
                        + ",[Order].[OrderDate]\n"
                        + ",[Order].[ShippingDate]\n"
                        + ",[Order].[Status]\n"
                        + ",[OrderAddress]\n"
                        + ",[Notes] "
                        + "ORDER BY OrderID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, (index - 1) * onPageOrder);
                stm.setInt(2, onPageOrder);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String fullName = rs.getString("FullName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String date = rs.getString("OrderDate");
                    String shippingDate = rs.getString("ShippingDate");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getString("OrderAddress");
                    int userID = rs.getInt("UserID");
                    String note = rs.getString("Notes");
                    OrderDTO dto = new OrderDTO(orderID, userID, fullName, phoneNumber, shippingDate, date, status, orderAddress, note);
                    list.add(dto);
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
        return list;
    }

    public ArrayList<OrderDTO> loadOrderByUsername(String userName)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrderDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + ",[Order].[UserID]\n"
                        + ",[Order].[FullName]\n"
                        + ",[Order].[PhoneNumber]\n"
                        + ",[Order].[OrderDate]\n"
                        + ",[Order].[ShippingDate]\n"
                        + ",[Order].[Status]\n"
                        + ",[OrderAddress]\n"
                        + ",[Notes] "
                        + "FROM [ProjectBirdMealOrderSystem].[dbo].[Order]\n"
                        + "INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[User]\n"
                        + "ON [ProjectBirdMealOrderSystem].[dbo].[Order].UserID = [ProjectBirdMealOrderSystem].[dbo].[User].UserID\n"
                        + "WHERE UserName  like ? "
                        + " ORDER BY [OrderID] DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String fullName = rs.getString("FullName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String date = rs.getString("OrderDate");
                    String shippingDate = rs.getString("ShippingDate");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getString("OrderAddress");
                    int userID = rs.getInt("UserID");
                    String note = rs.getString("Notes");
                    OrderDTO dto = new OrderDTO(orderID, userID, fullName, phoneNumber, shippingDate, date, status, orderAddress, note);
                    list.add(dto);
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
        return list;
    }

    public ArrayList<OrderDTO> loadOrderByUsername(String userName, int status)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrderDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + ",[Order].[UserID]\n"
                        + ",[Order].[OrderDate]\n"
                        + ",[Order].[ShippingDate]\n"
                        + ",[Order].FullName\n"
                        + ",[Order].PhoneNumber\n"
                        + ",[Order].[Status]\n"
                        + ",[OrderAddress]\n"
                        + ",[Notes] "
                        + "FROM [ProjectBirdMealOrderSystem].[dbo].[Order]\n"
                        + "INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[User]\n"
                        + "ON [ProjectBirdMealOrderSystem].[dbo].[Order].UserID = [ProjectBirdMealOrderSystem].[dbo].[User].UserID\n"
                        + "WHERE UserName  like ? AND [Order].[Status] = ? "
                        + " ORDER BY [OrderID] DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                stm.setInt(2, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String date = rs.getString("OrderDate");
                    String orderAddress = rs.getString("OrderAddress");
                    int userID = rs.getInt("UserID");
                    String note = rs.getString("Notes");
                    String fullName = rs.getString("FullName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    OrderDTO dto = new OrderDTO(orderID, userID, fullName, phoneNumber, date, status, orderAddress, note);
                    list.add(dto);
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
        return list;
    }

    public static double getMonthEarning(int month, int year) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        double result = 0;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT SUM(OrderDetail.Quantity * OrderDetail.Price) AS TotalSold\n"
                        + "  FROM [Order] INNER JOIN [OrderDetail]\n"
                        + "  ON [Order].OrderID = OrderDetail.OrderID\n"
                        + "  WHERE MONTH([ShippingDate])=? AND YEAR([ShippingDate]) = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, month);
                stm.setInt(2, year);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = rs.getDouble("TotalSold");
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
        return result;
    }

    public static double getYearEarning(int year) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        double result = 0;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT SUM(OrderDetail.Quantity * OrderDetail.Price) AS TotalSold\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Order] INNER JOIN [ProjectBirdMealOrderSystem].[dbo].[OrderDetail]\n"
                        + "  ON [Order].OrderID = OrderDetail.OrderID\n"
                        + "  WHERE YEAR([ShippingDate]) = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = rs.getDouble("TotalSold");
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
        return result;
    }

    public static OrderDTO loadOrderByOrderID(int orderID)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO order = new OrderDTO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + ",[Order].[UserID]\n"
                        + ",[Order].[OrderDate]\n"
                        + ",[Order].[ShippingDate]\n"
                        + ",[Order].FullName\n"
                        + ",[Order].PhoneNumber\n"
                        + ",[Order].[Status]\n"
                        + ",[OrderAddress]\n"
                        + ",[Notes] "
                        + "FROM [ProjectBirdMealOrderSystem].[dbo].[Order]\n"
                        + "WHERE OrderID = ? "
                        + " ORDER BY [OrderID] DESC";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String date = rs.getString("OrderDate");
                    String shippingDate = rs.getString("ShippingDate");
                    String orderAddress = rs.getString("OrderAddress");
                    int userID = rs.getInt("UserID");
                    String note = rs.getString("Notes");
                    String fullName = rs.getString("FullName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    int status = rs.getInt("Status");
                    order = new OrderDTO(orderID, userID, fullName, phoneNumber, shippingDate, date, status, orderAddress, note);
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
        return order;
    }
    public boolean checkOrderInProcess(int userID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT orderID "
                        + "FROM [order] "
                        + "WHERE userid = ? "
                        + "AND status > 0";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
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
        return result;
    }

    public boolean updateSuccessOrder(int orderID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
