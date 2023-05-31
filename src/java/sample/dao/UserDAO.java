/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sample.dto.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class UserDAO {

    public static UserDTO getUser(String getUsername, String getPassword) {
        UserDTO user = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String s = "select UserName,Password,Email,Fullname,Role,Status,Address,PhoneNumber,Gender,NumberReport\n"
                        + "from [User]\n"
                        + "where  UserName=? and Password=? ";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setString(1, getUsername);
                pst.setString(2, getPassword);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        
                        String userName = kq.getString("UserName");
                        String password = kq.getString("Password");
                        String email = kq.getString("Email");
                        String fullName = kq.getString("Fullname");
                        int role = kq.getInt("Role");
                        boolean status = kq.getBoolean("Status");
                        String address = kq.getString("Address");
                        int phoneNumber = kq.getInt("PhoneNumber");
                        boolean gender = kq.getBoolean("Gender");
                        int numberReport = kq.getInt("NumberReport");
                        user = new UserDTO(role, userName, password, email, fullName, role, status, address, phoneNumber, gender, numberReport);

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
        return user;
    }
    
    public static UserDTO getUserByEmail(String getEmail) {
        UserDTO user = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String s = "select UserName,Password,Email,Fullname,Role,Status,Address,PhoneNumber,Gender,NumberReport\n"
                        + "from [User]\n"
                        + "where  Email=?";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setString(1, getEmail);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        
                        String userName = kq.getString("UserName");
                        String password = kq.getString("Password");
                        String email = kq.getString("Email");
                        String fullName = kq.getString("Fullname");
                        int role = kq.getInt("Role");
                        boolean status = kq.getBoolean("Status");
                        String address = kq.getString("Address");
                        int phoneNumber = kq.getInt("PhoneNumber");
                        boolean gender = kq.getBoolean("Gender");
                        int numberReport = kq.getInt("NumberReport");
                        user = new UserDTO(role, userName, password, email, fullName, role, status, address, phoneNumber, gender, numberReport);

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
        return user;
    }
    
    
    public static boolean updateToken(String token, String username) {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
                pst = connection.prepareStatement("UPDATE [User]\n"
                        + "SET Token = ?\n"
                        + "WHERE UserName = ?;");
                pst.setString(2, username);
                pst.setString(1, token);
               
                
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
    //DELETE FROM Customers WHERE
    public static UserDTO getToken(String token) {
        UserDTO user = null;
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                String s = "select UserName,Password,Email,Fullname,Role,Status,Address,PhoneNumber,Gender,NumberReport\n"
                        + "from [User]\n"
                        + "where  Token=?";
                pst = connection.prepareStatement(s);
                pst.setString(1, token);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        
                        String userName = kq.getString("UserName");
                        String password = kq.getString("Password");
                        String email = kq.getString("Email");
                        String fullName = kq.getString("Fullname");
                        int role = kq.getInt("Role");
                        boolean status = kq.getBoolean("Status");
                        String address = kq.getString("Address");
                        int phoneNumber = kq.getInt("PhoneNumber");
                        boolean gender = kq.getBoolean("Gender");
                        int numberReport = kq.getInt("NumberReport");
                        user = new UserDTO(role, userName, password, email, fullName, role, status, address, phoneNumber, gender, numberReport);
                        
                    }
                    
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
        return user;
    }
    public static boolean deleteToken(String token) {
        Connection connection = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            // Establish a database connection
            connection = DBUtils.getConnection();
            // Prepare the SQL statement
            if (connection != null) {
               pst = connection.prepareStatement("UPDATE [User]\n"
                        + "SET Token = ?\n"
                        + "WHERE Token = ?;");
                pst.setString(2, token);
                pst.setString(1, "");
               
                
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
