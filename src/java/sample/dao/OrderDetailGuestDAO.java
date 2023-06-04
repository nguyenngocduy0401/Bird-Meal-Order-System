/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import sample.dto.OrderDetailGuestDTO;
import sample.dto.ProductDTO;
import sample.utils.DBUtils;


/**
 *
 * @author DucAnh
 */
public class OrderDetailGuestDAO implements Serializable{

    private List<OrderDetailGuestDTO> orderDetailsList;

    public List<OrderDetailGuestDTO> getOrderDetailsList() {
        return orderDetailsList;
    }

    public boolean createOrderDetails(int orderID, Map<ProductDTO, Integer> checkedItems)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false); //*****
                String sql = "INSERT INTO OrderDetailsGuest \n"
                        + "(OrderID, ProductID, Quantity, Price) \n"
                        + "VALUES (?,?,?,?) ";
                stm = con.prepareStatement(sql);
                Double price;
                Double total;
                int quantity;
//                int i = 0; //*****
                int affectedRows = 0;
                for (ProductDTO dto : checkedItems.keySet()) {
                    quantity = checkedItems.get(dto);
                    price = dto.getPrice();
                    total = quantity * price;
                    stm.setInt(1, orderID);
                    stm.setInt(2, dto.getProductID());
                    stm.setInt(3, quantity);
                    stm.setDouble(4, price);
                    affectedRows += stm.executeUpdate(); //*****
//                    stm.addBatch(); //******
//                    i++;//******
                }

                con.commit(); //*****

                if (affectedRows == checkedItems.size()) { //*****
                    return true;
                }

//                if (i % 100 == 0 || i == checkedItems.size()) {
//                    stm.executeBatch();
//                    return true;
//                }
            }
        } catch (SQLException ex) {
            if (con != null) {
                con.rollback();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
