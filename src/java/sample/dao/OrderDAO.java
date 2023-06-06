/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<OrderDTO> loadOrder()
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT OrderID, UserID, OrderDate, "
                        + "ShippingDate, Status, OrderAddress "
                        + "FROM [Order] ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int userID = rs.getInt("UserID");
                    Date orderDate = rs.getDate("OrderDate");
                    Date shippingDate = rs.getDate("ShippingDate");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getString("OrderAddress");

                    OrderDTO dto =new OrderDTO(orderID, userID, orderDate, shippingDate, status, orderAddress);
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
    
    public List<OrderDTO> loadOrderUser(int userID)
            throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT OrderID, OrderDate, "
                        + "ShippingDate, Status, OrderAddress "
                        + "FROM [Order] "
                        + "WHERE UserID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    Date shippingDate = rs.getDate("ShippingDate");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getString("OrderAddress");

                    OrderDTO dto =new OrderDTO(orderID, userID, orderDate, shippingDate, status, orderAddress);
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
                String sql = "SELECT OrderID, UserID, OrderDate, "
                        + "ShippingDate, Status, OrderAddress "
                        + "FROM [Order] "
                        + "ORDER BY OrderID "
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
                stm = con.prepareStatement(sql);
                stm.setInt(1,(index - 1) * onPageOrder);
                stm.setInt(2, onPageOrder);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int userID = rs.getInt("UserID");
                    Date orderDate = rs.getDate("OrderDate");
                    Date shippingDate = rs.getDate("ShippingDate");
                    int status = rs.getInt("Status");
                    String orderAddress = rs.getString("OrderAddress");

                    OrderDTO dto =new OrderDTO(orderID, userID, orderDate, shippingDate, status, orderAddress);
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
    
    
    public static void main(String[] args) throws SQLException, NamingException, ClassNotFoundException {
        OrderDAO dao = new OrderDAO();
        List<OrderDTO> list = dao.loadOrderUser(3);
        list.forEach((o) -> {
            System.out.println(o);
        });
    }
}
