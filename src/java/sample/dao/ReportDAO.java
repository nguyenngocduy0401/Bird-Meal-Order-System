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
import java.time.LocalDate;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class ReportDAO {

    public static boolean giveReportFeedback(int userID, int feedbackID, String details, int rate) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        LocalDate currentDate = LocalDate.now();
        String now = currentDate.toString();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  INSERT INTO [Report] (UserID, [FeedbackID],[Status],[ReportDetail],[ReportDate], Rate)\n"
                        + "	VALUES (?,?,0,?,?,?)";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setInt(2, feedbackID);
                stm.setString(3, details);
                stm.setString(4, now);
                stm.setInt(5, rate);
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

    public static boolean updateReportFeedback(int userID, int feedbackID, String details, int rate) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        LocalDate currentDate = LocalDate.now();
        String now = currentDate.toString();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "  INSERT INTO [Report] (UserID, [FeedbackID],[Status],[ReportDetail],[ReportDate], Rate)\n"
                        + "	VALUES (?,?,0,?,?,?)";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setInt(2, feedbackID);
                stm.setString(3, details);
                stm.setString(4, now);
                stm.setInt(5, rate);
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

    public static int getReportByFeedbackID(int feedbackID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 1;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [ReportID]\n"
                        + "      ,[UserID]\n"
                        + "      ,FeedbackID\n"
                        + "      ,[Status]\n"
                        + "      ,[ReportDetail]\n"
                        + "      ,[ReportDate]\n"
                        + "      ,[Rate]\n"
                        + "  FROM [Report]\n"
                        + "  WHERE FeedbackID = ?";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result = 0;
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
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(ReportDAO.getReportByFeedbackID(1));
    }

}
