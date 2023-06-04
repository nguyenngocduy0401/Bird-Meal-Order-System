/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Duy
 */
public class ProductDTO implements Serializable{
   
    
    private int productID;
    private String productName;
    private double price;
    private int quantity;
    private int categoryID;
    private String productDetail;
    private String size;
    private int ageRecommendation;
    private String date;
    private int status;
    private String country;
    private String imgPath;

    public ProductDTO() {
    }

    public ProductDTO(int productID, String productName, double price, int quantity, int categoryID, String productDetail, String size, int ageRecommendation, String date, int status, String country, String imgPath) {
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

    public ProductDTO(String productName, double price, int quantity, int categoryID, String productDetail, String size, int ageRecommendation, String date, int status, String country, String imgPath) {
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

    public int getCategoryID() {
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

    public String getImgPath() {
        return imgPath;
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

    public void setCategoryID(int categoryID) {
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

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.productID);
        hash = 47 * hash + Objects.hashCode(this.productName);
        hash = 47 * hash + Objects.hashCode(this.price);
        hash = 47 * hash + Objects.hashCode(this.categoryID);
        hash = 47 * hash + Objects.hashCode(this.productDetail);
        hash = 47 * hash + Objects.hashCode(this.ageRecommendation);
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.status);
        hash = 47 * hash + Objects.hashCode(this.size);
        hash = 47 * hash + Objects.hashCode(this.country);
        hash = 47 * hash + Objects.hashCode(this.imgPath);
        hash = 47 * hash + this.quantity;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductDTO other = (ProductDTO) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.categoryID, other.categoryID)) {
            return false;
        }
        if (!Objects.equals(this.productDetail, other.productDetail)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.ageRecommendation, other.ageRecommendation)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }

        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.imgPath, other.imgPath)) {
            return false;
        }

        return true;
    }

}
