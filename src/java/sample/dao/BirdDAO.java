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
import sample.dto.BirdDTO;
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
                String sql = "SELECT Bird.BirdID, BirdName \n"
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
                        
                        BirdDTO bird = new BirdDTO(birdID, birdName);
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
}
