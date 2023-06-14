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
import java.util.LinkedHashMap;
import java.util.Map;
import javax.naming.NamingException;
import sample.dto.ProductDTO;
import sample.utils.DBUtils;

/**
 *
 * @author DucAnh
 */
public class OrderDetailGuestDAO implements Serializable {

    public static boolean createOrderDetailsForGuest(int orderID, LinkedHashMap<String, Integer> checkedItems)
            throws SQLException, NamingException, ClassNotFoundException {
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stm = con.prepareStatement("INSERT INTO OrderDetailsGuest (OrderID, ProductID, Quantity, Price) VALUES (?, ?, ?, ?)")) {

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
}
