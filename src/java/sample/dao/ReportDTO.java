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
public class ReportDTO implements Serializable{
    private int reportID;
    private int userID;
    private int feedbackID;
    private int status;
    private String reportDetail;
    private String reportDate;
    private int rate;

    public ReportDTO() {
    }

    public ReportDTO(int reportID, int userID, int feedbackID, int status, String reportDetail, String reportDate, int rate) {
        this.reportID = reportID;
        this.userID = userID;
        this.feedbackID = feedbackID;
        this.status = status;
        this.reportDetail = reportDetail;
        this.reportDate = reportDate;
        this.rate = rate;
    }

    /**
     * @return the reportID
     */
    public int getReportID() {
        return reportID;
    }

    /**
     * @param reportID the reportID to set
     */
    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the feedbackID
     */
    public int getFeedbackID() {
        return feedbackID;
    }

    /**
     * @param feedbackID the feedbackID to set
     */
    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the reportDetail
     */
    public String getReportDetail() {
        return reportDetail;
    }

    /**
     * @param reportDetail the reportDetail to set
     */
    public void setReportDetail(String reportDetail) {
        this.reportDetail = reportDetail;
    }

    /**
     * @return the reportDate
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }
    
    
    
}
