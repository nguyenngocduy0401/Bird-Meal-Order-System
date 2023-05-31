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
         
        UserDTO user = UserDAO.getUser("asdasd", "asdasd");
        
        System.out.println(user);
        
        
        
//        boolean list = UserDAO.deleteToken("748934c5-8dc0-4019-a427-7f6195bd0b11");
        
//        UserDTO list = UserDAO.getToken("0be73930-1b66-4ca5-aaaa-55d7f39af14c");
//        System.out.println(list);
//            if (list != null) {
//                System.out.println("haha");
//            }else{
//                System.out.println("nu");
//            }
        
    }
}
