/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;
<<<<<<< Updated upstream

/**
 *
 * @author Duy
 */
public class ProductDTO implements Serializable{
   
    
    private int productID;
=======
import java.util.Objects;

/**
 *
 * @author DucAnh
 */
public class ProductDTO implements Serializable{

    private String productID;
>>>>>>> Stashed changes
    private String productName;
    private double price;
    private int quantity;
    private String categoryID;
    private String productDetail;
    private String size;
    private int ageRecommendation;
    private String date;
<<<<<<< Updated upstream
    private int status;
    private String country;
    private String imgPath;
    public ProductDTO() {
    }

    public ProductDTO(String productName, double price, int quantity, String categoryID, String productDetail, String size, int ageRecommendation, String date, int status, String country) {
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
    }

    public ProductDTO(int productID, String productName, double price, int quantity, String categoryID, String productDetail, String size, int ageRecommendation, String date, int status, String country) {
=======
    private boolean status;
    private String country;
    
    
       
    


    public ProductDTO(String productID, String productName, double price, int quantity, String categoryID, String productDetail, String size, int ageRecommendation, String date, boolean status, String country) {
>>>>>>> Stashed changes
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
    }

<<<<<<< Updated upstream
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

=======
   public ProductDTO(){
       
   }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

>>>>>>> Stashed changes
    public String getProductName() {
        return productName;
    }

<<<<<<< Updated upstream
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

=======
>>>>>>> Stashed changes
    public void setProductName(String productName) {
        this.productName = productName;
    }

<<<<<<< Updated upstream
=======
    public double getPrice() {
        return price;
    }

>>>>>>> Stashed changes
    public void setPrice(double price) {
        this.price = price;
    }

<<<<<<< Updated upstream
=======
    public int getQuantity() {
        return quantity;
    }

>>>>>>> Stashed changes
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

<<<<<<< Updated upstream
=======
    public String getCategoryID() {
        return categoryID;
    }

>>>>>>> Stashed changes
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

<<<<<<< Updated upstream
=======
    public String getProductDetail() {
        return productDetail;
    }

>>>>>>> Stashed changes
    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

<<<<<<< Updated upstream
=======
    public String getSize() {
        return size;
    }

>>>>>>> Stashed changes
    public void setSize(String size) {
        this.size = size;
    }

<<<<<<< Updated upstream
=======
    public int getAgeRecommendation() {
        return ageRecommendation;
    }

>>>>>>> Stashed changes
    public void setAgeRecommendation(int ageRecommendation) {
        this.ageRecommendation = ageRecommendation;
    }

<<<<<<< Updated upstream
=======
    public String getDate() {
        return date;
    }

>>>>>>> Stashed changes
    public void setDate(String date) {
        this.date = date;
    }

<<<<<<< Updated upstream
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
    
    

=======
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        hash = 47 * hash + Objects.hashCode(this.country);
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
       
        return true;
    }
    
>>>>>>> Stashed changes
}
