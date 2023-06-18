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

    public static List<CategoryDTO> getCatetoryList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CategoryDTO> listCate = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [ProjectBirdMealOrderSystem].[dbo].Category.CategoryID,\n"
                        + "[ProjectBirdMealOrderSystem].[dbo].Category.CategoryName\n"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Category]";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int cateID = rs.getInt("CategoryID");
                    String cateName = rs.getString("CategoryName");
                    
                    listCate.add(new CategoryDTO(cateID, cateName));
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
        return listCate;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryDAO dao = new CategoryDAO();
        List<CategoryDTO> listCate = dao.getCatetoryList();
        for (CategoryDTO dto : listCate) {
            System.out.println(dto);
        }

    }
}
