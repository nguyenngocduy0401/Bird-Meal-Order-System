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
public class CategoriesBirdDTO {
    private int BirdID;
    private int ProductID;

    public CategoriesBirdDTO() {
    }

    public CategoriesBirdDTO(int BirdID, int ProductID) {
        this.BirdID = BirdID;
        this.ProductID = ProductID;
    }

    public int getBirdID() {
        return BirdID;
    }

    public void setBirdID(int BirdID) {
        this.BirdID = BirdID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }
    
}
