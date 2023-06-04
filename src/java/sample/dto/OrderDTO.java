/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class OrderDTO implements Serializable{
    
  private int orderID;
  private int userID;
  private Date orderDate;
  private Date shippingDate;
  private int status;
  private String orderAddress;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int userID, Date orderDate, Date shippingDate, int status, String orderAddress) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.status = status;
        this.orderAddress = orderAddress;
    }

    public OrderDTO(int userID, Date orderDate, Date shippingDate, int status, String orderAddress) {
        this.userID = userID;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.status = status;
        this.orderAddress = orderAddress;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
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
    
}
