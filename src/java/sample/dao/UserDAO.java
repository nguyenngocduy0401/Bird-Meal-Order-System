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
                String s = "select UserID,UserName,Password,Email,Fullname,Role,Status,Address,PhoneNumber,Gender,NumberReport\n"
                        + "from [User]\n"
                        + "where  UserName=? and Password=? ";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setString(1, getUsername);
                pst.setString(2, getPassword);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        int userID = kq.getInt("UserID");
                        String userName = kq.getString("UserName");
                        String password = kq.getString("Password");
                        String email = kq.getString("Email");
                        String fullName = kq.getString("Fullname");
                        int role = kq.getInt("Role");
                        boolean status = kq.getBoolean("Status");
                        String address = kq.getString("Address");
                        String phoneNumber = kq.getString("PhoneNumber");
                        boolean gender = kq.getBoolean("Gender");
                        int numberReport = kq.getInt("NumberReport");
                        user = new UserDTO(userID, userName, password, email, fullName, role, status, address, phoneNumber, gender, numberReport);

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
                        String phoneNumber = kq.getString("PhoneNumber");
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
                        String phoneNumber = kq.getString("PhoneNumber");
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
    
    public static boolean createAccount(UserDTO dto)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO [User]("
                        + "UserName, Password, Email, Fullname, Role, Status, Address, PhoneNumber, Gender, NumberReport"
                        + ") VALUES("
                        + "? , ?, ?, ?, ?, ?, ?, ?, ? , ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUserName());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getEmail());
                stm.setNString(4, dto.getFullName());
                stm.setInt(5, dto.getRole());
                stm.setBoolean(6, dto.isStatus());
                stm.setNString(7, dto.getAddress());
                stm.setString(8, dto.getPhoneNumber());
                stm.setBoolean(9, dto.isGender());
                stm.setInt(10, dto.getNumberReport());

                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }
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
    public boolean removeAccount(String username)
                throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null; 
        boolean result = false; 
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Delete From [User] "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                int effectRow = stm.executeUpdate();
                //5. Preocess result
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
     public boolean updateAccount(String username, String fullName, String address, String phoneNumber, Boolean gender)
            throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null; //mo cuoi cung đóng đầu tiên (finally close) neu ko tk kia dong trc se bi crash
        boolean result = false; //thong qua bien trung gian
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Update [User] "
                        + "Set Fullname = ?, Address = ?, PhoneNumber = ?, Gender = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setNString(1, fullName);
                stm.setString(2, address);
                stm.setString(3, phoneNumber);
                stm.setBoolean(4, gender);
                stm.setString(5, username);
                        
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

    public static boolean checkEmailExist(String email)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT Email "
                        + "FROM [User] "
                        + "WHERE email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
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
    public static boolean updateAccountCustomer(String username, String fullName, String address, String phoneNumber, Boolean gender)
            throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null; //mo cuoi cung đóng đầu tiên (finally close) neu ko tk kia dong trc se bi crash
        boolean result = false; //thong qua bien trung gian
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Update [User] "
                        + "Set Fullname = ?, Address = ? , PhoneNumber = ?, Gender = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, fullName);
                stm.setString(2, address);
                stm.setString(3, phoneNumber);
                stm.setBoolean(4, gender);
                stm.setString(5, username);
                        
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
    public static boolean updatePassword(String username, String password)
            throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null; //mo cuoi cung đóng đầu tiên (finally close) neu ko tk kia dong trc se bi crash
        boolean result = false; //thong qua bien trung gian
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Update [User] "
                        + "Set Password = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, username);
                        
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
}
