/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

/**
 *
 * @author Duy
 */
public class CategoryDTO {
    private int categoryID;
    private String categoryName;
    private int status;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryID, String categoryName, int status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.status = status;
    }
    
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
