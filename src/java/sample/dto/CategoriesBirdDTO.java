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
    private int birdID;
    private int productID;

    public CategoriesBirdDTO() {
    }

    public CategoriesBirdDTO(int birdID, int broductID) {
        this.birdID = birdID;
        this.productID = broductID;
    }

    public int getBirdID() {
        return birdID;
    }

    public int getBroductID() {
        return productID;
    }

    public void setBirdID(int birdID) {
        this.birdID = birdID;
    }

    public void setBroductID(int broductID) {
        this.productID = broductID;
    }

    
    
}
