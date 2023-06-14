/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OrderDTO implements Serializable{
  private List<ProductDTO> productsList;
  private int orderID;
  private int userID;
  private String date;
  private int status;
  private String orderAddress;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int userID, String date, int status, String orderAddress) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.status = status;
        this.orderAddress = orderAddress;
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

    public void setProductsList(List<ProductDTO> productsList) {
        this.productsList = productsList;
    }

    public List<ProductDTO> getProductsList() {
        return productsList;
    }
    
}
