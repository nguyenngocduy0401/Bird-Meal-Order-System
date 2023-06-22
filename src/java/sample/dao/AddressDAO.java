/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.dto.AddressDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class AddressDAO {
        public static AddressDTO getAddressByID(int getAddressID) {
        AddressDTO addressDTO = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT AddressID, UserID, Fullname, AddressDetail, PhoneNumber \n"
                        + "FROM Address \n"
                        + "WHERE AddressID = ? ;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getAddressID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int addressID = rs.getInt("AddressID");
                        int userID = rs.getInt("UserID");
                        String fullName = rs.getString("Fullname");
                        String addressDetail = rs.getString("AddressDetail");
                        String phoneNumber = rs.getString("PhoneNumber");
                        addressDTO = new AddressDTO(addressID, userID, fullName, addressDetail, phoneNumber);
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
        return addressDTO;
    }

    public static boolean updateAddressDefault(int userID, int addressID) throws Exception {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("UPDATE Address \n"
                        + "SET Token = 'default' \n"
                        + "WHERE UserID = ? AND AddressID = ?;");
                pst.setInt(1, userID);
                pst.setInt(2, addressID);

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

    public static boolean createNewAddress(int userID, String name, String phoneNumber, String address) throws Exception {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("INSERT INTO [Address] (Fullname, UserID, AddressDetail, PhoneNumber)\n"
                        + "VALUES (?, ?, ?, ?);");
                pst.setString(1, name);
                pst.setInt(2, userID);
                pst.setString(3, address);
                pst.setString(4, phoneNumber);

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

    public static ArrayList<AddressDTO> getAddress(int getUserID) {
        ArrayList<AddressDTO> list = new ArrayList<>();
        AddressDTO addressDTO = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT AddressID, UserID, Fullname, AddressDetail, PhoneNumber \n"
                        + "FROM Address \n"
                        + "WHERE UserID = ? ;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getUserID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int addressID = rs.getInt("AddressID");
                        int userID = rs.getInt("UserID");
                        String fullName = rs.getString("Fullname");
                        String addressDetail = rs.getString("AddressDetail");
                        String phoneNumber = rs.getString("PhoneNumber");
                        addressDTO = new AddressDTO(addressID, userID, fullName, addressDetail, phoneNumber);
                        list.add(addressDTO);
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
        return list;
    }
}
