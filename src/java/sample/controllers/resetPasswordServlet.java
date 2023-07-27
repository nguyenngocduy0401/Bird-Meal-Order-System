/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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
import javax.servlet.http.HttpSession;
import sample.dao.TokenDAO;
import sample.dao.UserDAO;
import sample.dto.InformationCreateError;
import sample.dto.UserDTO;

/**
 *
 * @author haong
 */
@WebServlet(name = "resetPasswordServlet", urlPatterns = {"/resetPasswordServlet"})
public class resetPasswordServlet extends HttpServlet {

    private final String RESET_PASSWORD_PAGE = "resetPassword.jsp";
    private final String ERROR_PAGE = "error.html";

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String url = ERROR_PAGE;
        InformationCreateError errors = new InformationCreateError();
        boolean foundError = false;
        try {
            if (password.trim().length() < 8
                    || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password is required input from 8 to 30 characters");
            } else if (!confirmPassword.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }

            if (foundError) {
                request.setAttribute("RESET_PASSWORD_ERROR", errors);
                url = RESET_PASSWORD_PAGE;
                return;
            } else {
                UserDAO dao = new UserDAO();
                boolean result = dao.updatePasswordByEmail(email, password);
                if (result) {
                    request.setAttribute("RESET_PASSWORD_SUCCESS", result);
                    TokenDAO tdao = new TokenDAO();
                    tdao.DeleteTokenFromEmail(email);
                    url = RESET_PASSWORD_PAGE;
                }
            }
        } catch (ClassNotFoundException ex) {
            log("resetPasswordServlet_ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            log("resetPasswordServlet_SQLException" + ex.getMessage());
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
