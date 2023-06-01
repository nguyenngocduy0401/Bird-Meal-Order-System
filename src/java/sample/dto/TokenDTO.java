/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author haong
 */
public class TokenDTO implements Serializable{
    private long id;
    private String token;
    private Timestamp createdAt; //not null
    private Timestamp expiresAt; //not null
    private String email;

    public TokenDTO(String token, Timestamp createdAt, Timestamp expiresAt, String email) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.email = email;
    }

    public TokenDTO(Timestamp expiresAt, String email) {
        this.expiresAt = expiresAt;
        this.email = email;
    }
    

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   

    public TokenDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    
    
}
