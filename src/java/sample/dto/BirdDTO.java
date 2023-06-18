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
public class BirdDTO {
    private int birdID;
    private String birdName;

    public BirdDTO() {
    }

    public BirdDTO(String birdName) {
        this.birdName = birdName;
    }
    
    public BirdDTO(int birdID, String birdName) {
        this.birdID = birdID;
        this.birdName = birdName;
    }

    public int getBirdID() {
        return birdID;
    }

    public void setBirdID(int birdID) {
        this.birdID = birdID;
    }

    public String getBirdName() {
        return birdName;
    }

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }

    
}
