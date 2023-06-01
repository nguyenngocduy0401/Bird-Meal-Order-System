/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import sample.dto.TokenDTO;
import sample.utils.DBUtils;

/**
 *
 * @author haong
 */
public class TokenDAO implements Serializable{
    
    
    public TokenDTO findByToken(String token)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT UserEmail, expiresAt "
                        + "FROM Token "
                        + "WHERE  token = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, token);
                rs = stm.executeQuery();
                if(rs.next()) {
                    String email = rs.getString("UserEmail");
                    Timestamp expiresAt = rs.getTimestamp("expiresAt");
                    TokenDTO dto = new TokenDTO(expiresAt, email);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    public boolean DeleteTokenFromEmail(String email)
            throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. make connection to DB
            con = DBUtils.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Delete From Token "
                        + "Where UserEmail = ?";
                //3. create statement object to load SQL String
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                //4. execute query
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
                    return true;
                }
            }//end if con is opened
        } finally { 
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean saveConfirmationToken(TokenDTO dto)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Token("
                        + "Token, CreatedAt, expiresAt, UserEmail"
                        + ") VALUES("
                        + "? , ?, ?, ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getToken());
                stm.setTimestamp(2, dto.getCreatedAt());
                stm.setTimestamp(3, dto.getExpiresAt());
                stm.setString(4, dto.getEmail());
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
}
