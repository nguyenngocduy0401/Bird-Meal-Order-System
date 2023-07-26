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
import java.util.List;
import sample.dto.BirdDTO;
import sample.dto.CategoriesBirdDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class CategoriesBirdDAO {

    public static ArrayList<CategoriesBirdDTO> getCategoriesBirdsByProductID() {
        ArrayList<CategoriesBirdDTO> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from Accounts";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String Fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        CategoriesBirdDTO acc = new CategoriesBirdDTO(AccID, AccID);
                        list.add(acc);
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

    public static List<BirdDTO> getBirdList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BirdDTO> listBird = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [BirdID]\n"
                        + "      ,[BirdName]\n"
                        + "      ,[Status]\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Bird]";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String birdName = rs.getString("BirdName");
                    int birdID = rs.getInt("BirdID");
                    int status = rs.getInt("Status");
                    
                    listBird.add(new BirdDTO(birdID, birdName,status));
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
        return listBird;
    }
}
