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
import javax.naming.NamingException;
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
                String s = "SELECT CategoryID, CategoryName, [Status]\n"
                        + "FROM Category\n"
                        + "WHERE CategoryID = ?;";
                PreparedStatement pst = cn.prepareStatement(s);
                pst.setInt(1, getCategoryID);
                ResultSet kq = pst.executeQuery();
                if (kq != null) {
                    while (kq.next()) {
                        int categoryID = kq.getInt("CategoryID");
                        String categoryName = kq.getString("CategoryName");
                        int status = kq.getInt("Status");
                        category = new CategoryDTO(categoryID, categoryName, status);

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
                        + ",[Status]"
                        + "  FROM [ProjectBirdMealOrderSystem].[dbo].[Category]";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int cateID = rs.getInt("CategoryID");
                    String cateName = rs.getString("CategoryName");
                    int status = rs.getInt("Status");
                    listCate.add(new CategoryDTO(cateID, cateName, status));
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

    public static boolean createCategory(CategoryDTO category)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO [Category] "
                        + "([CategoryName], [Status]) "
                        + "VALUES (?, 1)";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setString(1, category.getCategoryName());
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            result = false;
            return result;
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

    public static boolean removeCategoryByID(int cateID)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  DELETE Category\n"
                        + "  WHERE [CategoryID] = ? ";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, cateID);
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

    public static boolean updateCategoryByID(int cateID, int status)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  UPDATE [Category]\n"
                        + "  SET [Status] = ?\n"
                        + "  WHERE CategoryID = ? ";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, cateID);
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

}
