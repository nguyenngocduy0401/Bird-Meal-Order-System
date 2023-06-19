/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sample.dto.CartDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class CartDAO {

    public static CartDTO getCartByUserID(int getUserID) {
        CartDTO cart = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String s = "select UserID, CartID\n"
                        + "from [Cart]\n"
                        + "where  UserID = ?";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setInt(1, getUserID);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        int userID = kq.getInt("UserID");
                        int cartID = kq.getInt("CartID");
                        cart = new CartDTO(userID, cartID);

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

    public static boolean createNewCart(int userID) throws Exception {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("INSERT INTO Cart(UserID)\n"
                        + "VALUES (?);");
                pst.setInt(1, userID);

                // Execute the SQL statement
                int rowCount = pst.executeUpdate();
                result = (rowCount == 1);
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
        return result;
    }

}
