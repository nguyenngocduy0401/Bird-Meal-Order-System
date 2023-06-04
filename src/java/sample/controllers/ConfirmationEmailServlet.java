/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author haong
 */
import sample.dao.TokenDAO;
import sample.dto.TokenDTO;
@WebServlet(name = "ConfirmationEmailServlet", urlPatterns = {"/ConfirmationEmailServlet"})
public class ConfirmationEmailServlet extends HttpServlet {
    private final String ERROR_REGISTRATION = "errorRegistration.html";
    private final String REGISTER_PAGE = "register.jsp";
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
        String token = request.getParameter("token").trim();
        String url = ERROR_REGISTRATION;
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        try {
            TokenDAO dao = new TokenDAO();
            TokenDTO dto = dao.findByToken(token);
            if (dto == null) {
                return;
            } else if (dto.getExpiresAt().before(now)) {
                return;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("REGISTRATION", dto);
                url = REGISTER_PAGE;
            }
            
        } catch (ClassNotFoundException ex) {
            log("ConfirmationEmailServlet_ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
             log("ConfirmationEmailServlet_SQLException" + ex.getMessage());
        }finally{
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
