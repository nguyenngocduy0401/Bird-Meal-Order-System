/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.UserDAO;
import sample.dto.InformationCreateError;
import sample.dto.UserDTO;

/**
 *
 * @author haong
 */
@WebServlet(name = "editInformationServlet", urlPatterns = {"/editInformationServlet"})
public class EditInformationServlet extends HttpServlet {

    private final String EDIT_INFORMATION_RESULT_PAGE = "editInformation.jsp";
    private final String ERROR_PAGE = "errorRegistration.html";

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
        String fullName = request.getParameter("txtFullName");
        String phoneNumber = request.getParameter("txtPhoneNumber");
        String province = request.getParameter("ddlProvince");
        String district = request.getParameter("ddlDistrict");
        String ward = request.getParameter("ddlWard");
        String addressDetails = request.getParameter("txtAddressDetails");
        String address = "";
        if (!province.isEmpty() && !district.isEmpty() && !ward.isEmpty() && !addressDetails.isEmpty()) {
            address = addressDetails + ", " + ward + ", " + district + ", " + province;
        } else if (!province.isEmpty() && !district.isEmpty() && !ward.isEmpty()) {
            address = ward + ", " + district + ", " + province;
        } else if (!province.isEmpty() && !district.isEmpty()) {
            address = district + ", " + province;
        } else if (!province.isEmpty()) {
            address = province;
        }
        String ddlGender = request.getParameter("ddlGender").trim();
        boolean gender;
        gender = !ddlGender.equals("male");
        boolean foundError = false;
        String url = EDIT_INFORMATION_RESULT_PAGE;
        InformationCreateError errors = new InformationCreateError();
        try {
            HttpSession session = request.getSession(false);
            if (session.getAttribute("user") != null) {
                UserDTO dto = (UserDTO) session.getAttribute("user");
                UserDAO dao = new UserDAO();
                //1. check validation of users' errors.
                if (fullName.trim().length() < 2
                        || fullName.trim().length() > 50) {
                    foundError = true;
                    errors.setFullnameLengthError("Full name is required input from 2 to 50 characters");
                }
                if (InformationCreateError.validPhoneNumber(phoneNumber) == false) {
                    foundError = true;
                    errors.setPhoneNumberFormatError("Please enter a valid mobile phone number. Enter 10 digits starting from 0");
                }
                if (foundError) {
                    request.setAttribute("EDIT_INFORMATION_ERROR", errors);
                } else {
                    boolean result = dao.updateAccount(dto.getUserName().trim(), fullName, address, phoneNumber, gender);
                    if (result) {
                        UserDTO informationAccount = UserDAO.getUserByEmail(dto.getEmail());
                        session.setAttribute("user", informationAccount);
                        request.setAttribute("EDIT_INFORMATION_STATUS", result);
                    } else {
                        request.setAttribute("EDIT_INFORMATION_STATUS", result);
                    }
                }
            } else {
                url = ERROR_PAGE;
            }
        } catch (ClassNotFoundException ex) {
            log("editInformationServlet_ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            log("editInformationServlet_SQLException" + ex.getMessage());
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
