/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.test;

import java.util.ArrayList;
import sample.dao.UserDAO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class TestgetUser {
    public static void main(String[] args) {
        UserDTO list = UserDAO.getUser("john123", "password123");
        
            System.out.println(list);
        
    }
}
