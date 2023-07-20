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
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class FeedbackDAO {

    public static boolean giveFeedback(int orderID, int userID, int productID, String details, int rate) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        LocalDate currentDate = LocalDate.now();
        String now = currentDate.toString();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Feedback (OrderID,ProductID,UserID, FeedBackDetail,FeedbackDate, Rate)\n"
                        + "VALUES (?,?,?,?,?,?)";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                stm.setInt(2, productID);
                stm.setInt(3, userID);
                stm.setString(4, details);
                stm.setString(5, now);
                stm.setInt(6, rate);
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

    public static int getAmountFeedbackByProductID(int productID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT (*) "
                        + "FROM [Feedback] "
                        + "WHERE [ProductID] = ? ";
                //3 create
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public static List<FeedbackDTO> getFeedbackByProductID(int productID)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FeedbackDTO> listFeedback = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [FeedbackID]\n"
                        + "      ,[OrderID]\n"
                        + "      ,[UserID]\n"
                        + "      ,[ProductID]\n"
                        + "      ,[FeedBackDetail]\n"
                        + "      ,[FeedbackDate]\n"
                        + "      ,[Rate]\n"
                        + "      ,[ReplyStaff]\n"
                        + "      ,[ReplyDetails]\n"
                        + "      ,[ReplyDate]"
                        + "  FROM [Feedback]\n"
                        + "  WHERE [ProductID] = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int feedbackID = rs.getInt("FeedbackID");
                    int orderID = rs.getInt("OrderID");
                    int userID = rs.getInt("UserID");
                    String details = rs.getString("FeedBackDetail");
                    String date = rs.getString("FeedbackDate");
                    int rate = rs.getInt("Rate");
                    String REplyStaff = rs.getString("ReplyStaff");
                    String replyDetails = rs.getString("ReplyDetails");
                    String replyDate = rs.getString("ReplyDate");

                    FeedbackDTO fb = new FeedbackDTO(feedbackID, orderID, userID, productID, details, date, rate, replyDate, replyDetails, REplyStaff);
                    listFeedback.add(fb);
                }//end while rs not null
            }//end if con is not null
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
        return listFeedback;
    }

    public static boolean updateFeedback(int feedbackID, String staff, String details, String date)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [Feedback] \n"
                        + "SET [ReplyStaff] = ?\n"
                        + "      ,[ReplyDetails]=?\n"
                        + "      ,[ReplyDate] = ?\n"
                        + "WHERE [FeedbackID] = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, staff);
                stm.setString(2, details);
                stm.setString(3, date);
                stm.setInt(4, feedbackID);
                int rowCount = stm.executeUpdate();
                result = (rowCount == 1);
            }//end if con is not null
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

    public static List<FeedbackDTO> getFeedBack()
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FeedbackDTO> listFeedback = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [FeedbackID]\n"
                        + "      ,[OrderID]\n"
                        + "      ,[UserID]\n"
                        + "      ,[ProductID]\n"
                        + "      ,[FeedBackDetail]\n"
                        + "      ,[FeedbackDate]\n"
                        + "      ,[Rate]\n"
                        + "      ,[ReplyStaff]\n"
                        + "      ,[ReplyDetails]\n"
                        + "      ,[ReplyDate]"
                        + "  FROM [Feedback]\n";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int feedbackID = rs.getInt("FeedbackID");
                    int orderID = rs.getInt("OrderID");
                    int userID = rs.getInt("UserID");
                    int productID = rs.getInt("ProductID");
                    String details = rs.getString("FeedBackDetail");
                    String date = rs.getString("FeedbackDate");
                    int rate = rs.getInt("Rate");
                    String REplyStaff = rs.getString("ReplyStaff");
                    String replyDetails = rs.getString("ReplyDetails");
                    String replyDate = rs.getString("ReplyDate");

                    FeedbackDTO fb = new FeedbackDTO(feedbackID, orderID, userID, productID, details, date, rate, REplyStaff, replyDetails, replyDate);
                    listFeedback.add(fb);
                }//end while rs not null
            }//end if con is not null
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
        return listFeedback;
    }

    public static FeedbackDTO getFeedbackUserProductOrder(int productID, int userID, int orderID)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        FeedbackDTO fb = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [FeedbackID]\n"
                        + "      ,[OrderID]\n"
                        + "      ,[UserID]\n"
                        + "      ,[ProductID]\n"
                        + "      ,[FeedBackDetail]\n"
                        + "      ,[FeedbackDate]\n"
                        + "      ,[Rate]\n"
                        + "      ,[ReplyStaff]\n"
                        + "      ,[ReplyDetails]\n"
                        + "      ,[ReplyDate]"
                        + "  FROM [Feedback]\n"
                        + "  WHERE [ProductID] = ? AND UserID = ? AND OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                stm.setInt(2, userID);
                stm.setInt(3, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int feedbackID = rs.getInt("FeedbackID");
                    String details = rs.getString("FeedBackDetail");
                    String date = rs.getString("FeedbackDate");
                    int rate = rs.getInt("Rate");
                    String REplyStaff = rs.getString("ReplyStaff");
                    String replyDetails = rs.getString("ReplyDetails");
                    String replyDate = rs.getString("ReplyDate");

                    fb = new FeedbackDTO(feedbackID, orderID, userID, productID, details, date, rate, replyDate, replyDetails, REplyStaff);
                }//end while rs not null
            }//end if con is not null
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
        return fb;
    }
    
    public static FeedbackDTO getFeedbackByID(int feedbackID)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        FeedbackDTO fb = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [FeedbackID]\n"
                        + "      ,[OrderID]\n"
                        + "      ,[UserID]\n"
                        + "      ,[ProductID]\n"
                        + "      ,[FeedBackDetail]\n"
                        + "      ,[FeedbackDate]\n"
                        + "      ,[Rate]\n"
                        + "      ,[ReplyStaff]\n"
                        + "      ,[ReplyDetails]\n"
                        + "      ,[ReplyDate]"
                        + "  FROM [Feedback]\n"
                        + "  WHERE [FeedbackID] = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, feedbackID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int userID = rs.getInt("UserID");
                    int productID = rs.getInt("ProductID");
                    String details = rs.getString("FeedBackDetail");
                    String date = rs.getString("FeedbackDate");
                    int rate = rs.getInt("Rate");
                    String REplyStaff = rs.getString("ReplyStaff");
                    String replyDetails = rs.getString("ReplyDetails");
                    String replyDate = rs.getString("ReplyDate");

                    fb = new FeedbackDTO(feedbackID, orderID, userID, productID, details, date, rate, replyDate, replyDetails, REplyStaff);
                }//end while rs not null
            }//end if con is not null
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
        return fb;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FeedbackDTO fb = FeedbackDAO.getFeedbackUserProductOrder(1, 4, 2);
        System.out.println(fb);
    }

}
