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
import java.util.ArrayList;
import java.util.List;
import sample.dto.FeedbackDTO;
import sample.dto.ReportDTO;
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

    public static boolean updateReportFeedback(int reportID, int status) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "    UPDATE [Report]\n"
                        + "  SET [Status] = ?\n"
                        + "  WHERE [ReportID] = ? ";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(2, reportID);
                stm.setInt(1, status);
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

    public static List<ReportDTO> getAllReport() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ReportDTO> listReport = new ArrayList<>();
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
                        + "  FROM [Report]\n";
                //3 create
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int reportID = rs.getInt("ReportID");
                    int userID = rs.getInt("UserID");
                    int feedbackID = rs.getInt("FeedbackID");
                    int status = rs.getInt("Status");
                    String details = rs.getString("ReportDetail");
                    String date = rs.getString("ReportDate");
                    int rate = rs.getInt("Rate");

                    ReportDTO report = new ReportDTO(reportID, userID, feedbackID, status, details, date, rate);
                    listReport.add(report);
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
        return listReport;
    }
    
     public static ReportDTO getReportByReportID(int reportID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ReportDTO report = null;
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
                        + " WHERE [ReportID] = ? ";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, reportID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    int feedbackID = rs.getInt("FeedbackID");
                    int status = rs.getInt("Status");
                    String details = rs.getString("ReportDetail");
                    String date = rs.getString("ReportDate");
                    int rate = rs.getInt("Rate");

                    report = new ReportDTO(reportID, userID, feedbackID, status, details, date, rate);
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
        return report;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<ReportDTO> listReport = ReportDAO.getAllReport();
        for (ReportDTO reportDTO : listReport) {
            System.out.println(reportDTO);
        }
    }

}
