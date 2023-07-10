/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.UserDAO;
import sample.dto.UserDTO;

/**
 *
 * @author Duy
 */
public class LoginController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String save = request.getParameter("savelogin");
            UserDTO user = null;
            UUID randomCode = UUID.randomUUID();
            String codeString = randomCode.toString();
            try {
                if (username == null || username.equals("") || password == null || password.equals("")) {
                    response.sendRedirect("login.jsp?check=1");
                } else {
                    user = UserDAO.getUser(username, password);
                    if (user != null && user.isStatus() != false && user.getNumberReport() != 10) {
                        //admin
                        if (user.getRole() == 0) {
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("user", user);
                                request.setAttribute("btAction", "AdminHome");
                                //create a cookie and attach it to response object
                                if (save != null) {
                                    String token = codeString;
                                    UserDAO.updateToken(token, username);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(365 * 24 * 60 * 60);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("AdminPageController");
                            }

                        } else if (user.getRole() == 1) {//staff
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("user", user);
                                //create a cookie and attach it to reponse object
                                if (save != null) {
                                    String token = codeString;
                                    UserDAO.updateToken(token, username);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(365 * 24 * 60 * 60);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("staff.jsp");
                            }
                        } else if (user.getRole() == 2) {//customer
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("user", user);
                                request.setAttribute("btAction", "Home");
                                //create a cookie and attach it to reponse object
                                if (save != null) {
                                    String token = codeString;
                                    UserDAO.updateToken(token, username);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(365 * 24 * 60 * 60);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("MainController");
                            } else {
                                response.sendRedirect("invalid.html");
                            }
                        }
                    } else {
                        if (user.isStatus() == false || user.getNumberReport() == 10) {
                            response.sendRedirect("login.jsp?check=3");
                        } else {
                            response.sendRedirect("login.jsp?check=2");
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
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
