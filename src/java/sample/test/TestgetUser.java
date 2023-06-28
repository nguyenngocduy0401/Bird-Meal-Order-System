/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import sample.dao.AddressDAO;
import sample.dao.CartDAO;
import sample.dao.CartDetailDAO;
import sample.dao.OrderDAO;
import sample.dao.OrderDetailsDAO;
import sample.dto.AddressDTO;
import sample.dto.CartDTO;
import sample.dto.CartDetailDTO;

/**
 *
 * @author Duy
 */
public class TestgetUser {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {

        AddressDTO check = AddressDAO.getAddressByUserIDAndAddressID(5, 6);
        System.out.println(check);
    }
}

