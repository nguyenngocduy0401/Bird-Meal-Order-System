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
@WebServlet(name = "EditPasswordServlet", urlPatterns = {"/EditPasswordServlet"})
public class EditPasswordServlet extends HttpServlet {

    private final String ERROR_PAGE = "errorRegistration.html";
    private final String EDIT_PASSWORD = "editPassword.jsp";

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
        String currentPassword = request.getParameter("txtCurrentPassword");
        String newPassword = request.getParameter("txtNewPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String url = EDIT_PASSWORD;
        InformationCreateError errors = new InformationCreateError();
        boolean foundError = false;
        try {
            HttpSession session = request.getSession(false);
            if (session.getAttribute("user") != null) {
            UserDAO dao = new UserDAO();
            UserDTO dto = (UserDTO)session.getAttribute("user");
                if (!currentPassword.equals(dto.getPassword())) {
                    foundError = true;
                    errors.setPasswordOldWrong("The current password isn't right!");
                }//end !dto.getPassword().equals(currentPassword)
                else if (newPassword.trim().length() < 8
                        || newPassword.trim().length() > 30) {
                    foundError = true;
                    errors.setPasswordLengthError("Password is required input from 8 to 30 characters");
                }
                if (!confirmPassword.trim().equals(newPassword.trim())) {
                    foundError = true;
                    errors.setConfirmNotMatched("Confirm must match password");
                }
            
            if (foundError) {
                request.setAttribute("UPDATE_PASSWORD_ERROR", errors);
            } else {
                boolean result = dao.updatePasswordByEmail(dto.getEmail(), newPassword);
                if (result){
                    request.setAttribute("UPDATE_PASSWORD_SUCCESS", result);
                }
            }}
            else {
                url = ERROR_PAGE;
            }
        } catch (ClassNotFoundException ex) {
            log("EditPasswordServlet_ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            log("EditPasswordServlet_SQLException" + ex.getMessage());
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
