/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;

/**
 *
 * @author Duy
 */
public class ProductDTO implements Serializable{
   
    
    private int productID;
    private String productName;
    private double price;
    private int quantity;
    private String categoryID;
    private String productDetail;
    private String size;
    private int ageRecommendation;
    private String date;
    private int status;
    private String country;
    private String imgPath;

    public ProductDTO() {
    }

    public ProductDTO(int productID, String productName, double price, int quantity, String categoryID, String productDetail, String size, int ageRecommendation, String date, int status, String country, String imgPath) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.productDetail = productDetail;
        this.size = size;
        this.ageRecommendation = ageRecommendation;
        this.date = date;
        this.status = status;
        this.country = country;
        this.imgPath = imgPath;
    }

    public ProductDTO(String productName, double price, int quantity, String categoryID, String productDetail, String size, int ageRecommendation, String date, int status, String country, String imgPath) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.productDetail = productDetail;
        this.size = size;
        this.ageRecommendation = ageRecommendation;
        this.date = date;
        this.status = status;
        this.country = country;
        this.imgPath = imgPath;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public String getSize() {
        return size;
    }

    public int getAgeRecommendation() {
        return ageRecommendation;
    }

    public String getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public String getCountry() {
        return country;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAgeRecommendation(int ageRecommendation) {
        this.ageRecommendation = ageRecommendation;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    
    
}
