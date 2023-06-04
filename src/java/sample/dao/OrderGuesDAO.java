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
import java.sql.Timestamp;
import javax.naming.NamingException;
import sample.dto.OrderGuestDTO;
import sample.utils.DBUtils;


/**
 *
 * @author DucAnh
 */
public class OrderGuesDAO implements Serializable{

    public int createNewOrder(String fullName, String phoneNumber, String ShippingDate, int status, String orderAddress, String total)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO OrderGuest \n"
                        + "(FullName, PhoneNumber, ShippingDate, Status, OrderAddress, Total) \n"
                        + "Output inserted.OrderID, inserted.OrderDate "
                        + "VALUES (?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, fullName);
                stm.setString(2, phoneNumber);
                stm.setString(3, ShippingDate);
                stm.setInt(4, status);
                stm.setString(5, orderAddress);
                stm.setDouble(6, new Double(total));
                rs = stm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    return orderID;
                }
            } //end if con connect success
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
        return -1;
    }

    public OrderGuestDTO getOrders(int orderID)
            throws NamingException, SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT OrderID, FullName, PhoneNumber, OrderDate, \n"
                        + "ShippingDate, Status, OrderAddress, Total \n"
                        + "FROM OrderGuest where OrderID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getNString("FullName");
                    String phoneNumber = rs.getNString("PhoneNumber");
                    Timestamp orderDate = rs.getTimestamp("OrderDate");
                    String shippingDate = rs.getNString("ShippingDate");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getNString("OrderAddress");
                    double total = rs.getDouble("Total");

                    OrderGuestDTO dto = new OrderGuestDTO(orderID, fullName, phoneNumber, orderDate, shippingDate, status, orderAddress, total);
                    return dto;
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
}
