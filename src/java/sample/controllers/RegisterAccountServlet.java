/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.TokenDAO;
import sample.dao.UserDAO;
import sample.dto.InformationCreateError;
import sample.dto.UserDTO;

/**
 *
 * @author haong
 */
@WebServlet(name = "RegisterAccountServlet", urlPatterns = {"/RegisterAccountServlet"})
public class RegisterAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername").trim();
        String password = request.getParameter("txtPassword").trim();
        String confirmPassword = request.getParameter("txtConfirmPassword").trim();
        String email = request.getParameter("txtEmail").trim();
        String fullName = request.getParameter("txtFullName").trim();
        String phoneNumber = request.getParameter("txtPhoneNumber").trim();
        String province = request.getParameter("ddlProvince").trim();
        String district = request.getParameter("ddlDistrict").trim();
        String ward = request.getParameter("ddlWard").trim();
        String addressDetails = request.getParameter("txtAddressDetails").trim();
        String address = "";
        String ddlGender = request.getParameter("ddlGender").trim();
        boolean gender;
        if(!province.isEmpty() && !district.isEmpty() && !ward.isEmpty() && !addressDetails.isEmpty()) {
            address = addressDetails + ", " + ward + ", " + district + ", " + province;
        } else if(!province.isEmpty() && !district.isEmpty() && !ward.isEmpty()) {
            address = ward + ", " + district + ", " + province;
        }
        if (ddlGender.equals("male")) {
            gender = false;
        } else {
            gender = true;
        }
        boolean foundError = false;
        String url = ERROR_PAGE;
        InformationCreateError errors = new InformationCreateError();
        try {
            //1. check validation of users' errors.
            if (username.trim().length() < 6
                    || username.trim().length() > 30) {
                foundError = true;
                errors.setUsernameLengthError("Username is required input from 6 to 30 characters");
            }
            if (password.trim().length() < 8
                    || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password is required input from 8 to 30 characters");
            } else if (!confirmPassword.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (InformationCreateError.validEmail(email) == false) {
                foundError = true;
                errors.setEmailFormatError("Please enter a valid email");
            }
            if (fullName.trim().length() < 2
                    || fullName.trim().length() > 50) {
                foundError = true;
                errors.setFullnameLengthError("Full name is required input from 2 to 50 characters");
            }
            if (InformationCreateError.validPhoneNumber(phoneNumber) == false) {
                foundError = true;
                errors.setPhoneNumberFormatError("Please enter a valid mobile phone number. Enter 10 digits starting from 0");
            }
            //2.Process
            //2.1 if errors occur, thong bao cho ng dung
            //2.2 ko co loi, call model
            if (foundError) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                UserDAO dao = new UserDAO();
                UserDTO dto = new UserDTO(username, password, email, fullName, 2, true, address, phoneNumber, gender, 0);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = LOGIN_PAGE;
                    TokenDAO tdao = new TokenDAO();
                    tdao.DeleteTokenFromEmail(email);
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("RegisterAccountServlet_SQLException" + msg);
            if(msg.contains("A9D105")) {
                errors.setEmailIsExisted("There is already an account associated with this email address");
                request.setAttribute("CREATE_ERROR", errors);
            }
            if(msg.contains("C9F284")) {
                errors.setUsernameIsExisted(username +" is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (ClassNotFoundException ex) {
            log("RegisterAccountServlet_ClassNotFoundException" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
