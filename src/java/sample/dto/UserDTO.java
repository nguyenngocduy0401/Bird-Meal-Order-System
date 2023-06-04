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
public class UserDTO {

    private int userID;
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private int role;
    private boolean status;
    private String address;
    private String phoneNumber;
    private boolean gender;
    private int numberReport;

    public UserDTO() {
    }

    public UserDTO(int userID, String userName, String password, String email, String fullName, int role, boolean status, String address, String phoneNumber, boolean gender, int numberReport) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.numberReport = numberReport;
    }

    public UserDTO(String userName, String password, String email, String fullName, int role, boolean status, String address, String phoneNumber, boolean gender, int numberReport) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.numberReport = numberReport;
    }

    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getNumberReport() {
        return numberReport;
    }

    public void setNumberReport(int numberReport) {
        this.numberReport = numberReport;
    }

   
}
