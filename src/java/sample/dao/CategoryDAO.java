/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sample.dto.CategoryDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class CategoryDAO {
    public static CategoryDTO getCategoryByID(int getCategoryID) {
        CategoryDTO category = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String s = "SELECT CategoryID, CategoryName\n"
                        + "FROM Category\n"
                        + "WHERE CategoryID = ?;";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setInt(1, getCategoryID);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        int categoryID = kq.getInt("CategoryID");
                        String categoryName = kq.getString("CategoryName");
                        category = new CategoryDTO(categoryID, categoryName);

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
        return category;
    }

}
