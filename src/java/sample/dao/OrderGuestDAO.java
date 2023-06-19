/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class OrderGuestDAO {
    
    public static int createNewOrderForGuest(String fullName, String phoneNumber, int status, String orderAddress, String notes) throws Exception {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs ;
        int orderID = -1;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("INSERT INTO OrderGuest(FullName,PhoneNumber,OrderDate,Status,OrderAddress,Notes) \n"
                        + "OUTPUT inserted.OrderID\n"
                        + "VALUES(?,?,?,?,?,?);");

                pst.setString(1, fullName);
                pst.setString(2, phoneNumber);
                pst.setString(3, formattedDate);
                pst.setInt(4, status);
                pst.setString(5, orderAddress);
                pst.setString(6, notes);
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
    
}
