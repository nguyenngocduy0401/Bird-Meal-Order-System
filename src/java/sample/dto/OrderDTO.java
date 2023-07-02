/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OrderDTO implements Serializable {

    private ArrayList<ProductDTO> productsList;
    private int orderID;
    private int userID;
    private String fullName;
    private String phoneNumber;
    private String shippingDate;
    private String date;
    private int status;
    private String orderAddress;
    private String note;
    private double shippingFee;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int userID, String fullName, String phoneNumber, String shippingDate, String date, int status, String orderAddress, String note) {
        this.orderID = orderID;
        this.userID = userID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.shippingDate = shippingDate;
        this.date = date;
        this.status = status;
        this.orderAddress = orderAddress;
        this.note = note;
    }
    
    public OrderDTO(int orderID, int userID, String fullName, String phoneNumber, String date, int status, String orderAddress, String note) {
        this.orderID = orderID;
        this.userID = userID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.status = status;
        this.orderAddress = orderAddress;
        this.note = note;
    }

    public OrderDTO(int orderID, int userID, String fullName, String phoneNumber, String shippingDate, String date, int status, String orderAddress, String note, double price) {
        this.orderID = orderID;
        this.userID = userID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.shippingDate = shippingDate;
        this.date = date;
        this.status = status;
        this.orderAddress = orderAddress;
        this.note = note;
        this.shippingFee = price;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }
     

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<ProductDTO> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<ProductDTO> productsList) {
        this.productsList = productsList;
    }
    

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

}
