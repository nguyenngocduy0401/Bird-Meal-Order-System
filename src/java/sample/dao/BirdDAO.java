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
import java.util.ArrayList;
import javax.naming.NamingException;
import sample.dto.BirdDTO;
import sample.dto.ProductDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class BirdDAO {

    public static ArrayList<BirdDTO> getBirdsByProductID(int productID) {
        ArrayList<BirdDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT Bird.BirdID, BirdName, Bird.[Status] \n"
                        + "FROM Product\n"
                        + "JOIN CategoriesBird ON Product.ProductID = CategoriesBird.ProductID\n"
                        + "JOIN Bird ON CategoriesBird.BirdID = Bird.BirdID\n"
                        + "WHERE Product.ProductID = ?;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, productID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        String birdName = rs.getString("BirdName");
                        int birdID = rs.getInt("BirdID");
                        int status = rs.getInt("Status");

                        BirdDTO bird = new BirdDTO(birdID, birdName, status);
                        list.add(bird);
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

    public static ArrayList<BirdDTO> getAllBird() {
        ArrayList<BirdDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [BirdID]\n"
                        + "         ,[BirdName]\n"
                        + "         ,[Status]\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Bird] ";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        String birdName = rs.getString("BirdName");
                        int birdID = rs.getInt("BirdID");
                        int status = rs.getInt("Status");
                        BirdDTO bird = new BirdDTO(birdID, birdName, status);
                        list.add(bird);
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
    
     public static ArrayList<BirdDTO> getAllAvailableBird() {
        ArrayList<BirdDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [BirdID]\n"
                        + "         ,[BirdName]\n"
                        + "         ,[Status]\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Bird] "
                        + "Where Status = 1";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        String birdName = rs.getString("BirdName");
                        int birdID = rs.getInt("BirdID");
                        int status = rs.getInt("Status");
                        BirdDTO bird = new BirdDTO(birdID, birdName, status);
                        list.add(bird);
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


    public static boolean createBird(BirdDTO bird)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  INSERT INTO [Bird] ([BirdName],[Status])\n"
                        + "  VALUES (?, 1)";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setString(1, bird.getBirdName());
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            result = false;
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

    public static boolean removeBirdByID(int birdID)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  DELETE Bird\n"
                        + "  WHERE [BirdID] = ? ";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, birdID);
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            result = false;
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
    
    public static boolean updateBirdByID(int birdID, int status)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  UPDATE [Bird]\n"
                        + "  SET [Status] = ?\n"
                        + "  WHERE BirdID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, birdID);
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            result = false;
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
        boolean rs = BirdDAO.removeBirdByID(1);
        System.out.println(rs);
    }

}
