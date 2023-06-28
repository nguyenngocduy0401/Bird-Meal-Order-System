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
import java.util.LinkedHashMap;
import java.util.Map;
import javax.naming.NamingException;
import sample.dto.CartDetailDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class CartDetailDAO {
    
    public static boolean createCartDetailsForCustomer(int CartID, LinkedHashMap<String, Integer> checkedItems)
            throws SQLException, NamingException, ClassNotFoundException {
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stm = con.prepareStatement("INSERT INTO CartDetail(CartID, ProductID, Quantity) VALUES (?, ?, ?)")) {

            con.setAutoCommit(false);

            int affectedRows = 0;

            for (Map.Entry<String, Integer> entry : checkedItems.entrySet()) {
                String productID = entry.getKey();
                int quantity = entry.getValue();
               
                stm.setInt(1, CartID);
                stm.setString(2, productID);
                stm.setInt(3, quantity);

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

    public static boolean cleanCart(int cartID) throws Exception {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("DELETE FROM CartDetail\n"
                        + "WHERE CartID = ?;");
                pst.setInt(1, cartID);

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

    public static boolean deleteCartDetail(int cartID, int productID) throws Exception{
    Connection connection = null;
    PreparedStatement pst = null;
    boolean result = false;
    try {
        // Establish a database connection
        connection = DBUtils.getConnection();
        // Prepare the SQL statement
        if (connection != null) {
            pst = connection.prepareStatement("DELETE FROM CartDetail WHERE CartID = ? AND ProductID = ?");
            pst.setInt(1, cartID);
            pst.setInt(2, productID);

            // Execute the SQL statement
            int rowCount = pst.executeUpdate();
            result = (rowCount > 0);
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

    public static boolean updateProductCart(int cartID, int pid, int quantity) throws Exception {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("UPDATE CartDetail \n"
                        + "SET Quantity = ? \n"
                        + "WHERE CartID = ? AND ProductID = ?");
                pst.setInt(1, quantity);
                pst.setInt(2, cartID);
                pst.setInt(3, pid);

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

    public static CartDetailDTO getNumberProductInCart(int getCartID, int getProductID) {
        CartDetailDTO cartDetailDTO = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT Quantity\n"
                        + "FROM CartDetail \n"
                        + "WHERE CartID = ? AND ProductID = ?;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getCartID);
                pst.setInt(2, getProductID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int Quantity = rs.getInt("Quantity");
                        cartDetailDTO = new CartDetailDTO(getCartID, getProductID, Quantity);

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
        return cartDetailDTO;
    }

    public static LinkedHashMap<String, Integer> getCartDetail(int getCartID) {
        LinkedHashMap<String, Integer> cart = new LinkedHashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT ProductID, Quantity\n"
                        + "FROM CartDetail \n"
                        + "WHERE CartID = ?;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getCartID);
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

    public static boolean insertNewProductCart(int cartID, int pid) throws Exception {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("INSERT INTO CartDetail (CartID, ProductID, Quantity)\n"
                        + "VALUES (?, ?, 1);");
                pst.setInt(1, cartID);
                pst.setInt(2, pid);

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
