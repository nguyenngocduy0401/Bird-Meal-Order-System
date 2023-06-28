/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.dto.AddressDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Duy
 */
public class AddressDAO {

    public static ArrayList<AddressDTO> getAddress(int getUserID) {
        ArrayList<AddressDTO> list = new ArrayList<>();
        AddressDTO addressDTO = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT AddressID, UserID, Fullname, AddressDetail, PhoneNumber \n"
                        + "FROM Address \n"
                        + "WHERE UserID = ?;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, getUserID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int addressID = rs.getInt("AddressID");
                        int userID = rs.getInt("UserID");
                        String fullName = rs.getString("Fullname");
                        String addressDetail = rs.getString("AddressDetail");
                        String phoneNumber = rs.getString("PhoneNumber");
                        addressDTO = new AddressDTO(addressID, userID, fullName, addressDetail, phoneNumber);
                        list.add(addressDTO);
                    }
                    cn.close();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
