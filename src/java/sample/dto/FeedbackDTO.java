/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class FeedbackDTO implements Serializable {

    private int feedbackID;
    private int orderID;
    private int userID;
    private int productID;
    private String feedbackDetails;
    private String feedbackDate;
    private int rate;
    private String replyDate;
    private String replyDetails;
    private String replyStaff;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int orderID, int userID, int productID, String feedbackDetails, String feedbackDate, int rate) {
        this.orderID = orderID;
        this.userID = userID;
        this.productID = productID;
        this.feedbackDetails = feedbackDetails;
        this.feedbackDate = feedbackDate;
        this.rate = rate;
    }

    public FeedbackDTO(int feedbackID, int orderID, int userID, int productID, String feedbackDetails, String feedbackDate, int rate) {
        this.feedbackID = feedbackID;
        this.orderID = orderID;
        this.userID = userID;
        this.productID = productID;
        this.feedbackDetails = feedbackDetails;
        this.feedbackDate = feedbackDate;
        this.rate = rate;
    }

    public FeedbackDTO(int feedbackID, int orderID, int userID, int productID, String feedbackDetails, String feedbackDate, int rate, String replyDate, String replyDetails, String replyStaff) {
        this.feedbackID = feedbackID;
        this.orderID = orderID;
        this.userID = userID;
        this.productID = productID;
        this.feedbackDetails = feedbackDetails;
        this.feedbackDate = feedbackDate;
        this.rate = rate;
        this.replyDate = replyDate;
        this.replyDetails = replyDetails;
        this.replyStaff = replyStaff;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getFeedbackDetails() {
        return feedbackDetails;
    }

    public void setFeedbackDetails(String feedbackDetails) {
        this.feedbackDetails = feedbackDetails;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyDetails() {
        return replyDetails;
    }

    public void setReplyDetails(String replyDetails) {
        this.replyDetails = replyDetails;
    }

    public String getReplyStaff() {
        return replyStaff;
    }

    public void setReplyStaff(String replyStaff) {
        this.replyStaff = replyStaff;
    }

    
    
}
