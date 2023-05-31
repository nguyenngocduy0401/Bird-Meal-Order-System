/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.test;

import java.util.ArrayList;
import sample.dao.BirdDAO;
import sample.dao.CategoryDAO;
import sample.dao.ProductDAO;
import sample.dao.UserDAO;
import sample.dto.BirdDTO;
import sample.dto.CategoryDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class TestgetUser {
    public static void main(String[] args) {
        
        
        
        ArrayList<BirdDTO> listBird = BirdDAO.getBirdsByProductID(2);
        for (BirdDTO birdDTO : listBird) {
            System.out.println(birdDTO.getBirdName());
        }
        
//        CategoryDTO i = CategoryDAO.getCategoryByID(1);
//        System.out.println(i.getCategoryName());
        
        
        
        
//        ProductDTO i = ProductDAO.getProductByID(1);
//        System.out.println(i.getStatus());
         
//        UserDTO user = UserDAO.getUser("asdasd", "asdasd");
//        
//        System.out.println(user);
//        
        
        
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
