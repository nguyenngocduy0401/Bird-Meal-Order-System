/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;

/**
 *
 * @author DucAnh
 */
public class OrderGuestDTO implements Serializable {

    private int orderID;
    private String fullName;
    private String phoneNumber;
    private String orderDate;
    private String shippingDate;
    private int status;
    private String orderAddress;
    private String notes;

    public OrderGuestDTO() {

    }

    public OrderGuestDTO(int orderID, String fullName, String phoneNumber, String orderDate, String shippingDate, int status, String orderAddress, String token, String notes) {
        this.orderID = orderID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.status = status;
        this.orderAddress = orderAddress;
        this.notes = notes;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
